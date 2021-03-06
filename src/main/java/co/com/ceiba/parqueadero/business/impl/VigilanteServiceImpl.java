package co.com.ceiba.parqueadero.business.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.CalculadorTiempoService;
import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.surcharge.SurchargeStrategy;
import co.com.ceiba.parqueadero.business.validation.PicoPlacaValidator;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.RegistroRepository;
import co.com.ceiba.parqueadero.repository.AbstractVehiculoRepository;
import co.com.ceiba.parqueadero.util.DateProvider;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;



@Service
public class VigilanteServiceImpl implements VigilanteService {

	private PropiedadService propiedadService;

	private PicoPlacaValidator placaValidator;

	private RegistroRepository registroRepository;

	private DateProvider dateProvider;

	private CalculadorTiempoService calculadorTiempoService;

	private List<SurchargeStrategy> surchargeStrategies;
	
	private ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	protected <T extends Vehiculo>AbstractVehiculoRepository<T> getVehiculoRepository(Class<T> vehiculoClass) {
		String[] beanNamesForType = getContext().getBeanNamesForType(ResolvableType.forClassWithGenerics(AbstractVehiculoRepository.class, vehiculoClass));
		return (AbstractVehiculoRepository<T>) getContext().getBean(beanNamesForType[0]);
	}

	protected RegistroRepository getRegistroRepository() {
		return registroRepository;
	}

	@Autowired
	public void setRegistroRepository(RegistroRepository registroRepository) {
		this.registroRepository = registroRepository;
	}

	protected PropiedadService getPropiedadService() {
		return propiedadService;
	}

	@Autowired
	public void setPropiedadService(PropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	protected PicoPlacaValidator getPlacaValidator() {
		return placaValidator;
	}

	@Autowired
	public void setPlacaValidator(PicoPlacaValidator placaValidator) {
		this.placaValidator = placaValidator;
	}

	protected DateProvider getDateProvider() {
		return dateProvider;
	}

	@Autowired
	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	public CalculadorTiempoService getCalculadorTiempoService() {
		return calculadorTiempoService;
	}

	@Autowired
	public void setCalculadorTiempoService(CalculadorTiempoService calculadorTiempoService) {
		this.calculadorTiempoService = calculadorTiempoService;
	}

	public List<SurchargeStrategy> getSurchargeStrategies() {
		return surchargeStrategies;
	}

	@Autowired
	public void setSurchargeStrategies(List<SurchargeStrategy> surchargeStrategies) {
		this.surchargeStrategies = surchargeStrategies;
	}

	@Override
	public Registro registrarEntrada(Vehiculo vehiculo) {
		if (vehiculo == null || vehiculo.getId() == null) {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO);
		}

		Optional<Registro> registroExistente = getRegistroRepository().findByVehiculoAndHoraSalidaIsNull(vehiculo);
		registroExistente.ifPresent(registro -> {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_YA_ESTA_ADENTRO);
		});

		Long cantidadVehiculos = getVehiculoRepository(vehiculo.getClass()).getCantidadVehiculosRegistrados();
		String claveCantidadMaximaVehiculo = PropiedadUtil.getClaveConComodin(
				vehiculo.getClass().getSimpleName().toLowerCase(), PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		Integer cantidadMaximaPermitidad = getPropiedadService().getPropertyAsInt(claveCantidadMaximaVehiculo);
		if (cantidadVehiculos >= cantidadMaximaPermitidad) {
			throw new BusinessException(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS);
		}

		getPlacaValidator().validate(vehiculo.getPlaca());

		Registro registro = new Registro(vehiculo, getDateProvider().getCurrentLocalDateTime());
		registro = getRegistroRepository().save(registro);
		return registro;
	}

	@Override
	public Optional<Registro> registrarSalida(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO);
		}
		Optional<Registro> registroOptional = getRegistroRepository().findByVehiculoAndHoraSalidaIsNull(vehiculo);
		registroOptional.ifPresent(registro -> {
			registro.setHoraSalida(dateProvider.getCurrentLocalDateTime());
			registro.setValor(calcularValor(vehiculo, registro.getHoraIngreso(), registro.getHoraSalida()));
			getRegistroRepository().save(registro);
		});
		return registroOptional;
	}

	@Override
	public BigDecimal calcularValor(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		String claveValorDia = PropiedadUtil.getClaveConComodin(vehiculo.getClass().getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_DIA_VEHICULO);
		BigDecimal valorDia = getPropiedadService().getPropertyAsBigDecimal(claveValorDia);
		String claveValorHora = PropiedadUtil.getClaveConComodin(vehiculo.getClass().getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_HORA_VEHICULO);
		BigDecimal valorHora = getPropiedadService().getPropertyAsBigDecimal(claveValorHora);

		Duration duration = getCalculadorTiempoService().calcularTiempo(fechaIngreso, fechaSalida);

		long numeroDias = duration.toDays();
		duration = duration.minusDays(numeroDias);
		long numeroHoras = duration.toHours();
		BigDecimal valorTotal = valorDia.multiply(BigDecimal.valueOf(numeroDias));
		valorTotal = valorTotal.add(valorHora.multiply(BigDecimal.valueOf(numeroHoras)));

		for (SurchargeStrategy surchargeStrategy : getSurchargeStrategies()) {
			if (surchargeStrategy.canApply(vehiculo)) {
				valorTotal = surchargeStrategy.compute(valorTotal, vehiculo);
			}
		}
		return valorTotal;
	}

	@Override
	public List<Registro> getRegistrosPendientes() {
		return getRegistroRepository().getRegistrosEntrada();
	}
	
	@Override
	public List<Registro> getRegistrosHistoricos() {
		return getRegistroRepository().getRegistrosHistoricos();
	}

}

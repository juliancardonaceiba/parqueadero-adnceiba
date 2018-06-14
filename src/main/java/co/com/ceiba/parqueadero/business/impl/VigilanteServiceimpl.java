package co.com.ceiba.parqueadero.business.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.PicoPlacaValidator;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.RegistroRepository;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;
import co.com.ceiba.parqueadero.util.DateProvider;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@Service
public class VigilanteServiceimpl implements VigilanteService {

	private VehiculoRepository vehiculoRepository;

	private PropiedadService propiedadService;

	private PicoPlacaValidator placaValidator;

	private RegistroRepository registroRepository;

	private DateProvider dateProvider;

	protected VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
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

	@Override
	public Registro registrarEntrada(Vehiculo vehiculo) {
		if(vehiculo==null) {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO);
		}
		Long cantidadVehiculos = getVehiculoRepository().contarCantidadVehiculos(vehiculo.getClass());
		String claveCantidadMaximaVehiculo = PropiedadUtil.getClaveConComodin(
				vehiculo.getClass().getSimpleName().toLowerCase(), PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		Integer cantidadMaximaPermitidad = getPropiedadService().getPropertyAsInt(claveCantidadMaximaVehiculo);
		if (cantidadVehiculos >= cantidadMaximaPermitidad) {
			throw new BusinessException(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS);
		}
		if (!getPlacaValidator().validate(vehiculo.getPlaca())) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA);
		}
		Registro registro = new Registro(vehiculo, getDateProvider().getCurrentLocalDateTime());
		registro = getRegistroRepository().save(registro);
		return registro;
	}

	@Override
	public Registro registrarSalida(Vehiculo vehiculo) {
		return null;
	}

	@Override
	public BigDecimal calcularValor(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		// TODO Auto-generated method stub
		return null;
	}

}

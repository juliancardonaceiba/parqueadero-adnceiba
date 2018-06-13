package co.com.ceiba.parqueadero.business.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.PlacaValidator;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@Service
public class VigilanteServiceimpl implements VigilanteService {

	private VehiculoRepository vehiculoRepository;

	private PropiedadService propiedadService;

	private PlacaValidator placaValidator;

	protected VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	protected PropiedadService getPropiedadService() {
		return propiedadService;
	}

	@Autowired
	public void setPropiedadService(PropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	protected PlacaValidator getPlacaValidator() {
		return placaValidator;
	}

	@Autowired
	public void setPlacaValidator(PlacaValidator placaValidator) {
		this.placaValidator = placaValidator;
	}

	@Override
	public Registro registrarEntrada(Vehiculo vehiculo) {
		Long cantidadVehiculos = getVehiculoRepository().contarCantidadVehiculos(vehiculo.getClass());
		Integer cantidadMaximaPermitidad = getPropiedadService().getPropertyAsInt(PropiedadUtil.getClaveConComodin(
				vehiculo.getClass().getSimpleName().toLowerCase(), PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO));
		if (cantidadVehiculos >= cantidadMaximaPermitidad) {
			throw new BusinessException(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS);
		}
		if (!placaValidator.validate(vehiculo.getPlaca())) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA);
		}
		return null;
	}

	@Override
	public Registro registrarSalida(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calcularValor(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		// TODO Auto-generated method stub
		return null;
	}

}

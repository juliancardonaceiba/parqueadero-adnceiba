package co.com.ceiba.parqueadero.business.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

public class VehiculoServiceImpl<T extends Vehiculo> implements VehiculoService<T> {

	private VehiculoRepository vehiculoRepository;

	private VehiculoValidator<T> vehiculoValidator;

	protected VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	public VehiculoValidator<T> getVehiculoValidator() {
		return vehiculoValidator;
	}

	@Autowired
	public void setVehiculoValidator(VehiculoValidator<T> vehiculoValidator) {
		this.vehiculoValidator = vehiculoValidator;
	}

	@Override
	public Optional<Vehiculo> getVehiculo(String placa) {
		if (StringUtils.isBlank(placa)) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_ES_REQUERIDA);
		}
		return getVehiculoRepository().findByPlaca(placa);
	}

	@Override
	public T crearVehiculo(T vehiculo) {
		getVehiculoValidator().validate(vehiculo);
		return getVehiculoRepository().save(vehiculo);
	}

}

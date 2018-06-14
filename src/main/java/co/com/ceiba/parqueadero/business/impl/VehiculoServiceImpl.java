package co.com.ceiba.parqueadero.business.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

public class VehiculoServiceImpl<T extends Vehiculo> implements VehiculoService<T> {

	private VehiculoRepository vehiculoRepository;

	protected VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	public Optional<Vehiculo> getVehiculo(String placa) {
		validarPlaca(placa);
		return getVehiculoRepository().findByPlaca(placa);
	}

	@Override
	public T crearVehiculo(T vehiculo) {
		validarVehiculo(vehiculo);
		return getVehiculoRepository().save(vehiculo);
	}

	protected void validarPlaca(String placa) {
		if (StringUtils.isBlank(placa)) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_ES_REQUERIDA);
		}
	}

	protected void validarVehiculo(T vehiculo) {
		if (vehiculo == null) {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO);
		}
		validarPlaca(vehiculo.getPlaca());
	}

}

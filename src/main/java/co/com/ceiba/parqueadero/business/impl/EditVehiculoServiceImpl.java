package co.com.ceiba.parqueadero.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.business.EditVehiculoService;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public abstract class EditVehiculoServiceImpl<T extends Vehiculo> extends ReadVehiculoServiceImpl<T>
		implements EditVehiculoService<T> {

	private VehiculoValidator<T> vehiculoValidator;

	public VehiculoValidator<T> getVehiculoValidator() {
		return vehiculoValidator;
	}

	@Autowired
	public void setVehiculoValidator(VehiculoValidator<T> vehiculoValidator) {
		this.vehiculoValidator = vehiculoValidator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T crearVehiculo(Vehiculo vehiculo) {
		T vehiculoGeneric = (T) vehiculo;
		getVehiculoValidator().validate(vehiculoGeneric);
		return getVehiculoRepository().save(vehiculoGeneric);
	}

}

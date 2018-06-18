package co.com.ceiba.parqueadero.business.validation;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface VehiculoValidator<T extends Vehiculo> {

	public void validate(T vehiculo);
}

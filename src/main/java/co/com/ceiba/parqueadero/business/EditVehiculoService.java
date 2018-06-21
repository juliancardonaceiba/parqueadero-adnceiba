package co.com.ceiba.parqueadero.business;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface EditVehiculoService<T extends Vehiculo> extends ReadVehiculoService<T> {

	public T crearVehiculo(Vehiculo vehiculo);
}

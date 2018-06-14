package co.com.ceiba.parqueadero.business;

import java.util.Optional;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface VehiculoService<T extends Vehiculo> {
	
	public Optional<Vehiculo> getVehiculo(String placa);
	
	public T crearVehiculo(T vehiculo);
}

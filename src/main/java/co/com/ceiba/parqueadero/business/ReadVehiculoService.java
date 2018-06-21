package co.com.ceiba.parqueadero.business;

import java.util.Optional;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface ReadVehiculoService<T extends Vehiculo> {
	
	public Optional<T> getVehiculoPorPlaca(String placa);
}

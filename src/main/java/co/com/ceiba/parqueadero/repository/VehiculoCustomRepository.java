package co.com.ceiba.parqueadero.repository;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface VehiculoCustomRepository {

	public Long contarCantidadVehiculos(Class<? extends Vehiculo> tipoVehiculo);
}

package co.com.ceiba.parqueadero.repository;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface AbstractVehiculoCustomRepository<T extends Vehiculo> {

	public Long getCantidadVehiculosRegistrados();	
}

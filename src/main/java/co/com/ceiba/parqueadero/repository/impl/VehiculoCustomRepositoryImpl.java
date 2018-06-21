package co.com.ceiba.parqueadero.repository.impl;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoCustomRepository;

public class VehiculoCustomRepositoryImpl extends AbstractVehiculoCustomRepositoryImpl<Vehiculo> implements VehiculoCustomRepository {

	public VehiculoCustomRepositoryImpl() {
		super(Vehiculo.class);
	}

}

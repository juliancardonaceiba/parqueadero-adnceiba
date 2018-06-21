package co.com.ceiba.parqueadero.repository.impl;

import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.repository.CarroCustomRepository;

public class CarroCustomRepositoryImpl extends AbstractVehiculoCustomRepositoryImpl<Carro> implements CarroCustomRepository {

	public CarroCustomRepositoryImpl() {
		super(Carro.class);
	}

}

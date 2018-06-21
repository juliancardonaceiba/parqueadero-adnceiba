package co.com.ceiba.parqueadero.repository.impl;

import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.repository.MotoCustomRepository;

public class MotoCustomRepositoryImpl extends AbstractVehiculoCustomRepositoryImpl<Moto> implements MotoCustomRepository {

	public MotoCustomRepositoryImpl() {
		super(Moto.class);
	}

}

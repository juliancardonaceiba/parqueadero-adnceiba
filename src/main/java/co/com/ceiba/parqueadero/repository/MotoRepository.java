package co.com.ceiba.parqueadero.repository;

import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Moto;

@Repository
public interface MotoRepository extends AbstractVehiculoRepository<Moto>, MotoCustomRepository {

}

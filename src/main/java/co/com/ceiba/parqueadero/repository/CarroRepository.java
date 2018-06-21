package co.com.ceiba.parqueadero.repository;

import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Carro;

@Repository
public interface CarroRepository extends AbstractVehiculoRepository<Carro>, CarroCustomRepository {

}

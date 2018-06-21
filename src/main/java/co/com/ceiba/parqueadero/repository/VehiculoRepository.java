package co.com.ceiba.parqueadero.repository;

import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@Repository
public interface VehiculoRepository extends AbstractVehiculoRepository<Vehiculo>, VehiculoCustomRepository {

}

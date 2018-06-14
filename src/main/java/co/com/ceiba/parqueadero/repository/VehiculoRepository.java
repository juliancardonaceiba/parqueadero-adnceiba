package co.com.ceiba.parqueadero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>, VehiculoCustomRepository {

	public Optional<Vehiculo> findByPlaca(String placa);
	
}

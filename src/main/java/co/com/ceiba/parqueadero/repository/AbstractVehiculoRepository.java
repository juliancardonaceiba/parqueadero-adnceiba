package co.com.ceiba.parqueadero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@NoRepositoryBean
public interface AbstractVehiculoRepository<T extends Vehiculo>
		extends JpaRepository<T, Long>, AbstractVehiculoCustomRepository<T> {

	public Optional<T> findByPlaca(String placa);
	
}

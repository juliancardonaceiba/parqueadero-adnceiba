package co.com.ceiba.parqueadero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>, RegistroCustomRepository {

	public Optional<Registro> findByVehiculoAndHoraSalidaIsNull(Vehiculo vehiculo);
}

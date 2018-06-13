package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

}

package co.com.ceiba.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.domain.model.Recargo;

@Repository
public interface RecargoRepository extends JpaRepository<Recargo, Long>, RecargoCustomRepository {

}

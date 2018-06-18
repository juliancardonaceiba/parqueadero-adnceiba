package co.com.ceiba.parqueadero.repository;

import java.util.Optional;

import co.com.ceiba.parqueadero.domain.model.Recargo;

public interface RecargoCustomRepository {

	public Optional<Recargo> getRecargoParaCilindraje(Integer cilidrajeMinimo);

}

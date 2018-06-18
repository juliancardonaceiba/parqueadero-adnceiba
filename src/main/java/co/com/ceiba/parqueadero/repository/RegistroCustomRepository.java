package co.com.ceiba.parqueadero.repository;

import java.util.List;

import co.com.ceiba.parqueadero.domain.model.Registro;

public interface RegistroCustomRepository {

	public List<Registro> getRegistrosEntrada();
}

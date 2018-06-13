package co.com.ceiba.parqueadero.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface VigilanteService {

	public Registro registrarEntrada(Vehiculo vehiculo);

	public Registro registrarSalida(Vehiculo vehiculo);

	public BigDecimal calcularValor(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida);	
}

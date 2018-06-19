package co.com.ceiba.parqueadero.business;

import java.time.Duration;
import java.time.LocalDateTime;

public interface CalculadorTiempoService {

	public Duration calcularTiempo(LocalDateTime fechaIngreso, LocalDateTime fechaSalida);
}

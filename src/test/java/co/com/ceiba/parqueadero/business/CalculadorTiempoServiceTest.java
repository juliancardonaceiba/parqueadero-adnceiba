package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.impl.CalculadorTiempoServiceImpl;
import co.com.ceiba.parqueadero.util.PropiedadConstants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CalculadorTiempoServiceTest {

	private static final Long NUMERO_HORAS_INICIO_COBRO_DIA = 9L;
	private static final LocalDateTime FECHA_INICIO_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8, 0);
	private static final LocalDateTime FECHA_FIN_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 16, 30);
	private static final Duration DURACION_NUEVE_HORAS = Duration.ofDays(1);
	private static final LocalDateTime FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8,
			0);
	private static final LocalDateTime FECHA_FIN_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 2, 10, 3);
	private static final Duration DURACION_UN_DIA_TRES_HORAS = Duration.ofDays(1).plusHours(3);

	@Mock
	private PropiedadService propiedadService;

	@InjectMocks
	private CalculadorTiempoService calculadorTiempoService = new CalculadorTiempoServiceImpl();

	@Test
	public void calcularTiempoUnDiaTresHorasTest() {
		// Arrange
		when(propiedadService.getPropertyAsLong(PropiedadConstants.NUMERO_HORAS_INICIO_COBRO_DIA))
				.thenReturn(NUMERO_HORAS_INICIO_COBRO_DIA);
		// Act
		Duration duration = calculadorTiempoService.calcularTiempo(FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(DURACION_UN_DIA_TRES_HORAS, duration);
		verify(propiedadService).getPropertyAsLong(PropiedadConstants.NUMERO_HORAS_INICIO_COBRO_DIA);

	}
	
	@Test
	public void calcularTiempoNueveHorasTest() {
		// Arrange
		when(propiedadService.getPropertyAsLong(PropiedadConstants.NUMERO_HORAS_INICIO_COBRO_DIA))
				.thenReturn(NUMERO_HORAS_INICIO_COBRO_DIA);
		// Act
		Duration duration = calculadorTiempoService.calcularTiempo(FECHA_INICIO_COBRO_NUEVE_HORAS,
				FECHA_FIN_COBRO_NUEVE_HORAS);
		// Assert
		assertEquals(DURACION_NUEVE_HORAS, duration);
		verify(propiedadService).getPropertyAsLong(PropiedadConstants.NUMERO_HORAS_INICIO_COBRO_DIA);

	}

}

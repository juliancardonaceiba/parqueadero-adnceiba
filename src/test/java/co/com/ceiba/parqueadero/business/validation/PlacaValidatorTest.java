package co.com.ceiba.parqueadero.business.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.validation.impl.PlacaValidatorImpl;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlacaValidatorTest {

	private static final String PLACA_VALIDA_SUNDAY = "AJH856";
	private static final String PLACA_INVALIDA_SUNDAY = "MFD856";

	@Mock
	private PropiedadService propiedadService;

	@InjectMocks
	private PlacaValidator placaValidator = new PlacaValidatorImpl();

	@Test
	public void validarPlacaInvalidaEnDiaActual() {
		// Arrange
		LocalDateTime fechaSunday = LocalDateTime.of(2018, Month.JUNE, 3, 0, 0);
		Clock clockMock = Clock.fixed(fechaSunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		((PlacaValidatorImpl) placaValidator).setClock(clockMock);
		String clavePlacasPermitidasDiaActual = PropiedadUtil.getClaveConComodin(DayOfWeek.SUNDAY.name().toLowerCase(),
				PropiedadConstants.INICIALES_PLACAS_PERMITIDAS_POR_DIA);
		when(propiedadService.getPropertyAsList(clavePlacasPermitidasDiaActual))
				.thenReturn(Arrays.asList(String.valueOf(PLACA_VALIDA_SUNDAY.charAt(0))));
		// Act
		boolean placaValida = placaValidator.validate(PLACA_INVALIDA_SUNDAY);
		// Assert
		assertFalse(placaValida);
	}
	
	@Test
	public void validarPlacaValidaEnDiaActual() {
		// Arrange
		LocalDateTime fechaSunday = LocalDateTime.of(2018, Month.JUNE, 3, 0, 0);
		Clock clockMock = Clock.fixed(fechaSunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		((PlacaValidatorImpl) placaValidator).setClock(clockMock);
		String clavePlacasPermitidasDiaActual = PropiedadUtil.getClaveConComodin(DayOfWeek.SUNDAY.name().toLowerCase(),
				PropiedadConstants.INICIALES_PLACAS_PERMITIDAS_POR_DIA);
		when(propiedadService.getPropertyAsList(clavePlacasPermitidasDiaActual))
				.thenReturn(Arrays.asList(String.valueOf(PLACA_VALIDA_SUNDAY.charAt(0))));
		// Act
		boolean placaValida = placaValidator.validate(PLACA_VALIDA_SUNDAY);
		// Assert
		assertTrue(placaValida);
	}

}

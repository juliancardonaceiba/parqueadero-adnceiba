package co.com.ceiba.parqueadero.business.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.validation.impl.PlacaValidatorImpl;
import co.com.ceiba.parqueadero.util.DateProvider;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlacaValidatorTest {

	private static final String PLACA_VALIDA_SUNDAY = "AJH856";
	private static final String PLACA_INVALIDA_SUNDAY = "MFD856";

	@Mock
	private PropiedadService propiedadService;
	
	@Mock
	private DateProvider dateProvider;

	@InjectMocks
	private PlacaValidator placaValidator = new PlacaValidatorImpl();

	@Test
	public void validarPlacaInvalidaEnDiaActual() {
		// Arrange
		LocalDate fechaSaturday = LocalDate.of(2018, Month.JUNE, 2);
		when(dateProvider.getCurrentLocalDate()).thenReturn(fechaSaturday);
		String clavePlacasNoPermitidasDiaActual = PropiedadUtil.getClaveConComodin(DayOfWeek.SATURDAY.name().toLowerCase(),
				PropiedadConstants.INICIALES_PLACAS_NO_PERMITIDAS_POR_DIA);
		when(propiedadService.getPropertyAsList(clavePlacasNoPermitidasDiaActual))
				.thenReturn(Arrays.asList(String.valueOf(PLACA_VALIDA_SUNDAY.charAt(0))));
		// Act
		boolean placaValida = placaValidator.validate(PLACA_VALIDA_SUNDAY);
		// Assert
		assertFalse(placaValida);
		verify(dateProvider).getCurrentLocalDate();
		verify(propiedadService).getPropertyAsList(clavePlacasNoPermitidasDiaActual);
	}
	
	@Test
	public void validarPlacaValidaEnDiaActual() {
		// Arrange
		LocalDate fechaSunday = LocalDate.of(2018, Month.JUNE, 3);
		when(dateProvider.getCurrentLocalDate()).thenReturn(fechaSunday);
		String clavePlacasPermitidasDiaActual = PropiedadUtil.getClaveConComodin(DayOfWeek.SUNDAY.name().toLowerCase(),
				PropiedadConstants.INICIALES_PLACAS_NO_PERMITIDAS_POR_DIA);
		when(propiedadService.getPropertyAsList(clavePlacasPermitidasDiaActual))
				.thenReturn(Arrays.asList(String.valueOf(PLACA_INVALIDA_SUNDAY.charAt(0))));
		// Act
		boolean placaValida = placaValidator.validate(PLACA_VALIDA_SUNDAY);
		// Assert
		assertTrue(placaValida);
		verify(dateProvider).getCurrentLocalDate();
		verify(propiedadService).getPropertyAsList(clavePlacasPermitidasDiaActual);
	}

}

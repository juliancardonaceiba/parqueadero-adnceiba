package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import co.com.ceiba.parqueadero.business.impl.PropiedadServiceImpl;
import co.com.ceiba.parqueadero.util.PropiedadConstants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PropiedadServiceTest {

	private static final String CADENA_ANTIDAD_MAXIMA_VEHICULO="10";
	
	@Mock
	private Environment environment;
	
	@InjectMocks
	private PropiedadService propiedadService = new PropiedadServiceImpl();
	
	@Test
	public void obtenerParametroComoCadenaTest() {
		//arrange
		when(environment.getProperty(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO)).thenReturn(CADENA_ANTIDAD_MAXIMA_VEHICULO);
		//act
		String valor = propiedadService.getProperty(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		//assert		
		assertEquals(CADENA_ANTIDAD_MAXIMA_VEHICULO, valor);
	}
	
	@Test
	public void obtenerParametroComoIntegerTest() {
		//arrange
		when(environment.getProperty(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO)).thenReturn(CADENA_ANTIDAD_MAXIMA_VEHICULO);
		//act
		Integer valor = propiedadService.getPropertyAsInt(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		//assert
		assertEquals(CADENA_ANTIDAD_MAXIMA_VEHICULO, String.valueOf(valor));
	}
	
	@Test
	public void obtenerParametroComoLongTest() {
		//arrange
		when(environment.getProperty(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO)).thenReturn(CADENA_ANTIDAD_MAXIMA_VEHICULO);
		//act
		Long valor = propiedadService.getPropertyAsLong(PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		//assert
		assertEquals(CADENA_ANTIDAD_MAXIMA_VEHICULO, String.valueOf(valor));
	}
}

package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.impl.CarroServiceImpl;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class CarroServiceITest {

	@Autowired
	private CarroService carroService = new CarroServiceImpl();

	@Test
	public void obtenerCarroConPlacaNullTest() {
		// Arrange
		boolean mensajePlacaEsRequerida = Boolean.FALSE;
		// Act
		try {
			carroService.getVehiculo(null);
		} catch (BusinessException e) {
			mensajePlacaEsRequerida = ExceptionConstants.MSG_PLACA_ES_REQUERIDA.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajePlacaEsRequerida);
	}

	@Test
	public void crearCarroConCarroNullTest() {
		// Arrange
		boolean mensajeVehiculoEsRequerida = Boolean.FALSE;
		// Act
		try {
			carroService.crearVehiculo(null);
		} catch (Exception e) {
			mensajeVehiculoEsRequerida = ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajeVehiculoEsRequerida);
	}

	@Test
	public void crearCarroConPlacaNullTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().withPlaca(null).build();
		boolean mensajePlacaEsRequerida = Boolean.FALSE;
		// Act
		try {
			carroService.crearVehiculo(carro);
		} catch (Exception e) {
			mensajePlacaEsRequerida = ExceptionConstants.MSG_PLACA_ES_REQUERIDA.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajePlacaEsRequerida);
	}

	@Test
	public void crearCarroConCarroOKTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		// Act
		Carro nuevoVehiculo = carroService.crearVehiculo(carro);
		// Assert
		assertTrue(nuevoVehiculo != null && nuevoVehiculo.getId() != null);
	}

}

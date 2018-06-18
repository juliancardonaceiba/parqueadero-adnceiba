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
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class MotoServiceITest {

	@Autowired
	private MotoService motoService;

	@Test
	public void obtenerMotoConPlacaNullTest() {
		// Arrange
		boolean mensajePlacaEsRequerida = Boolean.FALSE;
		// Act
		try {
			motoService.getVehiculo(null);
		} catch (BusinessException e) {
			mensajePlacaEsRequerida = ExceptionConstants.MSG_PLACA_ES_REQUERIDA.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajePlacaEsRequerida);
	}


	@Test
	public void crearMotoConMotoNullTest() {
		// Arrange
		boolean mensajeVehiculoEsRequerido = Boolean.FALSE;
		// Act
		try {
			motoService.crearVehiculo(null);
		} catch (Exception e) {
			mensajeVehiculoEsRequerido = ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajeVehiculoEsRequerido);
	}

	@Test
	public void crearMotoConPlacaNullTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withPlaca(null).build();
		boolean mensajePlacaEsRequerida = Boolean.FALSE;
		// Act
		try {
			motoService.crearVehiculo(moto);
		} catch (Exception e) {
			mensajePlacaEsRequerida = ExceptionConstants.MSG_PLACA_ES_REQUERIDA.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajePlacaEsRequerida);
	}

	@Test
	public void crearMotoConCilindrajeNullTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withCilindraje(null).build();
		boolean mensajeCilindrajeEsRequerido = Boolean.FALSE;
		// Act
		try {
			motoService.crearVehiculo(moto);
		} catch (Exception e) {
			mensajeCilindrajeEsRequerido = ExceptionConstants.MSG_CILINDRAJE_ES_REQUERIDO.equals(e.getMessage());
		}
		// Assert
		assertTrue(mensajeCilindrajeEsRequerido);
	}

	@Test
	public void crearMotoConMotoOKTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		Moto nuevoVehiculo = motoService.crearVehiculo(moto);
		// Assert
		assertTrue(nuevoVehiculo != null && nuevoVehiculo.getId() != null);
	}

}

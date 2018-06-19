package co.com.ceiba.parqueadero.business.validation.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotoValidatorTest {

	@Autowired
	private VehiculoValidator<Moto> motoValidator;

	@Test
	public void validarMotoNullTest() {
		// Arrange
		Moto moto = null;
		String mensajeVehiculoEsRequerido = null;
		// Act
		try {
			motoValidator.validate(moto);
		} catch (Exception e) {
			mensajeVehiculoEsRequerido = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO, mensajeVehiculoEsRequerido);
	}

	@Test
	public void validarMotoConPlacaNullTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withPlaca(null).build();
		String mensajePlacaEsRequerida = null;
		// Act
		try {
			motoValidator.validate(moto);
		} catch (Exception e) {
			mensajePlacaEsRequerida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_PLACA_ES_REQUERIDA, mensajePlacaEsRequerida);
	}
	
	@Test
	public void validarMotoConCilindrajeNullTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withCilindraje(null).build();
		String mensajePlacaEsRequerida = null;
		// Act
		try {
			motoValidator.validate(moto);
		} catch (Exception e) {
			mensajePlacaEsRequerida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_CILINDRAJE_ES_REQUERIDO, mensajePlacaEsRequerida);
	}

}

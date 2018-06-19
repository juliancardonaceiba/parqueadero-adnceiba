package co.com.ceiba.parqueadero.business.validation.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroValidatorTest {

	@Autowired
	private VehiculoValidator<Carro> carroValidator;

	@Test
	public void validarCarroNullTest() {
		// Arrange
		Carro carro = null;
		String mensajeVehiculoEsRequerido = null;
		// Act
		try {
			carroValidator.validate(carro);
		} catch (Exception e) {
			mensajeVehiculoEsRequerido = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO, mensajeVehiculoEsRequerido);
	}

	@Test
	public void validarCarroConPlacaNullTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().withPlaca(null).build();
		String mensajePlacaEsRequerida = null;
		// Act
		try {
			carroValidator.validate(carro);
		} catch (Exception e) {
			mensajePlacaEsRequerida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_PLACA_ES_REQUERIDA, mensajePlacaEsRequerida);
	}

}

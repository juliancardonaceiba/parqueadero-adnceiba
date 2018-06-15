package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
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
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class VigilanteServiceITest {

	private static final Long CANTIDAD_MAXIMA_CARROS_PERMITIDOS = 20L;
	private static final Long CANTIDAD_MAXIMA_MOTOS_PERMITIDOS = 10L;

	private static final String LETRAS_PLACA = "XYZ";

	@Autowired
	private VigilanteService vigilanteService;

	@Test
	public void registarEntradaConCantidadMaximaCarrosTest() {
		// Arrange
		boolean cantidadMaximaCarrosExcedida = Boolean.FALSE;
		Carro carro = new CarroTestDataBuilder().build();
		for (int i = 0; i < CANTIDAD_MAXIMA_CARROS_PERMITIDOS; i++) {
			Carro carroTest = new CarroTestDataBuilder().withPlaca(LETRAS_PLACA + i).build();
			vigilanteService.registrarEntrada(carroTest);
		}
		// Act
		try {
			vigilanteService.registrarEntrada(carro);
		} catch (BusinessException e) {
			cantidadMaximaCarrosExcedida = ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS.equals(e.getMessage());
		}
		// Assert
		assertTrue(cantidadMaximaCarrosExcedida);
	}

	@Test
	public void registarEntradaConCantidadMaximaMotosTest() {
		// Arrange
		String mensjaeCantidadMaximaMotosExcedida = null;
		Moto moto = new MotoTestDataBuilder().build();
		for (int i = 0; i < CANTIDAD_MAXIMA_MOTOS_PERMITIDOS; i++) {
			Moto motoTest = new MotoTestDataBuilder().withPlaca(LETRAS_PLACA + i).build();
			vigilanteService.registrarEntrada(motoTest);
		}
		// Act
		try {
			vigilanteService.registrarEntrada(moto);
		} catch (BusinessException e) {
			mensjaeCantidadMaximaMotosExcedida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS, mensjaeCantidadMaximaMotosExcedida);
	}

	// @Test
	// public void registarEntradaConPlacaNoPermitidaEnElDiaTest() {
	// // Arrange
	// String mensjaePlacaNoPermitida = null;
	// Moto moto = new MotoTestDataBuilder().build();
	// // Act
	// try {
	// vigilanteService.registrarEntrada(moto);
	// } catch (BusinessException e) {
	// mensjaePlacaNoPermitida = e.getMessage();
	// }
	// // Assert
	// assertEquals(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA,
	// mensjaePlacaNoPermitida);
	// }

	@Test
	public void registarEntradaMotoOkTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		Registro registroPersistent = vigilanteService.registrarEntrada(moto);
		boolean registroGuardado = registroPersistent != null && registroPersistent.getId() != null;
		// Assert
		assertTrue(registroGuardado);
	}
}

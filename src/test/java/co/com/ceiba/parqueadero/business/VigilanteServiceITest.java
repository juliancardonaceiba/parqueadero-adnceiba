package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

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
import co.com.ceiba.parqueadero.domain.model.Recargo;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.RecargoTestDataBuilder;
import co.com.ceiba.parqueadero.repository.RecargoRepository;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class VigilanteServiceITest {

	private static final Long CANTIDAD_MAXIMA_CARROS_PERMITIDOS = 20L;
	private static final Long CANTIDAD_MAXIMA_MOTOS_PERMITIDOS = 10L;

	private static final String LETRAS_PLACA = "XYZ";
	private static final String PLACA_NO_PERMITIDA = "156PQW";

	private static final BigDecimal VALOR_DIA_MOTO = BigDecimal.valueOf(4000);
	private static final BigDecimal VALOR_UN_DIA_TRES_HORAS_CARRO = BigDecimal.valueOf(11000);
	private static final BigDecimal VALOR_UN_DIA_TRES_HORAS_MOTO = BigDecimal.valueOf(5500);
	private static final LocalDateTime FECHA_INICIO_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8, 0);
	private static final LocalDateTime FECHA_FIN_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 17, 0);
	private static final LocalDateTime FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8,
			0);
	private static final LocalDateTime FECHA_FIN_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 2, 11, 0);

	@Autowired
	private VigilanteService vigilanteService;

	@Autowired
	private EditVehiculoService<Carro> carroService;

	@Autowired
	private EditVehiculoService<Moto> motoService;

	@Autowired
	private RecargoRepository recargoRepository;

	@Test
	public void registarEntradaConCantidadMaximaCarrosTest() {
		// Arrange
		boolean cantidadMaximaCarrosExcedida = Boolean.FALSE;
		Carro carro = new CarroTestDataBuilder().build();
		carro = carroService.crearVehiculo(carro);
		for (int i = 0; i < CANTIDAD_MAXIMA_CARROS_PERMITIDOS; i++) {
			Carro carroTest = new CarroTestDataBuilder().withPlaca(LETRAS_PLACA + i).build();
			carroTest = carroService.crearVehiculo(carroTest);
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
		moto = motoService.crearVehiculo(moto);
		for (int i = 0; i < CANTIDAD_MAXIMA_MOTOS_PERMITIDOS; i++) {
			Moto motoTest = new MotoTestDataBuilder().withPlaca(LETRAS_PLACA + i).build();
			motoTest = motoService.crearVehiculo(motoTest);
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

	@Test
	public void registarEntradaConPlacaNoPermitidaEnElDiaTest() {
		// Arrange
		String mensjaePlacaNoPermitida = null;
		Moto moto = new MotoTestDataBuilder().withId(Long.MAX_VALUE).withPlaca(PLACA_NO_PERMITIDA).build();
		// Act
		try {
			vigilanteService.registrarEntrada(moto);
		} catch (BusinessException e) {
			mensjaePlacaNoPermitida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA, mensjaePlacaNoPermitida);
	}

	@Test
	public void registarEntradaMotoOkTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		moto = motoService.crearVehiculo(moto);
		// Act
		Registro registroPersistent = vigilanteService.registrarEntrada(moto);
		boolean registroGuardado = registroPersistent != null && registroPersistent.getId() != null;
		// Assert
		assertTrue(registroGuardado);
	}

	@Test
	public void registarEntradaCarroOkTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		carro = carroService.crearVehiculo(carro);
		// Act
		Registro registroPersistent = vigilanteService.registrarEntrada(carro);
		boolean registroGuardado = registroPersistent != null && registroPersistent.getId() != null;
		// Assert
		assertTrue(registroGuardado);
	}

	@Test
	public void calcularValorMotoParaNueveHorasTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		BigDecimal valor = vigilanteService.calcularValor(moto, FECHA_INICIO_COBRO_NUEVE_HORAS,
				FECHA_FIN_COBRO_NUEVE_HORAS);
		// Assert
		assertEquals(VALOR_DIA_MOTO.compareTo(valor), BigDecimal.ZERO.intValue());
	}

	@Test
	public void calcularValorCarroUnDiaTresHorasTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		// Act
		BigDecimal valor = vigilanteService.calcularValor(carro, FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(VALOR_UN_DIA_TRES_HORAS_CARRO.compareTo(valor), BigDecimal.ZERO.intValue());
	}

	@Test
	public void calcularValorMotoUnDiaTresHorasTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		BigDecimal valor = vigilanteService.calcularValor(moto, FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(VALOR_UN_DIA_TRES_HORAS_MOTO.compareTo(valor), BigDecimal.ZERO.intValue());
	}

	@Test
	public void calcularValorMotoConRecargoUnDiaTresHorasTest() {
		// Arrange
		Recargo recargo = new RecargoTestDataBuilder().build();
		Moto moto = new MotoTestDataBuilder().withCilindraje(recargo.getCilindrajeMinimo()).build();
		recargoRepository.save(recargo);
		// Act
		BigDecimal valor = vigilanteService.calcularValor(moto, FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(VALOR_UN_DIA_TRES_HORAS_MOTO.add(recargo.getValorRecargado()).compareTo(valor),
				BigDecimal.ZERO.intValue());
	}

}

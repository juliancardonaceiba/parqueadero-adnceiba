package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Lists;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.impl.VigilanteServiceImpl;
import co.com.ceiba.parqueadero.business.surcharge.SurchargeStrategy;
import co.com.ceiba.parqueadero.business.validation.PicoPlacaValidator;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.repository.RegistroRepository;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;
import co.com.ceiba.parqueadero.util.DateProvider;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class VigilanteServiceTest {

	private static final Long CANTIDAD_MAXIMA_CARROS_PERMITIDOS = 20L;
	private static final Long CANTIDAD_MAXIMA_MOTOS_PERMITIDOS = 10L;
	private static final BigDecimal VALOR_HORA_MOTO = BigDecimal.valueOf(500);
	private static final BigDecimal VALOR_DIA_MOTO = BigDecimal.valueOf(4000);
	private static final BigDecimal VALOR_HORA_CARRO = BigDecimal.valueOf(1000);
	private static final BigDecimal VALOR_DIA_CARRO = BigDecimal.valueOf(8000);
	private static final BigDecimal VALOR_UN_DIA_TRES_HORAS_CARRO = BigDecimal.valueOf(11000);
	private static final BigDecimal VALOR_UN_DIA_TRES_HORAS_MOTO = BigDecimal.valueOf(5500);
	private static final LocalDateTime FECHA_INICIO_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8, 0);
	private static final LocalDateTime FECHA_FIN_COBRO_NUEVE_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 17, 0);
	private static final Duration DURACION_NUEVE_HORAS = Duration.ofDays(1);
	private static final LocalDateTime FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 1, 8,
			0);
	private static final LocalDateTime FECHA_FIN_COBRO_UN_DIA_TRES_HORAS = LocalDateTime.of(2018, Month.JUNE, 2, 10, 3);
	private static final Duration DURACION_UN_DIA_TRES_HORAS = Duration.ofDays(1).plusHours(3);

	@Mock
	private VehiculoRepository vehiculoRepository;

	@Mock
	private RegistroRepository registroRepository;

	@Mock
	private PropiedadService propiedadService;

	@Mock
	private PicoPlacaValidator placaValidator;

	@Mock
	private DateProvider dateProvider;

	@Mock
	private CalculadorTiempoService calculadorTiempoService;

	@Mock
	private SurchargeStrategy motoSurchargeStrategy;

	@Spy
	private List<SurchargeStrategy> surchargeStrategies = Lists.newArrayList();

	@InjectMocks
	private VigilanteService vigilanteService = new VigilanteServiceImpl();

	@Before
	public void before() {
		surchargeStrategies.add(motoSurchargeStrategy);
	}

	@Test
	public void registarEntradaConCantidadMaximaCarrosTest() {
		// Arrange
		String mensjaecantidadMaximaCarrosExcedida = null;
		Carro carro = new CarroTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.contarCantidadVehiculos(Carro.class)).thenReturn(CANTIDAD_MAXIMA_CARROS_PERMITIDOS);
		String claveCantidadMaximaCarros = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaCarros))
				.thenReturn(CANTIDAD_MAXIMA_CARROS_PERMITIDOS.intValue());
		// Act
		try {
			vigilanteService.registrarEntrada(carro);
		} catch (BusinessException e) {
			mensjaecantidadMaximaCarrosExcedida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS, mensjaecantidadMaximaCarrosExcedida);
		verify(vehiculoRepository).contarCantidadVehiculos(Carro.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaCarros);
	}

	@Test
	public void registarEntradaConCantidadMaximaMotosTest() {
		// Arrange
		String mensjaeCantidadMaximaMotosExcedida = null;
		Moto moto = new MotoTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.contarCantidadVehiculos(Moto.class)).thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS);
		String claveCantidadMaximaMotos = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaMotos))
				.thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS.intValue());
		// Act
		try {
			vigilanteService.registrarEntrada(moto);
		} catch (BusinessException e) {
			mensjaeCantidadMaximaMotosExcedida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS, mensjaeCantidadMaximaMotosExcedida);
		verify(vehiculoRepository).contarCantidadVehiculos(Moto.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaMotos);
	}

	@Test
	public void registarEntradaConPlacaNoPermitidaEnElDiaTest() {
		// Arrange
		String mensjaePlacaNoPermitida = null;
		Moto moto = new MotoTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.contarCantidadVehiculos(Moto.class)).thenReturn(BigDecimal.ZERO.longValue());
		String claveCantidadMaximaMotos = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaMotos))
				.thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS.intValue());

		doThrow(new BusinessException(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA)).when(placaValidator)
				.validate(moto.getPlaca());
		// Act
		try {
			vigilanteService.registrarEntrada(moto);
		} catch (BusinessException e) {
			mensjaePlacaNoPermitida = e.getMessage();
		}
		// Assert
		assertEquals(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA, mensjaePlacaNoPermitida);
		verify(vehiculoRepository).contarCantidadVehiculos(Moto.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaMotos);
		verify(placaValidator).validate(moto.getPlaca());
	}

	@Test
	public void registarEntradaMotoOkTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.contarCantidadVehiculos(Moto.class)).thenReturn(BigDecimal.ZERO.longValue());
		String claveCantidadMaximaMotos = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaMotos))
				.thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS.intValue());
		doNothing().when(placaValidator).validate(moto.getPlaca());
		LocalDateTime fechaActual = LocalDateTime.of(2018, Month.JUNE, 5, 9, 0);
		when(dateProvider.getCurrentLocalDateTime()).thenReturn(fechaActual);
		Registro registro = new Registro(moto, fechaActual);
		registro.setId(Long.MAX_VALUE);
		when(registroRepository.save(notNull())).thenReturn(registro);
		// Act
		Registro registroPersistent = vigilanteService.registrarEntrada(moto);
		boolean registroGuardado = registroPersistent != null && registroPersistent.getId() != null;
		// Assert
		assertTrue(registroGuardado);
		verify(vehiculoRepository).contarCantidadVehiculos(Moto.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaMotos);
		verify(placaValidator).validate(moto.getPlaca());
		verify(dateProvider).getCurrentLocalDateTime();
		verify(registroRepository).save(registro);
	}

	@Test
	public void registarEntradaCarroOkTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.contarCantidadVehiculos(Carro.class)).thenReturn(BigDecimal.ZERO.longValue());
		String claveCantidadMaximaCarros = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaCarros))
				.thenReturn(CANTIDAD_MAXIMA_CARROS_PERMITIDOS.intValue());
		doNothing().when(placaValidator).validate(carro.getPlaca());
		LocalDateTime fechaActual = LocalDateTime.of(2018, Month.JUNE, 5, 9, 0);
		when(dateProvider.getCurrentLocalDateTime()).thenReturn(fechaActual);
		Registro registro = new Registro(carro, fechaActual);
		registro.setId(Long.MAX_VALUE);
		when(registroRepository.save(notNull())).thenReturn(registro);
		// Act
		Registro registroPersistent = vigilanteService.registrarEntrada(carro);
		boolean registroGuardado = registroPersistent != null && registroPersistent.getId() != null;
		// Assert
		assertTrue(registroGuardado);
		verify(vehiculoRepository).contarCantidadVehiculos(Carro.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaCarros);
		verify(placaValidator).validate(carro.getPlaca());
		verify(registroRepository).save(registro);
		verify(dateProvider).getCurrentLocalDateTime();
	}

	@Test
	public void calcularValorMotoParaNueveHorasTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		String claveValorDia = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_DIA_VEHICULO);
		String claveValorHora = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_HORA_VEHICULO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorDia)).thenReturn(VALOR_DIA_MOTO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorHora)).thenReturn(VALOR_HORA_MOTO);
		when(calculadorTiempoService.calcularTiempo(FECHA_INICIO_COBRO_NUEVE_HORAS, FECHA_FIN_COBRO_NUEVE_HORAS))
				.thenReturn(DURACION_NUEVE_HORAS);
		when(motoSurchargeStrategy.canApply(moto)).thenReturn(Boolean.FALSE);
		// Act
		BigDecimal valor = vigilanteService.calcularValor(moto, FECHA_INICIO_COBRO_NUEVE_HORAS,
				FECHA_FIN_COBRO_NUEVE_HORAS);
		// Assert
		assertEquals(VALOR_DIA_MOTO.compareTo(valor), BigDecimal.ZERO.intValue());
		verify(propiedadService).getPropertyAsBigDecimal(claveValorDia);
		verify(propiedadService).getPropertyAsBigDecimal(claveValorHora);
		verify(calculadorTiempoService).calcularTiempo(FECHA_INICIO_COBRO_NUEVE_HORAS,
				FECHA_FIN_COBRO_NUEVE_HORAS);
		verify(motoSurchargeStrategy).canApply(moto);
	}

	@Test
	public void calcularValorCarroUnDiaTresHorasTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		String claveValorDia = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_DIA_VEHICULO);
		String claveValorHora = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_HORA_VEHICULO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorDia)).thenReturn(VALOR_DIA_CARRO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorHora)).thenReturn(VALOR_HORA_CARRO);
		when(calculadorTiempoService.calcularTiempo(FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS)).thenReturn(DURACION_UN_DIA_TRES_HORAS);
		when(motoSurchargeStrategy.canApply(carro)).thenReturn(Boolean.FALSE);
		// Act

		BigDecimal valor = vigilanteService.calcularValor(carro, FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(VALOR_UN_DIA_TRES_HORAS_CARRO.compareTo(valor), BigDecimal.ZERO.intValue());
		verify(propiedadService).getPropertyAsBigDecimal(claveValorDia);
		verify(propiedadService).getPropertyAsBigDecimal(claveValorHora);
		verify(calculadorTiempoService).calcularTiempo(FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		verify(motoSurchargeStrategy).canApply(carro);
	}

	@Test
	public void calcularValorMotoUnDiaTresHorasTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		String claveValorDia = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_DIA_VEHICULO);
		String claveValorHora = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.VALOR_HORA_VEHICULO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorDia)).thenReturn(VALOR_DIA_MOTO);
		when(propiedadService.getPropertyAsBigDecimal(claveValorHora)).thenReturn(VALOR_HORA_MOTO);
		when(calculadorTiempoService.calcularTiempo(FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS)).thenReturn(DURACION_UN_DIA_TRES_HORAS);
		when(motoSurchargeStrategy.canApply(moto)).thenReturn(Boolean.FALSE);
		// Act
		BigDecimal valor = vigilanteService.calcularValor(moto, FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		// Assert
		assertEquals(VALOR_UN_DIA_TRES_HORAS_MOTO.compareTo(valor), BigDecimal.ZERO.intValue());
		verify(propiedadService).getPropertyAsBigDecimal(claveValorDia);
		verify(propiedadService).getPropertyAsBigDecimal(claveValorHora);
		verify(calculadorTiempoService).calcularTiempo(FECHA_INICIO_COBRO_UN_DIA_TRES_HORAS,
				FECHA_FIN_COBRO_UN_DIA_TRES_HORAS);
		verify(motoSurchargeStrategy).canApply(moto);
	}

}

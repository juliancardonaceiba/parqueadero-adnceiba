package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.impl.VigilanteServiceimpl;
import co.com.ceiba.parqueadero.business.validation.PlacaValidator;
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

	@Mock
	private VehiculoRepository vehiculoRepository;

	@Mock
	private RegistroRepository registroRepository;

	@Mock
	private PropiedadService propiedadService;

	@Mock
	private PlacaValidator placaValidator;
	
	@Mock
	private DateProvider dateProvider;

	@InjectMocks
	private VigilanteService vigilanteService = new VigilanteServiceimpl();

	@Test
	public void registarEntradaConCantidadMaximaCarros() {
		// Arrange
		boolean cantidadMaximaCarrosExcedida = Boolean.FALSE;
		Carro carro = new CarroTestDataBuilder().build();
		when(vehiculoRepository.contarCantidadVehiculos(Carro.class)).thenReturn(CANTIDAD_MAXIMA_CARROS_PERMITIDOS);
		String claveCantidadMaximaCarros = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaCarros))
				.thenReturn(CANTIDAD_MAXIMA_CARROS_PERMITIDOS.intValue());
		// Act
		try {
			vigilanteService.registrarEntrada(carro);
		} catch (BusinessException e) {
			cantidadMaximaCarrosExcedida = ExceptionConstants.MSG_CANTIDAD_MAXIMA_VEHICULOS.equals(e.getMessage());
		}
		// Assert
		assertTrue(cantidadMaximaCarrosExcedida);
		verify(vehiculoRepository).contarCantidadVehiculos(Carro.class);
		verify(propiedadService).getPropertyAsInt(claveCantidadMaximaCarros);
	}

	@Test
	public void registarEntradaConCantidadMaximaMotos() {
		// Arrange
		String mensjaeCantidadMaximaMotosExcedida = null;
		Moto moto = new MotoTestDataBuilder().build();
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
		Moto moto = new MotoTestDataBuilder().build();
		when(vehiculoRepository.contarCantidadVehiculos(Moto.class)).thenReturn(BigDecimal.ZERO.longValue());
		String claveCantidadMaximaMotos = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaMotos))
				.thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS.intValue());

		when(placaValidator.validate(moto.getPlaca())).thenReturn(Boolean.FALSE);
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
		Moto moto = new MotoTestDataBuilder().build();
		when(vehiculoRepository.contarCantidadVehiculos(Moto.class)).thenReturn(BigDecimal.ZERO.longValue());
		String claveCantidadMaximaMotos = PropiedadUtil.getClaveConComodin(Moto.class.getSimpleName().toLowerCase(),
				PropiedadConstants.CANTIDAD_MAXIMA_VEHICULO);
		when(propiedadService.getPropertyAsInt(claveCantidadMaximaMotos))
				.thenReturn(CANTIDAD_MAXIMA_MOTOS_PERMITIDOS.intValue());
		when(placaValidator.validate(moto.getPlaca())).thenReturn(Boolean.TRUE);
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
		verify(registroRepository).save(registro);
		verify(dateProvider).getCurrentLocalDateTime();
	}

}

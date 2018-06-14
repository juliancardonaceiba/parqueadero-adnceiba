package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.impl.MotoServiceImpl;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MotoServiceTest {

	@Mock
	private VehiculoRepository vehiculoRepository;

	@InjectMocks
	private MotoService motoService = new MotoServiceImpl();

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
	public void obtenerMotoConPlacaCorrectaTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		when(vehiculoRepository.findByPlaca(MotoTestDataBuilder.PLACA_DEFAULT)).thenReturn(Optional.ofNullable(moto));
		// Act
		Optional<Vehiculo> vehiculo = motoService.getVehiculo(MotoTestDataBuilder.PLACA_DEFAULT);
		// Assert
		boolean vehiculoExiste = vehiculo.isPresent()
				&& MotoTestDataBuilder.PLACA_DEFAULT.equals(vehiculo.get().getPlaca());
		assertTrue(vehiculoExiste);
		verify(vehiculoRepository).findByPlaca(MotoTestDataBuilder.PLACA_DEFAULT);
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
		Moto motoNueva = new MotoTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.save(moto)).thenReturn(motoNueva);
		// Act
		Moto nuevoVehiculo = motoService.crearVehiculo(moto);
		// Assert
		assertTrue(nuevoVehiculo != null && nuevoVehiculo.getId() != null);
	}

}
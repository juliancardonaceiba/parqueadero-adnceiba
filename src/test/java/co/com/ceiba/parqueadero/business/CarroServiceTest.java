package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.assertNotNull;
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
import co.com.ceiba.parqueadero.business.impl.CarroServiceImpl;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarroServiceTest {

	@Mock
	private VehiculoRepository vehiculoRepository;

	@InjectMocks
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
	public void obtenerCarroConPlacaCorrectaTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.findByPlaca(CarroTestDataBuilder.PLACA_DEFAULT)).thenReturn(Optional.ofNullable(carro));
		// Act
		Optional<Vehiculo> vehiculo = carroService.getVehiculo(CarroTestDataBuilder.PLACA_DEFAULT);
		// Assert
		boolean vehiculoExiste = vehiculo.isPresent()
				&& CarroTestDataBuilder.PLACA_DEFAULT.equals(vehiculo.get().getPlaca());
		assertTrue(vehiculoExiste);
		verify(vehiculoRepository).findByPlaca(CarroTestDataBuilder.PLACA_DEFAULT);
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
		Carro carroNuevo = new CarroTestDataBuilder().withId(Long.MAX_VALUE).build();
		when(vehiculoRepository.save(carro)).thenReturn(carroNuevo);
		// Act
		Carro nuevoVehiculo = carroService.crearVehiculo(carro);
		// Assert
		assertTrue(nuevoVehiculo != null && nuevoVehiculo.getId() != null);
	}

}

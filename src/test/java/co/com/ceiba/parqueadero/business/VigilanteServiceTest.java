package co.com.ceiba.parqueadero.business;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.impl.VigilanteServiceimpl;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class VigilanteServiceTest {

	@Mock
	private VehiculoRepository vehiculoRepository;
	
	@InjectMocks
	private VigilanteServiceimpl vigilanteService;
	
	@Test
	public void registarEntradaConMaximaCantidadCarros() {
		//arrange
		Carro carro = new CarroTestDataBuilder().build();
		when(vehiculoRepository.contarCantidadVehiculos(carro.getClass())).thenReturn(20L);
		
		//act
		vigilanteService.registrarEntrada(carro);
		//assert
		//verify(vehiculoRepository);
	}
	
	public void registarEntradaConMaximaCantidadMotos() {
		fail();
	}
	
	public void calcularValorMoto() {
		fail();
	}
	
	public void calcularValorCarro() {
		fail();
	}
	
}

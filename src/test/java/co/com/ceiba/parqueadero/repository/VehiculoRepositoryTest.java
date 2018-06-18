//package co.com.ceiba.parqueadero.repository;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import co.com.ceiba.parqueadero.business.VigilanteService;
//import co.com.ceiba.parqueadero.domain.model.Carro;
//import co.com.ceiba.parqueadero.domain.model.Moto;
//import co.com.ceiba.parqueadero.domain.model.Recargo;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class VehiculoRepositoryTest {
//	
//	@Autowired
//	private VigilanteService vigilanteService; 
// 	
//	@Autowired
//	private VehiculoRepository vehiculoRepository;
//	
////	@Test
////	public void createVehiculoTest() {
////		vigilanteService.registrarEntrada(new Moto("FPH78D", 125));
////		vigilanteService.registrarEntrada(new Moto("FPH79D", 126));
////		vigilanteService.registrarEntrada(new Carro("FDS75H"));
////		vigilanteService.registrarEntrada(new Carro("FDS76H"));
////		vigilanteService.registrarEntrada(new Carro("FDS77H"));
////		
////		Long contarCantidadMotos = vehiculoRepository.contarCantidadVehiculos(Moto.class);
////		Long contarCantidadCarros = vehiculoRepository.contarCantidadVehiculos(Carro.class);
////		
////		assertTrue(2L ==  contarCantidadMotos);
////		assertTrue(3L ==  contarCantidadCarros);
////		
////		System.err.println();
////	}
//
//	
//	@Autowired
//	private RecargoRepository recargoRepository; 
//	
//	@Test
//	public void XXXXXXXXXX() {
//		
//		recargoRepository.save( new Recargo(BigDecimal.valueOf(2000), 500));
//		recargoRepository.save( new Recargo(BigDecimal.valueOf(2000), 700));
//		recargoRepository.save( new Recargo(BigDecimal.valueOf(2000), 900));
//		
//		Optional<Recargo> recargoParaCilindraje = recargoRepository.getRecargoParaCilindraje(920);
//		assertTrue(recargoParaCilindraje.isPresent());
//		assertTrue(recargoParaCilindraje.get().getCilindrajeMinimo() == 900);
//	}
//	
//}

package co.com.ceiba.parqueadero.business.surcharge.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.ceiba.parqueadero.business.surcharge.SurchargeStrategy;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Recargo;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.RecargoTestDataBuilder;
import co.com.ceiba.parqueadero.repository.RecargoRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MotoSurchargeStrategyImplTest {

	private static final Integer CILINDRAJE_500CC = 500;
	private static final BigDecimal VALOR_RECARGADO_500CC = BigDecimal.valueOf(2000);

	@Mock
	private RecargoRepository recargoRepository;

	@InjectMocks
	private SurchargeStrategy surchargeStrategy = new MotoSurchargeStrategyImpl();

	@Test
	public void aplicarRecargoACarroTest() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		// Act
		boolean canApply = surchargeStrategy.canApply(carro);
		// Assert
		assertFalse(canApply);
	}
	
	@Test
	public void aplicarRecargoAMotoTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		boolean canApply = surchargeStrategy.canApply(moto);
		// Assert
		assertTrue(canApply);
	}

	@Test
	public void calcularRecargoMotoConCilindrajeAplicableTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withCilindraje(CILINDRAJE_500CC).build();
		Recargo recargo = new RecargoTestDataBuilder().withCilindrajeMinimo(CILINDRAJE_500CC)
				.withValorRecargado(VALOR_RECARGADO_500CC).build();
		when(recargoRepository.getRecargoParaCilindraje(CILINDRAJE_500CC)).thenReturn(Optional.of(recargo));
		// Act
		BigDecimal valorRecargado = surchargeStrategy.compute(BigDecimal.ZERO, moto);
		// Assert
		assertEquals(VALOR_RECARGADO_500CC, valorRecargado);
	}

	@Test
	public void calcularRecargoMotoSinRecargoTest() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().withCilindraje(CILINDRAJE_500CC).build();
		when(recargoRepository.getRecargoParaCilindraje(CILINDRAJE_500CC)).thenReturn(Optional.empty());
		// Act
		BigDecimal valorRecargado = surchargeStrategy.compute(BigDecimal.ZERO, moto);
		// Assert
		assertEquals(BigDecimal.ZERO, valorRecargado);
	}

}

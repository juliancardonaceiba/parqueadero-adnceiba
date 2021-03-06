package co.com.ceiba.parqueadero.business.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroDTOConverterTest {

	@Autowired
	private CarroDTOConverter converter;

	@Test
	public void convertirCarroACarroDTO() {
		// Arrange
		Carro carro = new CarroTestDataBuilder().build();
		// Act
		CarroDTO carroDTO = converter.convert(carro);
		// Assert
		assertEquals(carro.getId(), carroDTO.getId());
		assertEquals(carro.getPlaca(), carroDTO.getPlaca());
	}
}

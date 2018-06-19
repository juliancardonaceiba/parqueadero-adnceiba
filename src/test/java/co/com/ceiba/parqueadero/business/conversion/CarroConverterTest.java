package co.com.ceiba.parqueadero.business.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.builder.CarroDTOTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroConverterTest {

	@Autowired
	private CarroConverter converter;

	@Test
	public void convertirCarroDTOACarro() {
		// Arrange
		CarroDTO carroDTO = new CarroDTOTestDataBuilder().build();
		// Act
		Carro carro = converter.convert(carroDTO);
		// Assert
		assertEquals(carroDTO.getId(), carro.getId());
		assertEquals(carroDTO.getPlaca(), carro.getPlaca());
	}
}

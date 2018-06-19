package co.com.ceiba.parqueadero.business.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.builder.MotoDTOTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotoConverterTest {

	@Autowired
	private MotoConverter converter;

	@Test
	public void convertirMotoDTOAMoto() {
		// Arrange
		MotoDTO motoDTO = new MotoDTOTestDataBuilder().build();
		// Act
		Moto moto = converter.convert(motoDTO);
		// Assert
		assertEquals(motoDTO.getId(), moto.getId());
		assertEquals(motoDTO.getPlaca(), moto.getPlaca());
		assertEquals(motoDTO.getCilindraje(), moto.getCilindraje());
	}
}

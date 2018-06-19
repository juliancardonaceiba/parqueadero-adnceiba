package co.com.ceiba.parqueadero.business.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotoDTOConverterTest {

	@Autowired
	private MotoDTOConverter converter;

	@Test
	public void convertirMotoAMotoDTO() {
		// Arrange
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		MotoDTO motoDTO = converter.convert(moto);
		// Assert
		assertEquals(moto.getId(), motoDTO.getId());
		assertEquals(moto.getPlaca(), motoDTO.getPlaca());
		assertEquals(moto.getCilindraje(), motoDTO.getCilindraje());
	}
}

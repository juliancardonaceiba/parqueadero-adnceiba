package co.com.ceiba.parqueadero.business.client.soap;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class TRMServiceITest {

	@Autowired
	private TRMService trmService;

	@Test
	public void consultarTRM() {
		// Arrange
		// Act
		Float trm = trmService.getTRM();
		// Assert
		assertNotNull(trm);

	}

}

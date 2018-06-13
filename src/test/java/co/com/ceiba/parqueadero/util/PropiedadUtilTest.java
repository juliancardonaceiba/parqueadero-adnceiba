package co.com.ceiba.parqueadero.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.com.ceiba.parqueadero.domain.model.Carro;

public class PropiedadUtilTest {

	private static final String CLAVE_CON_COMODIN="clave.prueba.para."+PropiedadConstants.COMODIN;
	private static final String CLAVE_ESPERADA="clave.prueba.para.carro";
	
	
	@Test
	public void resolverClaveConComodinTest() {
		//Arrange
		//Act
		String clavePorTipoVehiculo = PropiedadUtil.getClaveConComodin(Carro.class.getSimpleName().toLowerCase(), CLAVE_CON_COMODIN);
		//Assert
		assertEquals(CLAVE_ESPERADA, clavePorTipoVehiculo);
	}
	
}

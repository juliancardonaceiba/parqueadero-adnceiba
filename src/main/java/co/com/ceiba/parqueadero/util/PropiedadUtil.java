package co.com.ceiba.parqueadero.util;

public class PropiedadUtil {

	private PropiedadUtil() {
	}

	public static String getClaveConComodin(String valorComodin, String key) {
		return key.replaceAll(PropiedadConstants.COMODIN, valorComodin);
	}

}

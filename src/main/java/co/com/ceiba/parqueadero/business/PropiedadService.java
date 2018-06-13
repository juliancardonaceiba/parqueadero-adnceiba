package co.com.ceiba.parqueadero.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.com.ceiba.parqueadero.util.PropiedadConstants;

public interface PropiedadService {

	public String getProperty(String key);

	public default Long getPropertyAsLong(String key) {
		return Long.parseLong(getProperty(key));
	}

	public default Integer getPropertyAsInt(String key) {
		return Integer.parseInt(getProperty(key));
	}

	public default List<String> getPropertyAsList(String key) {
		String property = getProperty(key);
		return Stream.of(property.split(PropiedadConstants.PROPERTY_SEPARATOR)).collect(Collectors.toList());
	}

}

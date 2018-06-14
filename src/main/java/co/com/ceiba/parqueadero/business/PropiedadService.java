package co.com.ceiba.parqueadero.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.com.ceiba.parqueadero.util.PropiedadConstants;

public interface PropiedadService {

	public String getProperty(String key);

	public default Long getPropertyAsLong(String key) {
		String property = getProperty(key);
		return property != null ? Long.parseLong(property) : null;
	}

	public default Integer getPropertyAsInt(String key) {
		String property = getProperty(key);
		return property != null ? Integer.parseInt(property) : null;
	}

	public default List<String> getPropertyAsList(String key) {
		String property = getProperty(key);
		return property != null
				? Stream.of(property.split(PropiedadConstants.PROPERTY_SEPARATOR)).collect(Collectors.toList())
				: null;
	}

}

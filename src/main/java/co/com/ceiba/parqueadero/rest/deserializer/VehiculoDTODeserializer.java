package co.com.ceiba.parqueadero.rest.deserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.dto.factory.VehiculoDTOManagerFactory;

@Component
public class VehiculoDTODeserializer extends JsonDeserializer<VehiculoDTO> {

	private VehiculoDTOManagerFactory vehiculoDTOManagerFactory;

	public VehiculoDTOManagerFactory getVehiculoDTOManagerFactory() {
		return vehiculoDTOManagerFactory;
	}

	@Autowired
	public void setVehiculoDTOManagerFactory(VehiculoDTOManagerFactory vehiculoDTOManagerFactory) {
		this.vehiculoDTOManagerFactory = vehiculoDTOManagerFactory;
	}

	@Override
	public VehiculoDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return getVehiculoDTOManagerFactory().newInstance(jp);
	}

}

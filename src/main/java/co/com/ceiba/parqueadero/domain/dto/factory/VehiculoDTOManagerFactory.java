package co.com.ceiba.parqueadero.domain.dto.factory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

@Component
public class VehiculoDTOManagerFactory {

	private List<AbstractVehiculoDTOFactory<?>> vehiculoDTOFactories;

	public List<AbstractVehiculoDTOFactory<?>> getVehiculoDTOFactories() {
		return vehiculoDTOFactories;
	}

	@Autowired
	public void setVehiculoDTOFactories(List<AbstractVehiculoDTOFactory<?>> vehiculoDTOFactories) {
		this.vehiculoDTOFactories = vehiculoDTOFactories;
	}

	public VehiculoDTO newInstance(JsonParser jp) {
		try {
			JsonNode node = jp.getCodec().readTree(jp);
			if (node.has(VehiculoDTO.ClassInfo.TIPO_VEHICULO_NAME)) {
				String nombreTipoVehiculo = node.get(VehiculoDTO.ClassInfo.TIPO_VEHICULO_NAME).textValue();
				TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(nombreTipoVehiculo);
				Optional<AbstractVehiculoDTOFactory<?>> vehiculoDTOFactory = getVehiculoDTOFactories().stream()
						.filter(v -> v.getTipo().equals(tipoVehiculo)).findFirst();
				if (vehiculoDTOFactory.isPresent()) {
					return vehiculoDTOFactory.get().createVehiculoDTO(node);
				}
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return null;
	}
}

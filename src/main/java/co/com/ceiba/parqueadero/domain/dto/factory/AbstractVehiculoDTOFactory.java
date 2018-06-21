package co.com.ceiba.parqueadero.domain.dto.factory;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

public abstract class AbstractVehiculoDTOFactory<T extends VehiculoDTO> {

	public abstract TipoVehiculo getTipo();

	public abstract T createVehiculoDTO(JsonNode node) throws IOException;

	protected Long getId(JsonNode node) {
		Long id = null;
		if (node.has(VehiculoDTO.ClassInfo.ID_NAME)) {
			id =  node.get(VehiculoDTO.ClassInfo.ID_NAME).asLong();
		}
		return id;
	}

	protected String getPlaca(JsonNode node) {
		String placa = null;
		if (node.has(VehiculoDTO.ClassInfo.PLACA_NAME)) {
			placa = node.get(VehiculoDTO.ClassInfo.PLACA_NAME).textValue();
		}
		return placa;
	}

}

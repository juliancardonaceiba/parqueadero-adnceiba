package co.com.ceiba.parqueadero.domain.dto.factory.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.dto.factory.AbstractVehiculoDTOFactory;
import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

@Component
public class MotoVehiculoDTOFactory extends AbstractVehiculoDTOFactory<MotoDTO> {

	@Override
	public TipoVehiculo getTipo() {
		return TipoVehiculo.MOTO;
	}

	@Override
	public MotoDTO createVehiculoDTO(JsonNode node) throws IOException {
		return new MotoDTO(getId(node), getPlaca(node), getCilindraje(node));
	}

	protected Integer getCilindraje(JsonNode node) {
		Integer cilindraje = null;
		if (node.has(MotoDTO.ClassInfo.CILINDRAJE_NAME)) {
			cilindraje = node.get(MotoDTO.ClassInfo.CILINDRAJE_NAME).asInt();
		}
		return cilindraje;
	}

}

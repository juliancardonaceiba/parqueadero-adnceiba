package co.com.ceiba.parqueadero.domain.dto.factory.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;
import co.com.ceiba.parqueadero.domain.dto.factory.AbstractVehiculoDTOFactory;
import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

@Component
public class CarroVehiculoDTOFactory extends AbstractVehiculoDTOFactory<CarroDTO> {

	public TipoVehiculo getTipo() {
		return TipoVehiculo.CARRO;
	}

	@Override
	public CarroDTO createVehiculoDTO(JsonNode node) throws IOException {
		return new CarroDTO(getId(node), getPlaca(node));
	}

}

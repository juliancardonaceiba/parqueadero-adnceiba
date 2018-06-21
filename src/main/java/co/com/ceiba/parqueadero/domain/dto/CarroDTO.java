package co.com.ceiba.parqueadero.domain.dto;

import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

public class CarroDTO extends VehiculoDTO {

	public CarroDTO() {
	}

	public CarroDTO(Long id, String placa) {
		super(id, placa, TipoVehiculo.CARRO);
	}

}

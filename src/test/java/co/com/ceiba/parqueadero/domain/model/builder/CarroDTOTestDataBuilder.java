package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;

public class CarroDTOTestDataBuilder {

	public static final Long ID_DEFAULT = 1L;
	public static final String PLACA_DEFAULT = "FHT358";

	private Long id;

	private String placa;

	public CarroDTOTestDataBuilder() {
		this.id = ID_DEFAULT;
		this.placa = PLACA_DEFAULT;
	}

	public CarroDTOTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CarroDTOTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public CarroDTO build() {
		return new CarroDTO(id, placa);
	}

}

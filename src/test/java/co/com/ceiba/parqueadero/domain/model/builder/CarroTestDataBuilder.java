package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.model.Carro;

public class CarroTestDataBuilder {

	public static final String PLACA_DEFAULT = "FHT358";

	private Long id;

	private String placa;

	public CarroTestDataBuilder() {
		this.placa = PLACA_DEFAULT;
	}

	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CarroTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public Carro build() {
		Carro carro = new Carro(this.placa);
		carro.setId(id);
		return carro;
	}

}

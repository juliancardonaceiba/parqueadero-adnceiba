package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.model.Carro;

public class CarroTestDataBuilder {

	private static final String PLACA_DEFAULT = "FHT358";

	private String placa;

	public CarroTestDataBuilder() {
		this.placa = PLACA_DEFAULT;
	}

	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public Carro build() {
		return new Carro(this.placa);
	}

}

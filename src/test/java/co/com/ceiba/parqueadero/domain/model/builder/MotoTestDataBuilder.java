package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.model.Moto;

public class MotoTestDataBuilder {

	private static final String PLACA_DEFAULT = "KNR127";
	private static final Integer CILINDRAJE_DEFAULT = 125;

	private String placa;

	private Integer cilindraje;

	public MotoTestDataBuilder() {
		this.placa = PLACA_DEFAULT;
		this.cilindraje = CILINDRAJE_DEFAULT;
	}

	public MotoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public void withCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Moto build() {
		return new Moto(this.placa, this.cilindraje);
	}

}

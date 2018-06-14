package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.model.Moto;

public class MotoTestDataBuilder {

	public static final String PLACA_DEFAULT = "KNR127";
	private static final Integer CILINDRAJE_DEFAULT = 125;

	private Long id;

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

	public MotoTestDataBuilder withId(Long id) {
		this.id = id;
		return this;

	}

	public MotoTestDataBuilder withCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Moto build() {
		Moto moto = new Moto(this.placa, this.cilindraje);
		moto.setId(id);
		return moto;
	}

}

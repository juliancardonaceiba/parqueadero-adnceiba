package co.com.ceiba.parqueadero.domain.model.builder;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;

public class MotoDTOTestDataBuilder {

	public static final Long ID_DEFAULT = 1L;
	public static final String PLACA_DEFAULT = "KNR127";
	private static final Integer CILINDRAJE_DEFAULT = 125;

	private Long id;

	private String placa;

	private Integer cilindraje;

	public MotoDTOTestDataBuilder() {
		this.id=ID_DEFAULT;
		this.placa = PLACA_DEFAULT;
		this.cilindraje = CILINDRAJE_DEFAULT;
	}

	public MotoDTOTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoDTOTestDataBuilder withId(Long id) {
		this.id = id;
		return this;

	}

	public MotoDTOTestDataBuilder withCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public MotoDTO build() {
		return new MotoDTO(this.id,this.placa, this.cilindraje);
	}

}

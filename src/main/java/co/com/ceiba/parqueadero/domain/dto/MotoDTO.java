package co.com.ceiba.parqueadero.domain.dto;

public class MotoDTO extends VehiculoDTO {

	private Integer cilindraje;

	public MotoDTO() {
	}

	public MotoDTO(Long id, String placa, Integer cilindraje) {
		super(id, placa);
		this.cilindraje = cilindraje;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

}

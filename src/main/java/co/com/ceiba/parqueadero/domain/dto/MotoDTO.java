package co.com.ceiba.parqueadero.domain.dto;

import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;

public class MotoDTO extends VehiculoDTO {

	public static final class ClassInfo {

		public static final String CILINDRAJE_NAME = "cilindraje";

		private ClassInfo() {
		}
	}

	private Integer cilindraje;

	public MotoDTO() {
	}

	public MotoDTO(Long id, String placa, Integer cilindraje) {
		super(id, placa, TipoVehiculo.MOTO);
		this.cilindraje = cilindraje;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

}

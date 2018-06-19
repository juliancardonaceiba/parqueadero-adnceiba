package co.com.ceiba.parqueadero.domain.dto;

public abstract class VehiculoDTO {

	private Long id;

	private String placa;

	public VehiculoDTO() {
	}

	public VehiculoDTO(Long id, String placa) {
		this.id = id;
		this.placa = placa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}

package co.com.ceiba.parqueadero.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;
import co.com.ceiba.parqueadero.rest.deserializer.VehiculoDTODeserializer;

@JsonDeserialize(using = VehiculoDTODeserializer.class)
public abstract class VehiculoDTO {

	public static final class ClassInfo {

		public static final String ID_NAME = "id";
		public static final String PLACA_NAME = "placa";
		public static final String TIPO_VEHICULO_NAME = "tipoVehiculo";

		private ClassInfo() {
		}
	}

	private Long id;

	private String placa;

	private TipoVehiculo tipoVehiculo;

	public VehiculoDTO() {
	}

	public VehiculoDTO(Long id, String placa, TipoVehiculo tipoVehiculo) {
		super();
		this.id = id;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
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

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}

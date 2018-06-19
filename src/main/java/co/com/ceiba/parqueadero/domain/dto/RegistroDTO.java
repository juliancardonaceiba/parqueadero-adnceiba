package co.com.ceiba.parqueadero.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RegistroDTO {

	private Long id;

	private LocalDateTime horaIngreso;

	private LocalDateTime horaSalida;

	private BigDecimal valor;

	private VehiculoDTO vehiculo;

	public RegistroDTO() {
	}

	public RegistroDTO(Long id, LocalDateTime horaIngreso, VehiculoDTO vehiculo) {
		this.id = id;
		this.horaIngreso = horaIngreso;
		this.vehiculo = vehiculo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(LocalDateTime horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}

}

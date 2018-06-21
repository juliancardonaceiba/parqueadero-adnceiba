package co.com.ceiba.parqueadero.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Registro.TableInfo.TABLE_NAME)
public class Registro implements Serializable {

	public static final class TableInfo {
		public static final String TABLE_NAME = "registro";
		public static final String ID_NAME = "id";
		public static final int ID_LENGTH = 10;
		public static final boolean ID_UNIQUE = true;
		public static final String HORA_INGRESO_NAME = "hora_ingreso";
		public static final boolean HORA_INGRESO_NULLABLE = false;
		public static final String HORA_SALIDA_NAME = "hora_salida";
		public static final boolean HORA_SALIDA_NULLABLE = true;
		public static final String VALOR_NAME = "valor";
		public static final int VALOR_PRECISION = 8;
		public static final int VALOR_SCALE = 2;
		public static final boolean VALOR_NULLABLE = true;
		public static final String VEHICULO_NAME = "vehiculo";
		public static final boolean VEHICULO_NULLABLE = false;

		private TableInfo() {
		}
	}

	public static final class ClassInfo {

		public static final String VEHICULO_NAME = "vehiculo";

		private ClassInfo() {
		}
	}

	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime horaIngreso;

	private LocalDateTime horaSalida;

	private BigDecimal valor;

	private Vehiculo vehiculo;
	
	public Registro() {
		super();
	}

	public Registro(Vehiculo vehiculo, LocalDateTime horaIngreso) {
		super();
		this.horaIngreso = horaIngreso;
		this.vehiculo = vehiculo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID_NAME, length = TableInfo.ID_LENGTH)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = TableInfo.HORA_INGRESO_NAME, nullable = TableInfo.HORA_INGRESO_NULLABLE)
	public LocalDateTime getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(LocalDateTime horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	@Column(name = TableInfo.HORA_SALIDA_NAME, nullable = TableInfo.HORA_SALIDA_NULLABLE)
	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Column(name = TableInfo.VALOR_NAME, precision = TableInfo.VALOR_PRECISION, scale = TableInfo.VALOR_SCALE, nullable = TableInfo.VALOR_NULLABLE)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.VEHICULO_NAME, nullable = TableInfo.VEHICULO_NULLABLE)
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 48;
		int result = 3;
		result = prime * result + ((horaIngreso == null) ? 15 : horaIngreso.hashCode());
		result = prime * result + ((vehiculo == null) ? 74 : vehiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (horaIngreso == null) {
			if (other.horaIngreso != null)
				return false;
		} else if (!horaIngreso.equals(other.horaIngreso))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	
}

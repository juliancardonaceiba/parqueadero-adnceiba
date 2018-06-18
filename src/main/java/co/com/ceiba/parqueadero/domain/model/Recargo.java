package co.com.ceiba.parqueadero.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Recargo.TableInfo.TABLE_NAME)
public class Recargo {

	public static final class TableInfo {
		public static final String TABLE_NAME = "recargo";
		public static final String ID_NAME = "id";
		public static final int ID_LENGTH = 10;
		public static final boolean ID_UNIQUE = true;
		public static final String VALOR_RECARGADO_NAME = "valor_recargado";
		public static final int VALOR_RECARGADO_PRECISION = 8;
		public static final int VALOR_RECARGADO_SCALE = 2;
		public static final boolean VALOR_RECARGADO_NULLABLE = false;
		public static final String CILINDRAJE_MINIMO_NAME = "cilindraje_minimo";
		public static final int CILINDRAJE_MINIMO_LENGTH = 4;
		public static final boolean CILINDRAJE_MINIMO_NULLABLE = false;

		private TableInfo() {
		}
	}

	private Long id;

	private BigDecimal valorRecargado;

	private Integer cilindrajeMinimo;

	public Recargo() {
	}

	public Recargo(BigDecimal valorRecargado, Integer cilindrajeMinimo) {
		super();
		this.valorRecargado = valorRecargado;
		this.cilindrajeMinimo = cilindrajeMinimo;
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

	@Column(name = TableInfo.VALOR_RECARGADO_NAME, precision = TableInfo.VALOR_RECARGADO_PRECISION, scale = TableInfo.VALOR_RECARGADO_SCALE, nullable = TableInfo.VALOR_RECARGADO_NULLABLE)
	public BigDecimal getValorRecargado() {
		return valorRecargado;
	}

	public void setValorRecargado(BigDecimal valorRecargado) {
		this.valorRecargado = valorRecargado;
	}

	@Column(name = TableInfo.CILINDRAJE_MINIMO_NAME, length = TableInfo.CILINDRAJE_MINIMO_LENGTH, nullable = TableInfo.CILINDRAJE_MINIMO_NULLABLE)
	public Integer getCilindrajeMinimo() {
		return cilindrajeMinimo;
	}

	public void setCilindrajeMinimo(Integer cilindrajeMinimo) {
		this.cilindrajeMinimo = cilindrajeMinimo;
	}

}

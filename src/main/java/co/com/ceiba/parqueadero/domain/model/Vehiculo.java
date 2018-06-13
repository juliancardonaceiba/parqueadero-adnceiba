package co.com.ceiba.parqueadero.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Vehiculo.TableInfo.TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = Vehiculo.TableInfo.TIPO_VEHICULO_NAME)
public abstract class Vehiculo implements Serializable {

	public static final class TableInfo {

		public static final String TABLE_NAME = "vehiculo";
		public static final String ID_NAME = "id";
		public static final int ID_LENGTH = 10;
		public static final boolean ID_UNIQUE = true;
		public static final String PLACA_NAME = "placa";
		public static final int PLACA_LENGTH = 6;
		public static final boolean PLACA_UNIQUE = true;
		public static final boolean PLACA_NULLABLE = false;
		public static final String TIPO_VEHICULO_NAME = "tipo_vehiculo";
		public static final boolean TIPO_VEHICULO_INSERTABLE = false;
		public static final boolean TIPO_VEHICULO_UPDATABLE = false;

		private TableInfo() {
		}
	}

	private static final long serialVersionUID = 1L;

	private Long id;

	private String placa;

	private TipoVehiculo tipo;

	private List<Registro> registros;

	public Vehiculo() {

	}

	public Vehiculo(String placa) {
		this.placa = placa;
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

	@Column(name = TableInfo.PLACA_NAME, length = TableInfo.PLACA_LENGTH, unique = TableInfo.PLACA_UNIQUE, nullable = TableInfo.PLACA_NULLABLE)
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

//	@Enumerated(EnumType.STRING)
//	@Column(name = TableInfo.TIPO_VEHICULO_NAME, insertable = TableInfo.TIPO_VEHICULO_INSERTABLE, updatable = TableInfo.TIPO_VEHICULO_UPDATABLE)
//	public TipoVehiculo getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoVehiculo tipo) {
//		this.tipo = tipo;
//	}

	@OneToMany(mappedBy = Registro.EntityInfo.VEHICULO_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

}

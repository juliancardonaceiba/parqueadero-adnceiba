package co.com.ceiba.parqueadero.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Moto.TableInfo.DISCRIMINATOR_VALUE)
public class Moto extends Vehiculo {

	public static final class TableInfo {
		public static final String DISCRIMINATOR_VALUE = "MOTO";
		public static final String CILINDRAJE_NAME = "cilindraje";
		public static final int CILINDRAJE_LENGTH = 4;
		public static final boolean CILINDRAJE_NULLABLE = true;

		private TableInfo() {
		}
	}

	private static final long serialVersionUID = 1L;

	private Integer cilindraje;

	public Moto() {
		
	}

	public Moto(String placa, Integer cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	@Column(name = TableInfo.CILINDRAJE_NAME, length = TableInfo.CILINDRAJE_LENGTH, nullable = TableInfo.CILINDRAJE_NULLABLE)
	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

}

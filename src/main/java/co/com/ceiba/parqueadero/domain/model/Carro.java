package co.com.ceiba.parqueadero.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Carro.TableInfo.DISCRIMINATOR_VALUE)
public class Carro extends Vehiculo {

	public static final class TableInfo {
		public static final String DISCRIMINATOR_VALUE = "CARRO";

		private TableInfo() {
		}
	}

	private static final long serialVersionUID = -1L;

	public Carro() {
	}

	public Carro(String placa) {
		super(placa);
	}

}

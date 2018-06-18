package co.com.ceiba.parqueadero.business.surcharge;

import java.math.BigDecimal;

import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public interface SurchargeStrategy{

	public boolean canApply(Vehiculo vehiculo);
	
	public BigDecimal compute(BigDecimal valor, Vehiculo vehiculo);
}

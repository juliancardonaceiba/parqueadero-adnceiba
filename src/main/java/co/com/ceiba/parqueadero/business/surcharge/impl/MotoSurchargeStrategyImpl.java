package co.com.ceiba.parqueadero.business.surcharge.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.surcharge.SurchargeStrategy;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Recargo;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.RecargoRepository;

@Service
public class MotoSurchargeStrategyImpl implements SurchargeStrategy {

	private RecargoRepository recargoRepository;

	public RecargoRepository getRecargoRepository() {
		return recargoRepository;
	}

	@Autowired
	public void setRecargoRepository(RecargoRepository recargoRepository) {
		this.recargoRepository = recargoRepository;
	}

	@Override
	public boolean canApply(Vehiculo vehiculo) {
		return vehiculo instanceof Moto;
	}

	@Override
	public BigDecimal compute(BigDecimal valor, Vehiculo vehiculo) {
		Moto moto = (Moto) vehiculo;
		Optional<Recargo> recargoParaCilindraje = getRecargoRepository().getRecargoParaCilindraje(moto.getCilindraje());
		if (recargoParaCilindraje.isPresent()) {
			valor = valor.add(recargoParaCilindraje.get().getValorRecargado());
		}
		return valor;
	}

}

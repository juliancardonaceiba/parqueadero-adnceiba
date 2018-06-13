package co.com.ceiba.parqueadero.business.validation.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.validation.PlacaValidator;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@Service
public class PlacaValidatorImpl implements PlacaValidator {

	private Clock clock;

	private PropiedadService propiedadService;

	protected PropiedadService getPropiedadService() {
		return propiedadService;
	}

	@Autowired
	public void setPropiedadService(PropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	protected Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	@Override
	public boolean validate(String placa) {
		LocalDate fechaActual = LocalDate.now(getClock());
		List<String> inicialesPermitidas = propiedadService
				.getPropertyAsList(PropiedadUtil.getClaveConComodin(fechaActual.getDayOfWeek().name().toLowerCase(),
						PropiedadConstants.INICIALES_PLACAS_PERMITIDAS_POR_DIA));
		return inicialesPermitidas.contains(String.valueOf(placa.charAt(0)));
	}

}

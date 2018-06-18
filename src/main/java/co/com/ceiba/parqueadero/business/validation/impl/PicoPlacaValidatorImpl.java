package co.com.ceiba.parqueadero.business.validation.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.PicoPlacaValidator;
import co.com.ceiba.parqueadero.util.DateProvider;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.ceiba.parqueadero.util.PropiedadUtil;

@Component
public class PicoPlacaValidatorImpl implements PicoPlacaValidator {

	private PropiedadService propiedadService;

	private DateProvider dateProvider;

	protected PropiedadService getPropiedadService() {
		return propiedadService;
	}

	@Autowired
	public void setPropiedadService(PropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	protected DateProvider getDateProvider() {
		return dateProvider;
	}

	@Autowired
	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	@Override
	public void validate(String placa) {
		LocalDate fechaActual = getDateProvider().getCurrentLocalDate();
		String claveInicialesNoPermitidas = PropiedadUtil.getClaveConComodin(
				fechaActual.getDayOfWeek().name().toLowerCase(),
				PropiedadConstants.INICIALES_PLACAS_NO_PERMITIDAS_POR_DIA);
		List<String> inicialesNoPermitidas = getPropiedadService().getPropertyAsList(claveInicialesNoPermitidas);
		if (inicialesNoPermitidas.contains(String.valueOf(placa.charAt(0)))) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_NO_PERMITIDA_ESTE_DIA);
		}
	}

}

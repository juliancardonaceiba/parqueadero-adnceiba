package co.com.ceiba.parqueadero.business.validation.impl;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.domain.model.Moto;

@Component
public class MotoValidatorImpl extends VehiculoValidatorImpl<Moto> {

	@Override
	public void validate(Moto vehiculo) {
		super.validate(vehiculo);
		validarCilindraje(vehiculo.getCilindraje());
	}

	protected void validarCilindraje(Integer cilindraje) {
		if (cilindraje == null) {
			throw new BusinessException(ExceptionConstants.MSG_CILINDRAJE_ES_REQUERIDO);
		}
	}
}

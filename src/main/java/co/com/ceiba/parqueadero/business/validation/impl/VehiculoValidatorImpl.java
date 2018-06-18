package co.com.ceiba.parqueadero.business.validation.impl;

import org.apache.commons.lang3.StringUtils;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public abstract class VehiculoValidatorImpl<T extends Vehiculo> implements VehiculoValidator<T> {

	@Override
	public void validate(T vehiculo) {
		if (vehiculo == null) {
			throw new BusinessException(ExceptionConstants.MSG_VEHICULO_ES_REQUERIDO);
		}
		validarPlaca(vehiculo.getPlaca());
	}

	protected void validarPlaca(String placa) {
		if (StringUtils.isBlank(placa)) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_ES_REQUERIDA);
		}
	}

}

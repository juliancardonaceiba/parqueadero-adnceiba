package co.com.ceiba.parqueadero.business.impl;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.MotoService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.domain.model.Moto;

@Service
public class MotoServiceImpl extends VehiculoServiceImpl<Moto> implements MotoService {

	@Override
	protected void validarVehiculo(Moto moto) {
		super.validarVehiculo(moto);
		validarCilindraje(moto.getCilindraje());
	}

	protected void validarCilindraje(Integer cilindraje) {
		if (cilindraje == null) {
			throw new BusinessException(ExceptionConstants.MSG_CILINDRAJE_ES_REQUERIDO);
		}
	}
}

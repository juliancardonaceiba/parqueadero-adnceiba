package co.com.ceiba.parqueadero.business.validation.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.business.validation.VehiculoValidator;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

public abstract class VehiculoValidatorImpl<T extends Vehiculo> implements VehiculoValidator<T> {

	private VehiculoRepository vehiculoRepository;

	public VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

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
		Optional<Vehiculo> vehiculoPorPlaca = getVehiculoRepository().findByPlaca(placa);
		vehiculoPorPlaca.ifPresent(v -> {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_REGISTRADA);
		});
	}

}

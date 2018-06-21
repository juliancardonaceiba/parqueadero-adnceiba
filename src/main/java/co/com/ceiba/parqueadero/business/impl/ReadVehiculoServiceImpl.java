package co.com.ceiba.parqueadero.business.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.business.ReadVehiculoService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.AbstractVehiculoRepository;

public abstract class ReadVehiculoServiceImpl<T extends Vehiculo> implements ReadVehiculoService<T> {

	private AbstractVehiculoRepository<T> vehiculoRepository;

	protected AbstractVehiculoRepository<T> getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(AbstractVehiculoRepository<T> vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	public Optional<T> getVehiculoPorPlaca(String placa) {
		if (StringUtils.isBlank(placa)) {
			throw new BusinessException(ExceptionConstants.MSG_PLACA_ES_REQUERIDA);
		}
		return getVehiculoRepository().findByPlaca(placa);
	}

}

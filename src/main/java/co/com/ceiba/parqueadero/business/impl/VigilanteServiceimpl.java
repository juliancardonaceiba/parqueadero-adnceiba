package co.com.ceiba.parqueadero.business.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.repository.VehiculoRepository;

@Service
public class VigilanteServiceimpl implements VigilanteService {

	private VehiculoRepository vehiculoRepository;
	
	protected VehiculoRepository getVehiculoRepository() {
		return vehiculoRepository;
	}

	@Autowired
	public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	public Registro registrarEntrada(Vehiculo vehiculo) {
		Long cantidadVehiculos = getVehiculoRepository().contarCantidadVehiculos(vehiculo.getClass());
		
		return null;
	}

	@Override
	public Registro registrarSalida(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calcularValor(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		// TODO Auto-generated method stub
		return null;
	}

}

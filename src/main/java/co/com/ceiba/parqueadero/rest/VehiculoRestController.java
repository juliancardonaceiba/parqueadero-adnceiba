package co.com.ceiba.parqueadero.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public abstract class VehiculoRestController<T extends Vehiculo> {

	private VehiculoService<T> vehiculoService;

	protected VehiculoService<T> getVehiculoService() {
		return vehiculoService;
	}

	@Autowired
	public void setVehiculoService(VehiculoService<T> vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@GetMapping("/{placa}")
	@ResponseBody
	public Optional<Vehiculo> getVehiculo(@PathVariable("placa") String placa) {
		return getVehiculoService().getVehiculo(placa);
	}

	@PostMapping
	@ResponseBody
	public T crearVehiculo(@RequestBody T vehiculo) {
		return getVehiculoService().crearVehiculo(vehiculo);
	}
}

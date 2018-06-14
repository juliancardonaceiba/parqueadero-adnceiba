package co.com.ceiba.parqueadero.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.business.CarroService;
import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@RestController
@RequestMapping("/registro")
public class RegistroRestController {

	private VigilanteService vigilanteService;

	private CarroService carroService;

	protected VigilanteService getVigilanteService() {
		return vigilanteService;
	}

	@Autowired
	public void setVigilanteService(VigilanteService vigilanteService) {
		this.vigilanteService = vigilanteService;
	}

	protected CarroService getCarroService() {
		return carroService;
	}

	@Autowired
	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}

	@PostMapping
	public Registro registrarEntrada(@RequestParam("placa") String placa) {
		Optional<Vehiculo> vehiculo = getCarroService().getVehiculo(placa);
		return getVigilanteService().registrarEntrada(vehiculo.orElse(null));
	}

	@GetMapping
	public String hello() {
		return "H";
	}
}

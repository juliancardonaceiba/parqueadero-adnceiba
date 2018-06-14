package co.com.ceiba.parqueadero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.business.VigilanteService;

@RestController
@RequestMapping("/registro")
public class RegistroRestController {

	private VigilanteService vigilanteService;

	protected VigilanteService getVigilanteService() {
		return vigilanteService;
	}

	@Autowired
	public void setVigilanteService(VigilanteService vigilanteService) {
		this.vigilanteService = vigilanteService;
	}

	// @PostMapping
	// public Registro registrarEntrada(@RequestParam("placa") String placa) {
	// Optional<Vehiculo> vehiculo = vehiculoService.getVehiculo(placa);
	// vigilanteService.registrarEntrada(vehiculo.orElse(null));
	//
	// }
	//
	@GetMapping
	public String hello() {
		return "H";
	}
}

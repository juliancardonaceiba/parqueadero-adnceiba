package co.com.ceiba.parqueadero.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.business.VigilanteService;
import co.com.ceiba.parqueadero.domain.dto.RegistroDTO;
import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@RestController
@RequestMapping("/registros")
public class RegistroRestController {

	private VigilanteService vigilanteService;

	private VehiculoService vehiculoService;

	private ConversionService conversionService;

	protected VigilanteService getVigilanteService() {
		return vigilanteService;
	}

	@Autowired
	public void setVigilanteService(VigilanteService vigilanteService) {
		this.vigilanteService = vigilanteService;
	}

	public VehiculoService getVehiculoService() {
		return vehiculoService;
	}

	@Autowired
	public void setVehiculoService(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	public ConversionService getConversionService() {
		return conversionService;
	}

	@Autowired
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@PostMapping
	public RegistroDTO registrarEntrada(@RequestParam("placa") String placa) {
		Optional<Vehiculo> vehiculo = getVehiculoService().getVehiculoPorPlaca(placa);
		Registro registroEntrada = getVigilanteService().registrarEntrada(vehiculo.orElse(null));
		return conversionService.convert(registroEntrada, RegistroDTO.class);
	}

	@PutMapping
	public Optional<RegistroDTO> registrarSalida(@RequestParam("placa") String placa) {
		Optional<Vehiculo> vehiculo = getVehiculoService().getVehiculoPorPlaca(placa);
		Optional<Registro> registroSalida = getVigilanteService().registrarSalida(vehiculo.orElse(null));
		if (registroSalida.isPresent()) {
			RegistroDTO registroDTO = conversionService.convert(registroSalida.get(), RegistroDTO.class);
			return Optional.ofNullable(registroDTO);
		} else {
			return Optional.empty();
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping
	public Collection<RegistroDTO> getRegistros() {
		List<Registro> registrosPendientes = getVigilanteService().getRegistrosPendientes();
		return getConversionService().convert(registrosPendientes, Collection.class);
	}
}

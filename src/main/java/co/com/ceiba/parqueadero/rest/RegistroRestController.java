package co.com.ceiba.parqueadero.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/{placa}", method = RequestMethod.POST)
	public RegistroDTO registrarEntrada(@PathVariable("placa") String placa) {
		Optional<Vehiculo> vehiculo = getVehiculoService().getVehiculoPorPlaca(placa);
		Registro registroEntrada = getVigilanteService().registrarEntrada(vehiculo.orElse(null));
		return conversionService.convert(registroEntrada, RegistroDTO.class);
	}

	@RequestMapping(value = "/{placa}", method = RequestMethod.PUT)
	public Optional<RegistroDTO> registrarSalida(@PathVariable("placa") String placa) {
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
	@GetMapping(value="/pendientes")
	public Collection<RegistroDTO> getRegistrosActuales() {
		List<Registro> registrosPendientes = getVigilanteService().getRegistrosPendientes();
		return (List<RegistroDTO>) getConversionService().convert(registrosPendientes,
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Registro.class)),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RegistroDTO.class)));
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value="/historico")
	public Collection<RegistroDTO> getRegistrosHistoricos() {
		List<Registro> registrosHistoricos = getVigilanteService().getRegistrosHistoricos();
		return (List<RegistroDTO>) getConversionService().convert(registrosHistoricos,
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Registro.class)),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RegistroDTO.class)));
	}
}

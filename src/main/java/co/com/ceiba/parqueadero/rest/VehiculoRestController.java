package co.com.ceiba.parqueadero.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.business.EditVehiculoService;
import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoRestController {

	private ApplicationContext context;

	private ConversionService conversionService;

	private VehiculoService vehiculoService;

	public ConversionService getConversionService() {
		return conversionService;
	}

	@Autowired
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public ApplicationContext getContext() {
		return context;
	}

	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public VehiculoService getVehiculoService() {
		return vehiculoService;
	}

	@Autowired
	public void setVehiculoService(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@SuppressWarnings("unchecked")
	protected <T extends Vehiculo> EditVehiculoService<T> getVehiculoService(Class<T> vehiculoClass) {
		String[] beanNamesForType = getContext().getBeanNamesForType(
				ResolvableType.forClassWithGenerics(EditVehiculoService.class, vehiculoClass));
		return (EditVehiculoService<T>) getContext().getBean(beanNamesForType[0]);
	}

	@GetMapping("/{placa}")
	@ResponseBody
	public Optional<VehiculoDTO> getVehiculo(@PathVariable("placa") String placa) {
		Optional<Vehiculo> vehiculoOptional = getVehiculoService().getVehiculoPorPlaca(placa);
		if (vehiculoOptional.isPresent()) {
			VehiculoDTO vehiculoDTO = getConversionService().convert(vehiculoOptional.get(), VehiculoDTO.class);
			return Optional.ofNullable(vehiculoDTO);
		} else {
			return Optional.empty();
		}
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public VehiculoDTO crearVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
		Vehiculo vehiculo = getConversionService().convert(vehiculoDTO, Vehiculo.class);
		Vehiculo nuevoVehiculo = getVehiculoService(vehiculo.getClass()).crearVehiculo(vehiculo);
		return getConversionService().convert(nuevoVehiculo, VehiculoDTO.class);
	}

}

package co.com.ceiba.parqueadero.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.business.VehiculoService;
import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

public abstract class VehiculoRestController<T extends Vehiculo, V extends VehiculoDTO> {

	private VehiculoService<T> vehiculoService;

	private ConversionService conversionService;

	private Class<T> entityClass;

	private Class<V> dtoClass;

	public VehiculoRestController(Class<T> entityClass, Class<V> dtoClass) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	protected VehiculoService<T> getVehiculoService() {
		return vehiculoService;
	}

	@Autowired
	public void setVehiculoService(VehiculoService<T> vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	public ConversionService getConversionService() {
		return conversionService;
	}

	@Autowired
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public Class<V> getDtoClass() {
		return dtoClass;
	}

	@GetMapping("/{placa}")
	@ResponseBody
	public Optional<VehiculoDTO> getVehiculo(@PathVariable("placa") String placa) {
		Optional<Vehiculo> vehiculoOptional = getVehiculoService().getVehiculo(placa);
		if (vehiculoOptional.isPresent()) {
			VehiculoDTO vehiculoDTO = getConversionService().convert(vehiculoOptional.get(), VehiculoDTO.class);
			return Optional.ofNullable(vehiculoDTO);
		} else {
			return Optional.empty();
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping
	@ResponseBody
	public V crearVehiculo(@RequestBody V vehiculo) {
		T entity = getConversionService().convert(vehiculo, getEntityClass());
		T nuevoVehiculo = getVehiculoService().crearVehiculo(entity);
		VehiculoDTO vehiculoDTO = getConversionService().convert(nuevoVehiculo, VehiculoDTO.class);
		return (V) vehiculoDTO;
	}
}

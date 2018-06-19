package co.com.ceiba.parqueadero.business.conversion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.domain.dto.RegistroDTO;
import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.Registro;

@Component
public class RegistroDTOConverter implements Converter<Registro, RegistroDTO> {

	private ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public RegistroDTO convert(Registro registro) {
		ConversionService conversionService = getContext()
				.getBean(StringUtils.uncapitalize(ConversionService.class.getSimpleName()), ConversionService.class);
		VehiculoDTO vehiculoDTO = conversionService.convert(registro.getVehiculo(), VehiculoDTO.class);
		RegistroDTO registroDTO = new RegistroDTO(registro.getId(), registro.getHoraIngreso(), vehiculoDTO);
		registroDTO.setHoraSalida(registro.getHoraSalida());
		registroDTO.setValor(registro.getValor());
		return registroDTO;
	}

}

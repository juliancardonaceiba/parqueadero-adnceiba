package co.com.ceiba.parqueadero.business.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.Moto;

@Component
public class MotoDTOConverter implements Converter<Moto,VehiculoDTO> {

	@Override
	public MotoDTO convert(Moto moto) {
		return new MotoDTO(moto.getId(), moto.getPlaca(), moto.getCilindraje());
	}
}

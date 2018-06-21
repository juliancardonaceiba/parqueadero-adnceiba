package co.com.ceiba.parqueadero.business.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@Component
public class MotoConverter implements Converter<MotoDTO, Vehiculo> {

	@Override
	public Moto convert(MotoDTO motoDTO) {
		Moto moto = new Moto(motoDTO.getPlaca(), motoDTO.getCilindraje());
		moto.setId(motoDTO.getId());
		return moto;
	}
}

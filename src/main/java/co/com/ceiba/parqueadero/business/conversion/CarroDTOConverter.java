package co.com.ceiba.parqueadero.business.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;
import co.com.ceiba.parqueadero.domain.dto.VehiculoDTO;
import co.com.ceiba.parqueadero.domain.model.Carro;

@Component
public class CarroDTOConverter implements Converter<Carro,VehiculoDTO> {

	@Override
	public CarroDTO convert(Carro carro) {
		return new CarroDTO(carro.getId(), carro.getPlaca());
	}

}

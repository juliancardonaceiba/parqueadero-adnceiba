package co.com.ceiba.parqueadero.business.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.domain.dto.CarroDTO;
import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;

@Component
public class CarroConverter implements Converter<CarroDTO, Vehiculo> {

	@Override
	public Carro convert(CarroDTO carroDTO) {
		Carro carro = new Carro(carroDTO.getPlaca());
		carro.setId(carroDTO.getId());
		return carro;
	}

}

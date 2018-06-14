package co.com.ceiba.parqueadero.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.model.Carro;

@RestController
@RequestMapping("/carros")
public class CarroRestController extends VehiculoRestController<Carro> {

}

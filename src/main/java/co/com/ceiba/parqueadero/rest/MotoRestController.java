package co.com.ceiba.parqueadero.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.domain.model.Moto;

@RestController
@RequestMapping("/motos")
public class MotoRestController extends VehiculoRestController<Moto>{

}

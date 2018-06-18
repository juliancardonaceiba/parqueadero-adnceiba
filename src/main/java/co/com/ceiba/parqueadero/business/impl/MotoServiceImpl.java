package co.com.ceiba.parqueadero.business.impl;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.MotoService;
import co.com.ceiba.parqueadero.domain.model.Moto;

@Service
public class MotoServiceImpl extends VehiculoServiceImpl<Moto> implements MotoService {

}

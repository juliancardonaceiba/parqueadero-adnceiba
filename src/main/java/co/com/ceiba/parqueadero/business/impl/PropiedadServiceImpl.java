package co.com.ceiba.parqueadero.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.PropiedadService;

@Service
public class PropiedadServiceImpl implements PropiedadService {

	private Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public String getProperty(String key) {
		return getEnvironment().getProperty(key);
	}

}

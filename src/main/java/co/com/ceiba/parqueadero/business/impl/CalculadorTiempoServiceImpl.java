package co.com.ceiba.parqueadero.business.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.CalculadorTiempoService;
import co.com.ceiba.parqueadero.business.PropiedadService;
import co.com.ceiba.parqueadero.util.PropiedadConstants;

@Service
public class CalculadorTiempoServiceImpl implements CalculadorTiempoService {

	private PropiedadService propiedadService;

	public PropiedadService getPropiedadService() {
		return propiedadService;
	}

	@Autowired
	public void setPropiedadService(PropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	@Override
	public Duration calcularTiempo(LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		Long numeroHorasInicioCobroDia = getPropiedadService()
				.getPropertyAsLong(PropiedadConstants.NUMERO_HORAS_INICIO_COBRO_DIA);
		Duration between = Duration.between(fechaIngreso, fechaSalida);
		long numeroDias = between.toDays();
		between = between.minusDays(numeroDias);
		long numeroHoras = between.toHours();
		between = between.minusHours(numeroHoras);
		long numeroMinutos = between.toMinutes();
		between = between.minusMinutes(numeroMinutos);
		long nanos = between.toNanos();
		if (nanos > 0) {
			numeroMinutos++;
		}
		if (numeroMinutos > 0) {
			numeroHoras++;
		}
		if (numeroHoras >= numeroHorasInicioCobroDia) {
			numeroDias++;
			numeroHoras = 0;
		}
		return Duration.ofDays(numeroDias).plusHours(numeroHoras);
	}

}

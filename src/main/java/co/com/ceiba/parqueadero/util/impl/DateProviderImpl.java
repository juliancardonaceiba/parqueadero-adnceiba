package co.com.ceiba.parqueadero.util.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.util.DateProvider;

@Component
public class DateProviderImpl implements DateProvider {

	private LocalDateTime localDateTime;

	private LocalDate localDate;

	@Override
	public LocalDate getCurrentLocalDate() {
		return localDate != null ? localDate : LocalDate.now();
	}

	@Override
	public LocalDateTime getCurrentLocalDateTime() {
		return localDateTime != null ? localDateTime : LocalDateTime.now();
	}

}

package co.com.ceiba.parqueadero.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateProvider {

	public LocalDate getCurrentLocalDate();
	
	public LocalDateTime getCurrentLocalDateTime();
	
}

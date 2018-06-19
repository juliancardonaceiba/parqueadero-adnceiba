package co.com.ceiba.parqueadero.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConversionConfig {

	@Bean
	public ConversionServiceFactoryBean conversionService(Set<Converter<?, ?>> converters) {
	    ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
	    factory.setConverters(converters);
	    return factory;
	}
}

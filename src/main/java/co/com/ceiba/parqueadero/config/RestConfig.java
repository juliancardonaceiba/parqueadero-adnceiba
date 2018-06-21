package co.com.ceiba.parqueadero.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

@Configuration
@EnableWebMvc
public class RestConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
				.allowCredentials(false).maxAge(3600);
	}

	@Bean
	public HandlerInstantiator handlerInstantiator(ApplicationContext applicationContext) {
		return new SpringHandlerInstantiator(applicationContext.getAutowireCapableBeanFactory());
	}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(HandlerInstantiator handlerInstantiator) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.handlerInstantiator(handlerInstantiator);
		return builder;
	}
}

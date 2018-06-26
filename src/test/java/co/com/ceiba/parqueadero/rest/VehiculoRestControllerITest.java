package co.com.ceiba.parqueadero.rest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/parqueadero/creacion_vehiculo.feature")
@SpringBootTest
public class VehiculoRestControllerITest {

	
	
}

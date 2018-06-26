package co.com.ceiba.parqueadero.rest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features/parqueadero/registro_entrada_vehiculo.feature",
		"src/test/resources/features/parqueadero/registro_salida_vehiculo.feature" })
public class RegistroRestControllerITest {

}

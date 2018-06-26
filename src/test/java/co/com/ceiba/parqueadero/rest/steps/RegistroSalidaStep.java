package co.com.ceiba.parqueadero.rest.steps;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static net.serenitybdd.rest.SerenityRest.when;

import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.model.builder.MotoDTOTestDataBuilder;
import co.com.ceiba.parqueadero.util.URLConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;

public class RegistroSalidaStep {

	private static final String PATH_VEHICULO_REST_SERVICE = URLConfig.REST_SERVICES_BASE_PATH + "/rest/vehiculos";
	private static final String PATH_REGISTRO_REST_SERVICE = URLConfig.REST_SERVICES_BASE_PATH + "/rest/registros";

	private static final String PLACA_MOTO = "HTF27L";

	@Given("^Juan va a cobrar por un vehiculo en el parqueadero$")
	public void juan_va_a_cobrar_por_una_moto_en_el_parqueadero() {
		MotoDTO motoDTO = new MotoDTOTestDataBuilder().withPlaca(PLACA_MOTO).build();
		given().contentType(ContentType.JSON).body(motoDTO).when().post(PATH_VEHICULO_REST_SERVICE);
		given().contentType(ContentType.JSON).when().post(PATH_REGISTRO_REST_SERVICE + "/" + PLACA_MOTO);
	}

	@When("^Registra la salida de el vehiculo$")
	public void registra_la_salida_de_la_moto() {
		when().put(PATH_REGISTRO_REST_SERVICE + "/" + PLACA_MOTO);
	}

	@Then("^Debe generar el recibo de salida$")
	public void debe_generar_el_recibo_de_salida() {
		then().statusCode(HttpStatus.OK.value()).body(Matchers.containsString("horaIngreso"));
	}

}

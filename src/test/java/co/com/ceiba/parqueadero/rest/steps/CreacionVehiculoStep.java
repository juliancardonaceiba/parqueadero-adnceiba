package co.com.ceiba.parqueadero.rest.steps;

import static net.serenitybdd.rest.SerenityRest.given;

import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import co.com.ceiba.parqueadero.domain.dto.MotoDTO;
import co.com.ceiba.parqueadero.domain.model.builder.MotoDTOTestDataBuilder;
import co.com.ceiba.parqueadero.util.URLConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CreacionVehiculoStep {

	private static final String PATH_VEHICULO_REST_SERVICE = URLConfig.REST_SERVICES_BASE_PATH + "/rest/vehiculos";

	private RequestSpecification requestSpecification;

	@Given("^Miguel recibe un nuevo vehiculo en el parqueadero$")
	public void miguel_recibe_una_nueva_moto_en_el_parqueadero() {
		MotoDTO motoDTO = new MotoDTOTestDataBuilder().build();
		requestSpecification = given().contentType(ContentType.JSON).body(motoDTO);
	}

	@When("^Registra los datos de el vehiculo$")
	public void registra_los_datos_de_la_moto() {
		requestSpecification.when().post(PATH_VEHICULO_REST_SERVICE);
	}

	@Then("^Debe guardarse en la base de datos$")
	public void debe_guardarse_en_la_base_de_datos() {
		requestSpecification.then().statusCode(HttpStatus.OK.value()).body(Matchers.containsString("\"id\":"));
	}

}

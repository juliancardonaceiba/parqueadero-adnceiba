//package co.com.ceiba.parqueadero.rest;
//
//import static com.jayway.restassured.RestAssured.given;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.jayway.restassured.RestAssured;
//
//import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CarroRestControllerITest {
//	
//	@Value("${local.server.port}")
//    private int serverPort;
// 
//    @Before
//    public void setUp() {
//        RestAssured.port = serverPort;
//    }
//	
//	@Test
//	public void testIntegrationITest() {
//		given()
//			.pathParam("placa", MotoTestDataBuilder.PLACA_DEFAULT)
//		.when()
//			.get("/rest/motos/{placa}")
//		.then()
//			.statusCode(200);
//	}
//
//}

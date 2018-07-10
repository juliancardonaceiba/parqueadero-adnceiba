package co.com.ceiba.parqueadero.ui;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.com.ceiba.parqueadero.domain.model.Carro;
import co.com.ceiba.parqueadero.domain.model.Moto;
import co.com.ceiba.parqueadero.domain.model.TipoVehiculo;
import co.com.ceiba.parqueadero.domain.model.builder.CarroTestDataBuilder;
import co.com.ceiba.parqueadero.domain.model.builder.MotoTestDataBuilder;
import co.com.ceiba.parqueadero.util.URLConfig;
import io.github.bonigarcia.wdm.ChromeDriverManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistroVehiculosFTest {

	private static final String APP_URL = URLConfig.ANGULAR_BASE_PATH;
	private static final String URL_REGISTROS_ENTRADA = "/#/registro/entrada";
	private static final String REGISTROS_ACTUALES = "/#/administracion/actuales";
	private static final String PLACA_MOTO="FPH78D";
	private static final String PLACA_CARRO="FPH786";
	
	private static WebDriver webDriver;

	@BeforeClass
	public static void initDriver() {
		ChromeDriverManager.getInstance().setup();
		webDriver = new ChromeDriver();

	}
	
	@After
	public void reset() {
		webDriver.get(APP_URL);
	}

	@Test
	public void registrarMotoEntradaTest() {
		// Arrange
		webDriver.get(APP_URL + URL_REGISTROS_ENTRADA);

		WebElement textPlaca = webDriver.findElement(By.id("placa"));

		WebElement tipoVehiculoElemet = webDriver.findElement(By.id("tipoVehiculo"));
		Select selectTipoVehiculo = new Select(tipoVehiculoElemet);
		WebElement botonRegistrar = webDriver.findElement(By.id("btn-acceptar"));
		Moto moto = new MotoTestDataBuilder().build();
		// Act
		textPlaca.sendKeys(moto.getPlaca());
		tipoVehiculoElemet.click();
		if (tipoVehiculoElemet.isEnabled()) {
			selectTipoVehiculo.selectByValue(TipoVehiculo.MOTO.name());
			new WebDriverWait(webDriver, 3000)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("cilindraje")));
			WebElement textCilindraje = webDriver.findElement(By.id("cilindraje"));
			textCilindraje.sendKeys(String.valueOf(moto.getCilindraje()));
		}
		botonRegistrar.click();
		// Assert
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("panel-recibo-entrada")));
		WebElement dialogoRegistroEntrada = webDriver.findElement(By.id("panel-recibo-entrada"));
		assertTrue(dialogoRegistroEntrada.isDisplayed());
	}

	@Test
	public void registrarMotoSalidaTest() {
		// Arrange
		
		webDriver.get(APP_URL + URL_REGISTROS_ENTRADA);

		WebElement textPlaca = webDriver.findElement(By.id("placa"));

		WebElement tipoVehiculoElemet = webDriver.findElement(By.id("tipoVehiculo"));
		Select selectTipoVehiculo = new Select(tipoVehiculoElemet);
		WebElement botonRegistrar = webDriver.findElement(By.id("btn-acceptar"));
		Moto moto = new MotoTestDataBuilder().withPlaca(PLACA_MOTO).build();
		textPlaca.sendKeys(moto.getPlaca());
		tipoVehiculoElemet.click();
		if (tipoVehiculoElemet.isEnabled()) {
			selectTipoVehiculo.selectByValue(TipoVehiculo.MOTO.name());
			new WebDriverWait(webDriver, 3000)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("cilindraje")));
			WebElement textCilindraje = webDriver.findElement(By.id("cilindraje"));
			textCilindraje.sendKeys(String.valueOf(moto.getCilindraje()));
		}
		botonRegistrar.click();
		
		webDriver.get(APP_URL + REGISTROS_ACTUALES);
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-registrar-salida")));
		WebElement botonRegistrarSalida = webDriver.findElement(By.className("btn-registrar-salida"));
		// Act
		botonRegistrarSalida.click();
		new WebDriverWait(webDriver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-si")));
		WebElement botonSi = webDriver.findElement(By.className("btn-si"));
		botonSi.click();
		// Assert
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("panel-recibo-salida")));
		WebElement dialogoRegistroSalida = webDriver.findElement(By.id("panel-recibo-salida"));
		assertTrue(dialogoRegistroSalida.isDisplayed());
	}
	
	@Test
	public void registrarCarroEntradaTest() {
		// Arrange
		webDriver.get(APP_URL + URL_REGISTROS_ENTRADA);

		WebElement textPlaca = webDriver.findElement(By.id("placa"));

		WebElement tipoVehiculoElemet = webDriver.findElement(By.id("tipoVehiculo"));
		Select selectTipoVehiculo = new Select(tipoVehiculoElemet);
		WebElement botonRegistrar = webDriver.findElement(By.id("btn-acceptar"));
		Carro carro = new CarroTestDataBuilder().build();
		// Act
		textPlaca.sendKeys(carro.getPlaca());
		tipoVehiculoElemet.click();
		if (tipoVehiculoElemet.isEnabled()) {
			selectTipoVehiculo.selectByValue(TipoVehiculo.CARRO.name());			
		}
		botonRegistrar.click();
		// Assert
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("panel-recibo-entrada")));
		WebElement dialogoRegistroEntrada = webDriver.findElement(By.id("panel-recibo-entrada"));
		assertTrue(dialogoRegistroEntrada.isDisplayed());
	}

	@Test
	public void registrarCarroSalidaTest() {
		// Arrange
		webDriver.get(APP_URL + URL_REGISTROS_ENTRADA);

		WebElement textPlaca = webDriver.findElement(By.id("placa"));

		WebElement tipoVehiculoElemet = webDriver.findElement(By.id("tipoVehiculo"));
		Select selectTipoVehiculo = new Select(tipoVehiculoElemet);
		WebElement botonRegistrar = webDriver.findElement(By.id("btn-acceptar"));
		Carro carro = new CarroTestDataBuilder().withPlaca(PLACA_CARRO).build();
		textPlaca.sendKeys(carro.getPlaca());
		tipoVehiculoElemet.click();
		if (tipoVehiculoElemet.isEnabled()) {
			selectTipoVehiculo.selectByValue(TipoVehiculo.CARRO.name());			
		}
		botonRegistrar.click();
		
		webDriver.get(APP_URL + REGISTROS_ACTUALES);
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-registrar-salida")));
		WebElement botonRegistrarSalida = webDriver.findElement(By.className("btn-registrar-salida"));
		// Act
		botonRegistrarSalida.click();
		new WebDriverWait(webDriver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-si")));
		WebElement botonSi = webDriver.findElement(By.className("btn-si"));
		botonSi.click();
		// Assert
		new WebDriverWait(webDriver, 5000)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("panel-recibo-salida")));
		WebElement dialogoRegistroSalida = webDriver.findElement(By.id("panel-recibo-salida"));
		assertTrue(dialogoRegistroSalida.isDisplayed());
	}

	@AfterClass
	public static void destroyDriver() {
		webDriver.quit();
	}

}

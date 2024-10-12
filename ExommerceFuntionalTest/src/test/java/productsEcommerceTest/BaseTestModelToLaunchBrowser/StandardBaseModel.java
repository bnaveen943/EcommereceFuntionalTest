package productsEcommerceTest.BaseTestModelToLaunchBrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import productsEcommerceTest.ExommerceFuntionalTest.LoginPageTest;

public class StandardBaseModel {

	public WebDriver driver;
	public LoginPageTest loginPageTest;

	public WebDriver intializeBrowser() throws Exception {

		Properties prop = new Properties();
		// E:\\Java Eclipse\\eclipse\\ExommerceFuntionalTest
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\productsEcommerceTest\\GlobalProperties\\GlobalProperties.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// = prop.getProperty("browser");

		// TO run test cases in headless mode use can use this below login

		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			

		}
// Some comment to verify
		
		// some change to push this to Jenkins
		
		/*
		 * if (browserName.equalsIgnoreCase("chrome")) {
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 * driver.manage().window().setSize(new Dimension(1440, 900));
		 * 
		 * }
		 */
		 
		  else if (browserName.equalsIgnoreCase("fireFox")) {
			// firefox
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	public String getScreenshot(String takeFailedTestScreenShot, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + takeFailedTestScreenShot + ".png");
		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" + takeFailedTestScreenShot + ".png";
	}

	public List<HashMap<String, String>> getJsonUtilityData(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPageTest launchBrowser() throws Exception {
		driver = intializeBrowser();
		loginPageTest = new LoginPageTest(driver);
		loginPageTest.goToLogin();
		return loginPageTest;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}

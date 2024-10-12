package ecommerceApplicationTest.EcommerceAplicationTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestApplication {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");

		String email = "john.aran@gmail.com";
		String password = "John@0071";
		String country = "India";
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		String nameOfTheProduct = "ZARA COAT 3";
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(nameOfTheProduct))
				.findFirst().orElse(null);

		// Click on
		prod.findElement(By.cssSelector("[class='card-body'] button:last-of-type")).click();

		// Explicit wait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Waiting for toast message
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.id("toast-container"));

		// Click on 'Cart' button
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

		// Verifying the list of carted products
		List<WebElement> cartVerificationProducts = driver.findElements(By.cssSelector("[class='cartSection'] h3"));
		org.testng.Assert.assertEquals("ZARA COAT 3", nameOfTheProduct);

		// Click on check out button
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		// Email verification

		String emailVerification = driver.findElement(By.xpath("//div /div /div /label")).getText();
		System.out.println(emailVerification);
		Assert.assertEquals(email, emailVerification);

		// Sending value to country field to select country

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("[class='ta-item list-group-item ng-star-inserted']")));

		// Get the list of countries

		List<WebElement> countries = driver
				.findElements(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"));

		// Select the required country from the list
		for (WebElement webElement : countries) {
			if (webElement.getText().equalsIgnoreCase("India")) {
				webElement.click();
			}
		}

		// Click on place order
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

		// Get the text of final confirmation message
		String finalConfirmationMessage = driver.findElement(By.xpath("//td /h1")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", finalConfirmationMessage);

	}

}

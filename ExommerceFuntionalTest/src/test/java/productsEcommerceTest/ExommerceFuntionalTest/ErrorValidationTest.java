package productsEcommerceTest.ExommerceFuntionalTest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import productsEcommerceTest.BaseTestModelToLaunchBrowser.FlakyTestRetryTestNG;
import productsEcommerceTest.BaseTestModelToLaunchBrowser.StandardBaseModel;

public class ErrorValidationTest extends StandardBaseModel {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = FlakyTestRetryTestNG.class )
	public void loginErrorValidation() throws Exception {

		// email
		String userEmail = "john.aran@gmail.com";
		// Password
		String userPassword = "John071";

		// Product CatalogPage to select the products
		loginPageTest.loginWithCredential(userEmail, userPassword);
		Assert.assertEquals("Incorrect email or password.", loginPageTest.errorValidatationMessage());
		;

	}

	@Test
	public void productErrorValidation() throws Exception {

		// email
		String userEmail = "hnaveen@gmail.com";
		// Password
		String userPassword = "Naveen0071@";
		// Product Name to select
		String productName = "ZARA COAT 3";

		// Product CatalogPage to select the products
		ProductCatalogPageTest productCatalogPageTest = loginPageTest.loginWithCredential(userEmail, userPassword);
		// Verify the product in MyCartPage
		MyCartPageTest myCartPageTest = productCatalogPageTest.selectProduct(productName);
		Boolean value = myCartPageTest.verifiedProducts("ZARA COAT 33");
		Assert.assertFalse(value);

	}

}

package productsEcommerceTest.ExommerceFuntionalTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import productsEcommerceTest.BaseTestModelToLaunchBrowser.StandardBaseModel;

public class BaseModelToTest extends StandardBaseModel {

	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String, String> dataInputs) throws Exception {

		/*
		 * // email String userEmail = "john.aran@gmail.com"; // Password String
		 * userPassword = "John@0071"; // Product Name to select String productName =
		 * "ZARA COAT 3";
		 */

		/*
		 * // Country key to select String key = "ca";
		 * 
		 * // Country which you want to select String country = "Canada";
		 */

		// Create LoginPage class to start execution
		// LoginPageTest loginPageTest = launchBrowser();

		// Product CatalogPage to select the products
		ProductCatalogPageTest productCatalogPageTest = loginPageTest.loginWithCredential(dataInputs.get("email"),
				dataInputs.get("password"));
		// Verify the product in MyCartPage
		MyCartPageTest myCartPageTest = productCatalogPageTest.selectProduct(dataInputs.get("productName"));
		Boolean value = myCartPageTest.verifiedProducts(dataInputs.get("productName"));
		org.testng.Assert.assertTrue(value);
		PayementMethodPage payementMethodPage = myCartPageTest.goToCheckout();
		String emailVerification = payementMethodPage.verifedEmail();
		Assert.assertEquals(dataInputs.get("email"), emailVerification);
		
		FinalConfirmationPage finalConfirmationPage = payementMethodPage.selectCountry(dataInputs.get("key"),
				dataInputs.get("country"));
		String ConfirmationText = finalConfirmationPage.getConfirmationText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", ConfirmationText);
		// getConfirmationText

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryPageTest() throws Exception {

		// email
		String userEmail = "john.aran@gmail.com";
		// Password
		String userPassword = "John@0071";
		// Product Name to select
		String productName = "ZARA COAT 3";

		// Product CatalogPage to select the products
		ProductCatalogPageTest productCatalogPageTest = loginPageTest.loginWithCredential(userEmail, userPassword);

		OrderpageTest orderpageTest = productCatalogPageTest.ordersTestValidation();
		Assert.assertTrue(orderpageTest.orderVerificationTest(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		// E:\\Java Eclipse\\eclipse\\ExommerceFuntionalTest
		List<HashMap<String, String>> data = getJsonUtilityData(
				System.getProperty("user.dir") + "\\src\\test\\java\\productsEcommerceTest\\JsonData\\JsonFile.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * HashMap<String, String> hm = new HashMap<String, String>(); hm.put("email",
	 * "john.aran@gmail.com"); hm.put("password", "John@0071");
	 * hm.put("productName", "ZARA COAT 3"); HashMap<String, String> hm1 = new
	 * HashMap<String, String>(); hm1.put("email", "hnaveen@gmail.com");
	 * hm1.put("password", "Naveen0071@"); hm1.put("productName",
	 * "ADIDAS ORIGINAL");
	 */

}

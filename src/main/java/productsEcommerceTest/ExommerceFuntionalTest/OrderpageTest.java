package productsEcommerceTest.ExommerceFuntionalTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class OrderpageTest extends AbstractClassMethod {

	WebDriver driver;

	public OrderpageTest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Verifying the carted products
	@FindBy(xpath = "//tbody /tr /td [2]")
	List<WebElement> verifyProducts;

	// wait for elements to visible
	By verifyProductVisible = By.xpath("//tbody /tr /td [2]");

	public List<WebElement> getListOfCartedProducts() {
		waitUntilProductsToAppear(verifyProductVisible);
		return verifyProducts;
	}

	public Boolean orderVerificationTest(String productName) {

		Boolean prod = getListOfCartedProducts().stream()
				.allMatch(product -> product.getText().equalsIgnoreCase(productName));

		return prod;
	}

}

package productsEcommerceTest.ExommerceFuntionalTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class MyCartPageTest extends AbstractClassMethod {

	WebDriver driver;

	public MyCartPageTest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Verifying the carted products
	@FindBy(css = "[class='cartSection'] h3")
	List<WebElement> verifyProducts;

	// wait for elements to visible
	By verifyProductVisible = By.cssSelector("[class='cartSection'] h3");

	// Click on checkout
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOut;

	public List<WebElement> getListOfCartedProducts() {
		waitUntilProductsToAppear(verifyProductVisible);
		return verifyProducts;
	}

	public Boolean verifiedProducts(String productName) {
		Boolean prod = getListOfCartedProducts().stream()
				.allMatch(product -> product.getText().equalsIgnoreCase(productName));

		return prod;
	}

	public PayementMethodPage goToCheckout() {
		checkOut.click();
		
		return new PayementMethodPage(driver);
	}

}

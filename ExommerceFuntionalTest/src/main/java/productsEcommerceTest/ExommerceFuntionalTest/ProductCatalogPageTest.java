package productsEcommerceTest.ExommerceFuntionalTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class ProductCatalogPageTest extends AbstractClassMethod {

	WebDriver driver;

	public ProductCatalogPageTest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List of products
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// visibility of products
	By productsVisible = By.cssSelector(".mb-3");

	// add to cart button
	By addToCart = By.cssSelector("[class='card-body'] button:last-of-type");

	// After clicked on wait until message displayed
	By waitforMessageToDisplay = By.cssSelector("#toast-container");

	// Select cart to pay and verify the products
	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement clickCart;

	// By clickCart = By.cssSelector("[routerlink='/dashboard/cart']");

	// Get list of products
	public List<WebElement> getListOfProducts() {
		waitUntilProductsToAppear(productsVisible);
		return products;
	}

	// Get product based on name of the product
	public WebElement getProduct(String productName) {
		WebElement prod = getListOfProducts().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return prod;
	}

	public MyCartPageTest selectProduct(String productName) {
		WebElement prod = getProduct(productName);
		prod.findElement(addToCart).click();
		waitUntilProductsToAppear(waitforMessageToDisplay);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		clickCart.click();
		
		return new MyCartPageTest(driver);

	}

}

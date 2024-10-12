package ecommerceApplicationTest.EcommerceAplicationTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ecommerceApplicationTest.AbsractPackage.AbstractClassForCommanElements;

public class ProductCatalogPage extends AbstractClassForCommanElements {

	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	// List of products
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// Waiting for products to load
	By productsLoad = By.cssSelector(".mb-3");

	By productCart = By.cssSelector("[class='card-body'] button:last-of-type");

	// Waiting for element to visible
	By elementVisble = By.id("toast-container");

	// Wait for element to disappear
	@FindBy(css = ".ng-animating")
	WebElement elementDisappear;

	// Add to cart button
	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement addtoCartButton;

	public List<WebElement> getTheListOfProducts() {
		waitForElementToAppear(productsLoad);
		return products;
	}

	public WebElement getTheProduct(String nameOfTheProduct) {
		WebElement prod = getTheListOfProducts().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(nameOfTheProduct))
				.findFirst().orElse(null);

		return prod;
	}

	public void goToCartAndAdd(String nameOfTheProduct) {

		// prod.findElement(By.cssSelector("[class='card-body']
		// button:last-of-type")).click();
		WebElement prod = getTheProduct(nameOfTheProduct);

		prod.findElement(productCart).click();
		waitForElementToAppear(elementVisble);
		
		waitForElementToDisappear(elementDisappear);
		addtoCartButton.click();

	}

}

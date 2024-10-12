package applicationTest.EcommerceApplication;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import applicationTest.AbstractClassForWait.AbstractClassForCommanElement;

public class ProductCatalogPage extends AbstractClassForCommanElement {

	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = ".mb-3")
	List<WebElement> products;

	By productsLoad = By.cssSelector(".mb-3");

	By productCart = By.cssSelector("[class='card-body'] button:last-of-type");

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
		WebElement getProdAndAdd = getTheProduct(nameOfTheProduct);
		getProdAndAdd.findElement(productCart).click();
	}

}

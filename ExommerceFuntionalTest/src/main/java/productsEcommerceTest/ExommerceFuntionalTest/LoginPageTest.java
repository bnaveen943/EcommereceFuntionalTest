package productsEcommerceTest.ExommerceFuntionalTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class LoginPageTest extends AbstractClassMethod {

	WebDriver driver;

	public LoginPageTest(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// User email
	@FindBy(id = "userEmail")
	WebElement email;

	// User password
	@FindBy(id = "userPassword")
	WebElement password;

	// click on login
	@FindBy(id = "login")
	WebElement login;

	// Login error message

	// .ng-tns-c4-35.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

	@FindBy(css = "div[class*='flyInOut ']")
	WebElement errorMessage;

	public String errorValidatationMessage() {
		waitUntilWebElementProductsToAppear(errorMessage);
		return errorMessage.getText();
	}

	// Go to login page
	public void goToLogin() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogPageTest loginWithCredential(String userEmail, String userPassword) {

		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();

		return new ProductCatalogPageTest(driver);

	}

}

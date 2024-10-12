package applicationTest.EcommerceApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import applicationTest.AbstractClassForWait.AbstractClassForCommanElement;

public class LandingPage extends AbstractClassForCommanElement {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	public void loginApplication(String userEmail, String userPassword) {
		goTo();
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}

package ecommerceApplicationTest.EcommerceAplicationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import applicationTest.AbstractClassForWait.AbstractClassForCommanElement;
import ecommerceApplicationTest.AbsractPackage.AbstractClassForCommanElements;

public class LandingPage extends AbstractClassForCommanElements {

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
	
	By ele=By.id("toast-container");

	public void loginApplication(String userEmail, String userPassword) {
		goTo();
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();
		waitForElementToAppear(ele);

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}

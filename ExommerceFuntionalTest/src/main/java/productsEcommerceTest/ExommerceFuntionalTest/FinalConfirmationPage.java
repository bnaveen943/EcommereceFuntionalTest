package productsEcommerceTest.ExommerceFuntionalTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class FinalConfirmationPage extends AbstractClassMethod {

	WebDriver driver;

	public FinalConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//td /h1")
	WebElement confirmationText;

	// Wait for until text visible
	By waitForTextToVisible = By.xpath("//td /h1");

	public String getConfirmationText() {
		waitUntilProductsToAppear(waitForTextToVisible);
		return confirmationText.getText();
	}

}

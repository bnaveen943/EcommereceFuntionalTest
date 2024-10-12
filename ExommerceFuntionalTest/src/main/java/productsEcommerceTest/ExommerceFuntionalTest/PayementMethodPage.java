package productsEcommerceTest.ExommerceFuntionalTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import productsEcommerceTest.AbstractMethodClass.AbstractClassMethod;

public class PayementMethodPage extends AbstractClassMethod {

	WebDriver driver;

	public PayementMethodPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// wait until this text visible
	By textVisible = By.xpath("//div[text()=' Payment Method ']");

	// Verify the email
	@FindBy(xpath = "//div /div /label")
	WebElement verifyEmail;

	// Send key of the country which you wanted to select
	@FindBy(css = "[placeholder='Select Country']")
	WebElement sendCountrykey;

	// List of countries
	@FindBy(xpath = "//section /button /span")
	List<WebElement> listOfCoutries;

	// wait until countries to visible
	By waitForCountriesVisible = By.xpath("//section /button /span");

	// click on placeOrder
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement clickOnPlaceOrder;

	public String verifedEmail() {
		waitUntilProductsToAppear(textVisible);
		String emailVerifcation = verifyEmail.getText();
		return emailVerifcation;

	}

	public FinalConfirmationPage selectCountry(String countryKey, String CountryName) {

		sendCountrykey.sendKeys(countryKey);

		waitUntilProductsToAppear(waitForCountriesVisible);
		WebElement eleCountry = listOfCoutries.stream()
				.filter(country -> country.getText().equalsIgnoreCase(CountryName)).findFirst().orElse(null);
		Actions action = new Actions(driver);
		action.click(eleCountry).build().perform();
		clickOnPlaceOrder.click();
		
		return new FinalConfirmationPage(driver);

	}

}

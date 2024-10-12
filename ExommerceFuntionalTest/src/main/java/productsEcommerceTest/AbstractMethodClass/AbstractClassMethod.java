package productsEcommerceTest.AbstractMethodClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import productsEcommerceTest.ExommerceFuntionalTest.OrderpageTest;

public class AbstractClassMethod {

	WebDriver driver;

	public AbstractClassMethod(WebDriver driver) {
		this.driver = driver;
		/* PageFactory.initElements(driver,this); */
	}

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement ordersValidation;

	public void waitUntilProductsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitUntilWebElementProductsToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public OrderpageTest ordersTestValidation() {
		ordersValidation.click();

		OrderpageTest orderpageTest = new OrderpageTest(driver);
		return orderpageTest;

	}

}

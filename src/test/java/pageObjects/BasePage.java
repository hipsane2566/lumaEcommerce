package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	public void implicitWaits() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String getTexts(WebElement elem) {
		return wait.until(ExpectedConditions.visibilityOf(elem)).getText();
	}
	
	public WebElement visibilityOfElem(WebElement elme) {
		return wait.until(ExpectedConditions.visibilityOf(elme));
	}
}

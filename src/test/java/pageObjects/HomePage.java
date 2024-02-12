package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[contains(@class,'panel header')]/descendant::a[3]")
	WebElement accountBtn;
	
	@FindBy(xpath="//div[@class='panel header']//descendant::span[2]")
	WebElement validationMsg;
	
	public void clickCreateAccount() {
		accountBtn.click();
	}
	
	@FindBy(xpath = "//div[contains(@class,'panel header')]/descendant::a[2]")
	WebElement signInBtn;
	
	public void clickSignIn() {
		signInBtn.click();
	}
	
	public void waitForElem() {
		visibilityOfElem(validationMsg);
	}
	
}

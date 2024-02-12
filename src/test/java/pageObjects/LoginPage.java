package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private @FindBy(id="email")
	WebElement txtEmail;
	
	private @FindBy(id="pass")
	WebElement txtPassword;
	
	private @FindBy(xpath ="//button[contains(@class,'primary')]")
	WebElement btnSignin;
	
	public void setEmailId(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickSignIn() {
		btnSignin.click();
	}
}

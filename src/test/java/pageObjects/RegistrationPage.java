package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends BasePage{
	WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	private @FindBy(id="firstname")
	WebElement txtFirstName;
	
	private @FindBy(id="lastname")
	WebElement txtLastName;
	
	private @FindBy(id="email_address")
	WebElement txtEmailAddress;
	
	private @FindBy(id="password")
	WebElement txtPassword;
	
	private @FindBy(id="password-confirmation")
	WebElement txtConfirmPassword;
	
	private @FindBy(how=How.XPATH, using="//button[contains(@class,'action submit primary')]") 
	WebElement btnCreateAccount;
	
	private @FindBy(how=How.XPATH, using="//div[contains(text(),'Thank you for registering with Main Website Store.')]")
	WebElement txtConfirmMsg;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmailAddress(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setConfirmPassword(String confPassword) {
		txtConfirmPassword.sendKeys(confPassword);
	}
	
	public void clickCreateAccount() {
		btnCreateAccount.click();
	}
	
	public String getConfirmationMsg() {
		String message = txtConfirmMsg.getText();
		return message;
	}
}

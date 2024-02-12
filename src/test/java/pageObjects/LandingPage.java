package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	private @FindBy(xpath="//div[@class='panel header']/descendant::span[2]")
	WebElement txtWelcomemsg;
	
	private @FindBy(xpath= "//div[@class='panel header']/descendant::button")
	WebElement headerLinks;
	
	private @FindBy(xpath= "//div[@class='panel header']/descendant::li[5]")
	WebElement linkSignout;
	
	
	public boolean getWelcomeMsg() {
		try {
			boolean status;
			Thread.sleep(5000);
			String txtmessage = getTexts(txtWelcomemsg);
			status = txtmessage.contains("Welcome");
			System.out.println(status);
			return status;
		}catch(Exception e) {
			return false;
		}	
	}
	
	public void clickSignout() {
		headerLinks.click();
		linkSignout.click();
		
	}
	
	


	
}

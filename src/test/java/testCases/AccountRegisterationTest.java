package testCases;

import org.testng.Assert;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class AccountRegisterationTest extends BaseClass {
	
	//Account Creation
	
	@Test(groups= {"sanity"})
	public void testCase01() {
		log.info("*******Test case TC_001_AccountRegisterationTest Started*******");
		log.debug("Log debug");
		try {
			HomePage homepage = new HomePage(driver);
			
			log.info("*******Clicked on CreateAccount*******");
			homepage.clickCreateAccount();
			percy.snapshot("navigate to account register page");
			//Enter firstname, lastname, email address and password
			RegistrationPage registerPage = new RegistrationPage(driver);
			log.info("*******Entering Registration Form Details*******");
			registerPage.setFirstName(randomString().toUpperCase());
			registerPage.setLastName(randomString().toUpperCase());
			registerPage.setEmailAddress(randomString()+"@gmail.com");
			
			String password = randomAlphaNumberic();
			registerPage.setPassword(password);
			registerPage.setConfirmPassword(password);
			
			//click on create account button
			log.info("*******Clicked on CreateAccount Button*******");
			registerPage.clickCreateAccount();
			
			implicitWaits();
			percy.snapshot("After Login");
			String confirmMsg = registerPage.getConfirmationMsg();
			String expMsg = "Thank you for registering with Main Website Store.";
			if(confirmMsg.equals(expMsg)) {
				log.info("*******Test Passed*******");
				Assert.assertTrue(true);
			}else {
				log.error("*******Test Failed*******");
				log.debug("*******Debug logs*******");
				Assert.fail();
			}
		}catch(Exception e) {
			log.error("*******Test is failed error*******");
			log.debug("*******Debug logs*******");
		}
		log.debug("*******Application logs end*******");
		log.info("*******Finished TC_001_AccountRegistrationTest*******");
		
	}
}

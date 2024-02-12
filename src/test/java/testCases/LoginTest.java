package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProvidersClass;

public class LoginTest extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass =DataProvidersClass.class, groups= {"regression"})
	public void testcase02(String email, String password, String expResult) {
		
		log.info("*****Login Test Case is Started*****");
		try{
			boolean status;
			HomePage homepage = new HomePage(driver);
			log.info("*****Clicked on Sign In Page*****");
			homepage.clickSignIn();
			
			LoginPage loginpage = new LoginPage(driver);
			log.info("*****Entering Login details*****");
			loginpage.setEmailId(email);
			loginpage.setPassword(password);
			
			log.info("*****Click on Login button*****");
			loginpage.clickSignIn();
			LandingPage landingpage = new LandingPage(driver);
			status = landingpage.getWelcomeMsg();
			if(expResult.equalsIgnoreCase("Valid")) {
				if(status==true) {
					log.info("*****Login Test Case is Passed with Valid Data*****");
					landingpage.clickSignout();
					homepage.waitForElem();
					Thread.sleep(5000);
					Assert.assertTrue(true);
				}else {
					log.info("*****Login Test Case is Failed with Valid Data*****");
					landingpage.clickSignout();
					Thread.sleep(5000);
					Assert.assertTrue(false);
				}
			}
			if(expResult.equalsIgnoreCase("Invalid")) {
				if(status==true) {
					log.info("*****Login Test Case is Failed with InValid Data*****");
					landingpage.clickSignout();
					Thread.sleep(5000);
					Assert.assertTrue(false);
				}else {
					log.info("*****Login Test Case is Passed with InValid Data*****");
					Thread.sleep(5000);
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			log.info("*****Error while executing login test case*****");
			Assert.fail(e.getMessage());
		}
		
		log.info("*****Login Test Case Executed *****");
		
		
	}
}

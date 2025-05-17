package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void LogTest_Validation(){
		 logger.info("Starting Login Test");
		 try {
		 HomePage hp = new HomePage(driver);
		 hp.clickLogin();
		 LoginPage lp = new LoginPage(driver);
		 lp.setEmailAddress(prop.getProperty("email"));
		 lp.setPassword(prop.getProperty("password"));
		 lp.clickLogin();
		 //Thread.sleep(3000);
		 MyAccountPage myp = new MyAccountPage(driver);
		 boolean targetPage = myp.isMyAccountTest();
		 Assert.assertEquals(targetPage, true,"Login failed.");
		 }
		 catch(Exception e) {
			 Assert.fail();
		 }
		 logger.info("Login Test finished.");
		
	}

}

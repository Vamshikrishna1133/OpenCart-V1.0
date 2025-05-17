package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTesting extends BaseClass{
	
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")
	public void Verify_loginDDT(String email, String pwd, String expresult){
		logger.info("**** Starting Test003_LoginDataDrivenTesting ***********");
		try {
		//Homepage
		 HomePage hp = new HomePage(driver);
		 hp.clickLogin();
		 
		 //loginpage
		 LoginPage lp = new LoginPage(driver);
		 lp.setEmailAddress(email);
		 lp.setPassword(pwd);
		 lp.clickLogin();
		 Thread.sleep(3000);
		 
		 //MyAccountpage
		 MyAccountPage myp = new MyAccountPage(driver);
		 boolean targetPage = myp.isMyAccountTest();
		 if(expresult.equalsIgnoreCase("Valid"))  //data is valid
		 {
			 if(targetPage==true) //valid data , logged in Test pass
			 {
				 myp.clicklogout();
				 logger.info("if(if(targetPage==true))");
				 Assert.assertTrue(true);
			 
			 }
			 else  //valid data , login failed, Test failed
			 {
				 logger.info("if(else(targetPage==false))");
				 Assert.assertTrue(false,"Test Failed");
			 }
		  }
		 else //Invalid data
		 {
			 if(targetPage==true)   //Invalid data , logged in Test fail
			 {
				myp.clicklogout();
				 logger.info("else(if(targetPage==true))");
				 Assert.assertTrue(false, "Test Failed"); 
			 }
			 else  			//Invalid data , login failed, Test passed.
			 {
				 logger.info("else(if(targetPage==false))");
				 Assert.assertTrue(true, "Test Failed");
			 }
		 }	
		 
		 
		} catch(Exception e)
		  {
			Assert.fail(); 
		  }
			logger.info("**** Test Execution Completed *********");
		
	}
	
}

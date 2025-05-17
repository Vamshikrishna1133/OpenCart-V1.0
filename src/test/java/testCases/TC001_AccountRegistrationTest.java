package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"Regression","Master"})
	public void verify_Account_Registration() {
		
		logger.info("Starting Test Case");
		try {
		HomePage hp = new HomePage(driver);
		logger.info("Clicking on my Account.");
		hp.clickMyAccount();
		logger.info("Clicking on Register.");
		hp.clickRegister();
		AccountRegistrationPage ap = new AccountRegistrationPage(driver);
		logger.info("Filling the required registration details.");
		ap.setFirstName(randomString(5).toUpperCase());
		ap.setLastName(randomString(5).toUpperCase());
		ap.setEmailAddress(randomString(5)+"@gmail.com");
		ap.setTelephone(randomNumeric(10));
		String pwd =randomAlphaNumeric(6);
		ap.setPassword(pwd);
		ap.setConfirmPassword(pwd);
		logger.info("Checking privacy policy.");
		ap.setPrivacyPolicy();
		logger.info("Clicking on continue.");
		ap.clickContinue();
		logger.info("Validating expected message..");
		String accountCreation=ap.getConfirmationMessage();
		String expectedMsg = "Your Account Has Been Created!";
		if(accountCreation.equals(expectedMsg)) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false,"Account creation failed");
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("Finished Test Execution successfully.");

	}
	
	
}

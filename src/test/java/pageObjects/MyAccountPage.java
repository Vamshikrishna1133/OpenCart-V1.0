package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") //My Account Page heading.
	WebElement msgMyAccount;

	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
	WebElement btnlogout;
	
	public boolean isMyAccountTest() {
		try {
		return msgMyAccount.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		btnlogout.click();
	}
}

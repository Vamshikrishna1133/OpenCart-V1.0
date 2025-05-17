package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//invoking the parent class driver
	public HomePage(WebDriver driver) {
		//super keyword refers to the immediate parent
		super(driver);
	}
	
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement linkMyAccount;

@FindBy(xpath="(//a[normalize-space()='Register'])")
WebElement linkRegister;

@FindBy(xpath="(//a[normalize-space()='Login'])")
WebElement linkLogin;


public void clickMyAccount(){
	linkMyAccount.click();
}

public void clickRegister() {
	linkRegister.click();
}

public void clickLogin() {
	clickMyAccount();
	linkLogin.click();
}
}

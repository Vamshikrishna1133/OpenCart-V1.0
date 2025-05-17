package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException
	{
		
		//Forloading config.propertiesfile
		String filePath = "./src//test//resources//config.properties";
		FileReader file = new FileReader(filePath);
		prop = new Properties();
		prop.load(file);
		
		//For logs related functionality like log.info("message"); etc
		logger = LogManager.getLogger(this.getClass());
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Invalid operating system input");
				return;
			}
			
			//browser
			switch(browser.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default : System.out.println("Invalide browser name"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.0.77:4444/wd/hub"),capabilities);
		}
		
		
		//For local execution
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(browser.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name...");return;
			}
		
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void teardown() {
		driver.quit();
	}
	
	
	public String randomString(int len) {
		String generatedString = RandomStringUtils.randomAlphabetic(len);
				return generatedString;
	}
	
	public String randomNumeric(int len) {
		String generatedNumericString = RandomStringUtils.randomNumeric(len);
		return generatedNumericString;
	}
	
	public String randomAlphaNumeric(int len) {
		String alphaNumeric = (randomString(len) + randomNumeric(len));
		
		//or we can use below method
		/*
		String alphaNumeric =  RandomStringUtils.randomAlphanumeric(len);
		*/
		return alphaNumeric;
	}
	
	public String captureScreen(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver; 
		
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_"+ timestamp;
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
			
	}
		
}

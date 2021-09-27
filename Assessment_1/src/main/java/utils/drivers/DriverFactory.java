package utils.drivers;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {

	private final String GECKO_DRIVER_DIRECTORY = System.getProperty("user.dir")
			+ "\\src\\main\\java\\utils\\drivers\\geckodriver.exe";
	private final String CHROME_DRIVER_DIRECTORY = System.getProperty("user.dir")
			+ "\\src\\main\\java\\utils\\drivers\\chromedriver.exe";
	public WebDriver getDriver(String browser,String downloadFilePath) {
	WebDriver driver = null;
	
	//ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	//WebDriver driver = threadLocalDriver.get();
	
	try {	
		switch(browser) {
		case "firefox":
			//code
			if(driver == null) {
				System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_DIRECTORY);
				FirefoxOptions options = new FirefoxOptions();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
				//0 means to download to the desktop, 1 means to download to the default "Downloads" directory, 2 means to use the directory
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.dir", downloadFilePath);
				profile.setAcceptUntrustedCertificates(true);
				options.setProfile(profile);
				driver = new FirefoxDriver(options);
				driver.manage().window().maximize(); 
			}
			break;
		case "chrome":
			//code
			if(driver==null) {
				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_DIRECTORY);
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilePath);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("ignore-certificate-errors");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize(); 
			}
			break;
		// use chrome if not indicated 	
		default:
			//code
			if(driver==null) {
				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_DIRECTORY);
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilePath);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("ignore-certificate-errors");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize(); 
			}
			break;	
		}
	}catch(Exception e) {
		System.out.println("fail to start browser..");
		e.printStackTrace();
	}finally{
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	}
	return driver;
}
}

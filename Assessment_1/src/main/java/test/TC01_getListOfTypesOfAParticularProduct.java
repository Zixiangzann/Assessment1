package test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.drivers.DriverFactory;

public class TC01_getListOfTypesOfAParticularProduct {
	
	Actions actions;
	String scriptPath = System.getProperty("user.dir");
	String ssPath = scriptPath + "\\src\\main\\java\\test\\Results\\Screenshots";
	
	@Parameters("browser")
	@Test(testName = "getListOfProduct", description = "Get the list of types of a particular product.")
	public void getList(@Optional("chrome")String browser) throws InterruptedException, IOException {
		DriverFactory driverFactory = new DriverFactory();
		WebDriver driver = driverFactory.getDriver(browser, ssPath + "\\downloads");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		HomePage hp = new HomePage(driver, wait, actions);
		driver.get("https://www.amazon.com/");
		hp.clickNavMainAllMenu();
		hp.expandAll();
		//We can change this part to fetch a particular product list of types
		hp.clickMainCat("Electronics");
		List<String> eleListOfProdType = hp.returnSubCat();
		System.out.print("Electronics list of types: ");
		System.out.println(eleListOfProdType);
		System.out.print("Count of Electronics list of types: ");
		System.out.println(eleListOfProdType.size());
		hp.backMainMenuClick();
		hp.clickMainCat("Smart Home");
		List<String> SHListOfProdType = hp.returnSubCat();
		System.out.print("Smart Home list of types: ");
		System.out.println(SHListOfProdType);
		System.out.print("Count of Smart Home list of types: ");
		System.out.println(SHListOfProdType.size());
		
		driver.quit();
		
	}

}

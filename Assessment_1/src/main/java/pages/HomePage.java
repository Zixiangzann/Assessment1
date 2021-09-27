package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	
	@FindBy(xpath = "//a[@id='nav-hamburger-menu']")
	WebElement NavMainAllMenu;
	
	@FindBy(xpath = "//div[@id='nav-xshop']//a[contains(@class,'')][contains(text(),\"Today\")]")
	WebElement NavMainTodayDeal;
	
	@FindBy(xpath = "//div[@id='nav-xshop']//a[contains(@class,'')][contains(text(),\"Customer Service\")]")
	WebElement NavMainCustomerService;
	
	@FindBy(xpath = "//div[@id='nav-xshop']//a[contains(@class,'')][contains(text(),\"Registry\")]")
	WebElement NavMainRegistry;
	
	@FindBy(xpath = "//div[@id='nav-xshop']//a[contains(@class,'')][contains(text(),\"Gift Cards\")]")
	WebElement NavMainGiftCards;
	
	@FindBy(xpath = "//div[@id='nav-xshop']//a[contains(@class,'')][contains(text(),'Sell')]")
	WebElement NavMainSell;
	
	@FindBy(xpath="//div[contains(@class,'nav-search-field')]")
	WebElement SearchField;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement SearchBtn;
	
	@FindBy(xpath="//ul[@class='hmenu hmenu-visible hmenu-translateX']//a[contains(@class,'item')][not(contains(@class,'back'))]")
	List<WebElement> subCat;
	
	@FindBy(xpath="//ul[contains(@class,'hmenu hmenu-visible hmenu-translateX')]//li//a[contains(@class,'hmenu-item hmenu-back-button')]")
	WebElement BackMainMenu;
	
	@FindBy(xpath="//i[@class='nav-sprite hmenu-arrow-more']")
	List<WebElement> SeeAll;
	
	public HomePage(WebDriver driver, WebDriverWait wait, Actions actions) {
		this.driver = driver;
		this.wait = wait;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickNavMainAllMenu() {
		NavMainAllMenu.click();
	}
	
	public void expandAll() {
		wait.until(ExpectedConditions.elementToBeClickable(SeeAll.get(0)));
		for(int i = 0; i<SeeAll.size();i++) {
				SeeAll.get(i).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void clickMainCat(String mainCat) {
		By mainCatXPath = By.xpath("//div[contains(text(),'"+mainCat+"')]");
		wait.until(ExpectedConditions.elementToBeClickable(mainCatXPath));
		driver.findElement(mainCatXPath).click();	
	}
	
	public List<String> returnSubCat() {
		wait.until(ExpectedConditions.visibilityOf(BackMainMenu));
		List<String> list = new ArrayList<String>();
		for(int i=0;i<subCat.size();i++){
			list.add(subCat.get(i).getText());
		}

		return list;
	}
	
	public void backMainMenuClick() {
		wait.until(ExpectedConditions.elementToBeClickable(BackMainMenu));
		BackMainMenu.click();
	}


}

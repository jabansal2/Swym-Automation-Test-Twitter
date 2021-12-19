package TestScripts.PageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestScripts.Resources.BaseTest;

public class ProfilePage{

	protected WebDriver driver;
	
	@FindBy(xpath = "//div[@data-testid='UserName']/div/div/div[2]/div/span")
	private WebElement profileUserName;
	
	@FindBy(xpath = "//div[@data-testid='SideNav_AccountSwitcher_Button']")///div[1]/div/div[2]/div/div[2]/div/div/div[4]/div")
	private WebElement accountButton;
	
	
	@FindBy(xpath = "//a[@href='/logout' and @data-testid='AccountSwitcher_Logout_Button']")
	private WebElement logOutButton;
	
	public ProfilePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getAccountName() {
		
		String s = profileUserName.getText();
		return s.substring(1, s.length());		
	}
	
	public boolean isPageOpened() {
		waitForVisibilityOfElement(profileUserName);
		System.out.println("step1");
		((JavascriptExecutor) driver).executeScript("return document.readyState");
		System.out.println("step2");
		return driver.getCurrentUrl().equals("https://twitter.com/" + getAccountName());
	}
	
	public void logout() {

		waitForVisibilityOfElement(accountButton);
		new Actions(driver).moveToElement(accountButton).build().perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", accountButton);
//		waitForVisibilityOfElement(logOutButton);
		new Actions(driver).moveToElement(logOutButton).build().perform();	
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", logOutButton);

	}
	
	public void waitForVisibilityOfElement(WebElement e) throws Error {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(e));
	}
}

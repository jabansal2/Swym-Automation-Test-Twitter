package TestScripts.PageObjectModels;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	
	private static String login_page_url = "https://twitter.com/";
			
	@FindBy(xpath = "//a[@data-testid='loginButton']")
	private WebElement signInButton;
	
	@FindBy(xpath = "//a[@data-testid='login']")
	private List<WebElement> loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get(login_page_url);
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		driver.get(login_page_url);
	}
	
	public boolean isPageOpened() {
		((JavascriptExecutor) driver).executeScript("return document.readyState");
		return driver.getTitle().contains("Twitter");
	}
	
	public void clickSignIn() {
		((JavascriptExecutor) driver).executeScript("return document.readyState");
		if(loginButton.size() != 0) {
			waitForVisibilityOfElement(loginButton.get(0));
			loginButton.get(0).click();
		}else {
			waitForVisibilityOfElement(signInButton);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", signInButton);
			new Actions(driver).moveToElement(signInButton).build().perform();
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", signInButton);
		}

	//	signInButton.click();
	}
	
	public void waitForVisibilityOfElement(WebElement e) throws Error {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(e));
	}
}

package TestScripts.PageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	protected WebDriver driver;
	
	@FindBy(xpath = "//input[@name = 'text' and @type='text']")
	private WebElement usernameButton;
	
	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//span[text()='Enter your phone number or username']")
	private WebElement accountUserNameText;
	
	@FindBy(xpath = "//span[text()='Enter your password']")
	private WebElement passwordText;
	
	@FindBy(xpath = "//input[@name='text']")
	private WebElement accountUserName;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement loginButton;
	
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPageOpened() {
//		((JavascriptExecutor) driver).executeScript("return document.readyState");
		waitForVisibilityOfElement(usernameButton);
		return driver.getCurrentUrl().equals("https://twitter.com/i/flow/login");
	}
	
	public void sendUserName(String username) {
//		waitForVisibilityOfElement(usernameButton);
		usernameButton.sendKeys(username);
	}
	
	public void clickNextButton() {
		waitForVisibilityOfElement(nextButton);
		nextButton.click();
	}
	
	public boolean isAccountUsernameAsked() {
		waitForVisibilityOfElement(accountUserName);
		return accountUserNameText.isDisplayed();
	}
	
	public void enterAccountUsername(String acctUserName) {
		accountUserName.sendKeys(acctUserName);
	}
	
	public boolean isPasswordAsked() {
		waitForVisibilityOfElement(passwordText);
		return passwordText.isDisplayed();
	}
	
	
	public void sendPassword(String passwd) {
		waitForVisibilityOfElement(password);
		password.sendKeys(passwd);
	}
	
	public void clickLoginButton() {
		waitForVisibilityOfElement(loginButton);
		loginButton.click();
	}
	
	public void waitForVisibilityOfElement(WebElement e) throws Error {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(e));
	}
}

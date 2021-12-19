package TestScripts.PageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage{

	protected WebDriver driver;
	
	
	@FindBy(xpath = "//div[@data-testid='confirmationSheetConfirm']")
	private WebElement logOutButton;
	
	@FindBy(xpath = "//div[@data-testid='confirmationSheetCancel']")
	private WebElement cancelButton;
	
	private static String logout_page_url = "https://twitter.com/logout";
	public LogOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPageOpened() {
		return driver.getCurrentUrl().equals(logout_page_url);
	}
	public void cancelLogOut() {
		cancelButton.click();
	}


	public void confirmLogOut() {
		logOutButton.click();
	}
}

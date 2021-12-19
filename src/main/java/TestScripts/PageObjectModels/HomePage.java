package TestScripts.PageObjectModels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestScripts.Resources.BaseTest;

public class HomePage{

	protected WebDriver driver;

	@FindBy(xpath = "//div[contains(@data-testid,'tweetTextarea')]")
	private WebElement tweetTextBox;

	@FindBy(xpath = "//div[@data-testid='tweetButtonInline']")
	private WebElement tweetButton;

	@FindBy(xpath = "//article[@data-testid='tweet']/div/div/div/div[2]/div[2]/div[2]/div[1]/div/span")
	private List<WebElement> postedTweet;
	
	@FindBy(xpath = "//input[@type=\"text\" and @data-testid=\"SearchBox_Search_Input\"]")
	private WebElement searchBar;

	@FindBy(xpath = "//div[@data-testid='clearButton']")
	private List<WebElement> clearSearchButton;

	@FindBy(xpath = "//a[@data-testid=\"AppTabBar_Profile_Link\"]")
	private WebElement profileButton;

	@FindBys(@FindBy(xpath = "//div[contains(@id,'typeaheadDropdown')]/div[@data-testid='typeaheadResult']/div[@role='button']/div[@data-testid='TypeaheadUser']/div/div[2]/div[1]/div/div/div[1]/div[1]/span/span"))
	private List<WebElement> searchList;

	@FindBy(xpath = "//div[contains(@id,'typeaheadDropdown')]/div[@data-testid='typeaheadResult']/div[@role='button']/div")
	private List<WebElement> textToSelect;

//	@FindBy(xpath = "//div[@role='listbox']/div")
	@FindBy(xpath="//div[contains(@id,'typeaheadDropdown')]/div")
	private List<WebElement> totalListBoxSize;

	@FindBy(xpath = "//div[contains(@id,'typeaheadDropdown')]/div[@data-testid='typeaheadResult']/div[@role='button']/div[@role='button']")
	private List<WebElement> listProfiles;

	private static String home_Page_Url = "https://twitter.com/home";

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isPageOpened() {
		return driver.getCurrentUrl().equals(home_Page_Url);
	}

	public void typeTweet(String tweetMessage) {
		waitForVisibilityOfElement(tweetTextBox);
		tweetTextBox.sendKeys(tweetMessage);
	}

	public void sendTweet() {
		tweetButton.click();
	}
	
	public boolean isTweetPosted(String tweetMsg) {
		((JavascriptExecutor) driver).executeScript("return document.readyState");
		driver.navigate().refresh();
		waitForVisibilityOfElement(tweetTextBox);
//		waitForVisibilityOfElement(tweetButton);
		String tweetPosted = postedTweet.get(0).getText();
		System.out.println("tweetPosted = " + tweetPosted);
		return tweetMsg.equals(tweetPosted);
		
	}

	public void typeSearchText(String text) {
		waitForVisibilityOfElement(searchBar);
		if(clearSearchButton.size() != 0) {
			clearSearchButton.get(0).click();
		}else {
			searchBar.clear();
		}
		
		searchBar.sendKeys(text);
	}

	public void clickProfile() {
		((JavascriptExecutor) driver).executeScript("return document.readyState");
		profileButton.click();
	}

	public void searchProfile(String optionToClick) throws InterruptedException {
		waitForVisibility(searchList);
		int diff = totalListBoxSize.size() - listProfiles.size();
		for (int i = 0; i < searchList.size(); i++) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchList.get(i));
			new Actions(driver).moveToElement(searchList.get(i)).build().perform();
			String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", searchList.get(i));
			if (text.equals(optionToClick)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", textToSelect.get(i + diff - 2));
			}
		}
		((JavascriptExecutor) driver).executeScript("return document.readyState");
	}

	public void waitForVisibility(List<WebElement> searchList2) throws Error {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(searchList2));
	}

	public void waitForVisibilityOfElement(WebElement e) throws Error {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(e));
	}

}

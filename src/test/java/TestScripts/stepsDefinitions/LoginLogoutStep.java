package TestScripts.stepsDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import TestScripts.PageObjectModels.HomePage;
import TestScripts.PageObjectModels.LogOutPage;
import TestScripts.PageObjectModels.LoginPage;
import TestScripts.PageObjectModels.ProfilePage;
import TestScripts.PageObjectModels.SignInPage;
import TestScripts.Resources.BaseTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginLogoutStep extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	SignInPage signinPage;
	ProfilePage profPage;
	LogOutPage logOutPage;

	@Given("^Open Login Page$")
	public void user_is_on_Login_Page() throws Throwable {
		driver = startDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		signinPage = new SignInPage(driver);
		profPage = new ProfilePage(driver);
		logOutPage = new LogOutPage(driver);
		
		loginPage.openPage();
//		assertEquals(loginPage.isPageOpened(), true, "Sign in Page is not opened");
		loginPage.clickSignIn();

	}

	@When("^User Provides Credentials with ([^\"]*), ([^\"]*) and ([^\"]*)$")
	public void user_Provides_Credentials_with_and(String email, String username, String password) throws Throwable {
		assertEquals(signinPage.isPageOpened(), true, "Sign in Page is not opened");
		signinPage.sendUserName(email);
		signinPage.clickNextButton();
		if (signinPage.isAccountUsernameAsked()) {
			signinPage.enterAccountUsername(username);
			signinPage.clickNextButton();
			signinPage.sendPassword(password);
		}
		else {
			signinPage.sendPassword(password);
		}
		signinPage.clickLoginButton();
	}

	@Then("^User login Successful and user is on Homepage$")
	public void user_login_Successful_and_user_is_on_Homepage() throws Throwable {
		homePage.isPageOpened();
	}

	@Then("^User Logs out$")
	public void user_Logs_out() throws Throwable {
		profPage.logout();
//		assertEquals(logOutPage.isPageOpened(), true, "Logout page is not opened");
		logOutPage.confirmLogOut();
//		driver.quit();
	}

	@Then("^User Sends a Tweet as ([^\"]*)$")
	public void user_Sends_a_Tweet(String tweet) throws Throwable {
		homePage.typeTweet(tweet);
		homePage.sendTweet();
		assertEquals(homePage.isTweetPosted(tweet), true, "Tweet posted is different from expected");
	}

	@Then("^User Searches with Text ([^\"]*) for profile ([^\"]*)$")
	public void user_Searches_with_Text_for_profile(String searchText, String profile)
			throws Throwable {
		homePage.typeSearchText(searchText);
		homePage.searchProfile(profile);
	}

	@Then("^User confirms landing on the profile page$")
	public void user_confirms_landing_on_the_profile_page() throws Throwable {
		assertEquals(profPage.isPageOpened(), true, "Logout page is not opened");
	}

	@Then("^User closes the browser$")
	public void closeBrowser() {
		driver.quit();
	}

}

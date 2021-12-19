package TestScripts.SwymAutomation.TestScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestScripts.PageObjectModels.HomePage;
import TestScripts.PageObjectModels.LogOutPage;
import TestScripts.PageObjectModels.LoginPage;
import TestScripts.PageObjectModels.ProfilePage;
import TestScripts.PageObjectModels.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import TestScripts.Resources.BaseTest;
public class TestCases extends BaseTest{

	WebDriver driver;

//	@BeforeTest
	public void setup() throws IOException {
//		System.setProperty("webdriver.gecko.driver", "C:\\Softwares\\SeleniumDrivers\\geckodriver.exe");
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
//		driver = startDriver();
	}

//	@BeforeMethod
	public void waitimplict() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test()
	public void tc_tc1a_clickSignInButton() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.isPageOpened();
		loginPage.clickSignIn();
	}
	
	
//	@Test
	public void tc_tc1b_signinPage() throws InterruptedException {
		SignInPage signinPage = new SignInPage(driver);
		signinPage.isPageOpened();
		String username = "aqua19841bansal.jatin@gmail.com";
		signinPage.sendUserName(username);
		signinPage.clickNextButton();
		if (signinPage.isAccountUsernameAsked()) {
			signinPage.enterAccountUsername("aqua19841bansal");
			signinPage.clickNextButton();
			signinPage.sendPassword("Password@1234");
		}
		/* if(signinPage.isPasswordAsked()) */
		else {
			signinPage.sendPassword("Password@1234");
		}
		signinPage.clickLoginButton();
	}
	
//	@Test
	public void tc_tc1c_homePage() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.isPageOpened();
		homePage.typeTweet("Hello Twitter");
		homePage.sendTweet();
		homePage.isTweetPosted("Hello Twitter");
		homePage.typeSearchText("Unity");	
		
		homePage.typeSearchText("Narendra");
		homePage.searchProfile("PMO India");
	}
	
//	@Test
	public void tc_tc1d_profilePage() {
		ProfilePage profPage = new ProfilePage(driver);
		profPage.isPageOpened();
		profPage.logout();
	}
	
//	@Test
	public void tc_tc1e_logout() {
		LogOutPage logOutPage = new LogOutPage(driver);
		logOutPage.isPageOpened();
		logOutPage.isPageOpened();
		logOutPage.confirmLogOut();
	}
	
//	@AfterTest
	public void closeDriver() {
		driver.close();
	}

}

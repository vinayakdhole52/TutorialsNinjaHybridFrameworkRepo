package com.TutorialsNinja.qa.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.Utils.Utilities;
import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.AccountPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.LoginPage;

public class LoginTest extends Base {
	
	LoginPage loginPage;
		
	public LoginTest() throws IOException {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void Setup() throws IOException {

		driver = initializeBrowserAndOpenApplicationURl(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		AccountPage accountPage=loginPage.Login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() throws IOException {
		// Object[][] data= {{"vinayakdhole1674@gmail.com", "Vinayak@52"},
		// {"vinayakdhole1675@gmail.com", "Vinayak52@"},{"vinayakdhole1676@gmail.com",
		// "Vinayak52@"}};
		Object[][] data = Utilities.getTestDataFromExcel("LoginSheet");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		loginPage.Login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("InvalidPassword"));
//		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
//		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
//				"expected Warning Message is not displayed");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"expected Warning Message is not displayed");


	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {

		loginPage.Login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
//		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
//		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
//				"expected Warning Message is not displayed");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"expected Warning Message is not displayed");


	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {

		loginPage.Login(prop.getProperty("validEmail"), dataprop.getProperty("InvalidPassword"));
//		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
//		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
//				"expected Warning Message is not displayed");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"expected Warning Message is not displayed");


	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginPage.clickOnLoginButton();

//		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
//		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"expected Warning Message is not displayed");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

package com.TutorialsNinja.qa.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.Utils.Utilities;
import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.AccountSuccessPage;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.RegisterPage;

public class RegisterTest extends Base {

	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() throws IOException {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void Setup() throws IOException {
		driver = initializeBrowserAndOpenApplicationURl(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualSuccesHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccesHeading, dataprop.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success Page is not Displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllFields() {
		accountSuccessPage = registerPage.registerWithAllFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualSuccesHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccesHeading, dataprop.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success Page is not Displayed");
	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		registerPage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),
				prop.getProperty("validEmail"), dataprop.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"));

		// String actualWarning = registerPage.retrievDuplicateEmailAddressWarning();
		Assert.assertTrue(registerPage.retrievDuplicateEmailAddressWarning()
				.contains(dataprop.getProperty("duplicateEmailWarning")), "Warning message is not displayed");
	}

	@Test(priority = 4)
	public void RegisteringAnAccountWithoutFillingAnyDetails() {
		registerPage.clickOnContinueButon();

		// String actualPrivacyPolicyWarning =
		// registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(
				registerPage.retrievePrivacyPolicyWarning().contains(dataprop.getProperty("privacyPolicyWarning")),
				"Privacy Policy Warning Message is not Displayed ");

		// String actualFirstNameWarning = registerPage.retrieveFirstNameWanring();
		Assert.assertEquals(registerPage.retrieveFirstNameWanring(), dataprop.getProperty("firstNameWarning"),
				"First Name Warning Message is not Displayed");

		// String actualLastNameWarning = registerPage.retrieveLastNameWanring();
		Assert.assertEquals(registerPage.retrieveLastNameWanring(), dataprop.getProperty("lastNameWarning"),
				"Last Name Warning Message is not Displayed");

		// String actualEmailWarning = registerPage.retrieveEmailWanring();
		Assert.assertEquals(registerPage.retrieveEmailWanring(), dataprop.getProperty("emailWarning"),
				"Email Warning Message is not Displayed");

		// String actualTelephoneWarning = registerPage.retrieveTelephoneWanring();
		Assert.assertEquals(registerPage.retrieveTelephoneWanring(), dataprop.getProperty("telephoneWarning"),
				"Telephone Warning Message is not Displayed");

		// String actualPasswordWarning = registerPage.retrievePasswordWanring();
		Assert.assertEquals(registerPage.retrievePasswordWanring(), dataprop.getProperty("passwordWarning"),
				"Password Warning Message is not Displayed");

	}

}

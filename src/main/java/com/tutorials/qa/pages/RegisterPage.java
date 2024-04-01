package com.tutorials.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWanring;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWanring;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWanring;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWanring;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWanring;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String retrieveFirstNameWanring() {
		String firstNameWanringText = firstNameWanring.getText();
		return firstNameWanringText;
	}

	public String retrieveLastNameWanring() {
		String lastNameWanringText = lastNameWanring.getText();
		return lastNameWanringText;
	}

	public String retrieveEmailWanring() {
		String emailWanringText = emailWanring.getText();
		return emailWanringText;
	}

	public String retrieveTelephoneWanring() {
		String telephoneWanringText = telephoneWanring.getText();
		return telephoneWanringText;
	}

	public String retrievePasswordWanring() {
		String passwordWanringText = passwordWanring.getText();
		return passwordWanringText;
	}

	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lasttNameText) {
		lastNameField.sendKeys(lasttNameText);
	}

	public void enterEmailAddress(String emailtext) {
		emailAddressField.sendKeys(emailtext);
	}

	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButon() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsletterOption() {
		yesNewsletterOption.click();
	}

	public String retrievDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarningText;
	}

	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lasttNameText, String emailtext,
			String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lasttNameText);
		emailAddressField.sendKeys(emailtext);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);

	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText, String lasttNameText, String emailtext,
			String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lasttNameText);
		emailAddressField.sendKeys(emailtext);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		yesNewsletterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);

	}

}

package com.TutorialsNinja.qa.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.Base;
import com.tutorials.qa.pages.HomePage;
import com.tutorials.qa.pages.SearchPage;
//Take a comment
public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() throws IOException {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() throws IOException {
		driver = initializeBrowserAndOpenApplicationURl(prop.getProperty("browser"));
		homePage=new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage=homePage.searchForAProduct(dataprop.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage=homePage.searchForAProduct(dataprop.getProperty("invalidProduct"));
   //     String actulaSearchMessage=searchPage.retrieveNoProductMessagetext();
		Assert.assertEquals(searchPage.retrieveNoProductMessagetext(), "abcd",
				"No Product Message in Search Result is not displayed");
	}

	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {	
		searchPage=homePage.clickOnSearchButton();
//        String actulaSearchMessage=searchPage.retrieveNoProductMessagetext();
		Assert.assertEquals(searchPage.retrieveNoProductMessagetext(), dataprop.getProperty("NoProductTextInSearchResult"),
				"No Product Message in Search Result is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

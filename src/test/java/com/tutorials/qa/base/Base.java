package com.tutorials.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.TutorialsNinja.qa.Utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public Base() throws IOException
	{
		prop = new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorials\\qa\\config\\Config.properties");
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
		
		dataprop = new Properties();
		File datapropfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorials\\qa\\testdata\\testdata.properties");
		FileInputStream datafile=new FileInputStream(datapropfile);
		dataprop.load(datafile);
		
	}

	public WebDriver initializeBrowserAndOpenApplicationURl(String browserName ) {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	public void loadPropertiesFile() throws IOException
	{
		
	}

}

package com.TutorialsNinja.qa.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() throws IOException {
		
	ExtentReports extentReport= new ExtentReports();

	File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("TutorialsNinja Test Automation Results");
	sparkReporter.config().setDocumentTitle("TN Automation Report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp=new Properties();
	File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorials\\qa\\config\\Config.properties");
	FileInputStream fisConfigProp=new FileInputStream(configPropFile);
	configProp.load(fisConfigProp);
	
	
	extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
	extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
	extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentReport.setSystemInfo("Username ", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Vesrion", System.getProperty("java.version"));

	return extentReport;

}
}
package com.framework.base;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.framework.utilities.FetchTestData;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya
 * 
 *         All tests Extend Base Class and invoke test pre-requisites and
 *         post-execution steps
 *
 */
public class Base extends Global {

	/**
	 * Initialize Page Objects and Input data Before Executing the test
	 */
	@BeforeClass
	public void preapareTestClass() {
		classname = this.getClass().getSimpleName();
		sf = new SFDC_AllPageObjects();
		getTestData();
	}

	/**
	 * @return
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * 
	 *                               Configure the Test, Such as Browser
	 *                               Initialization, Initializing Reports to capture
	 *                               Test Results
	 */
	@BeforeMethod(alwaysRun = true)
	public WebDriver configureTest() throws InterruptedException, FileNotFoundException {
		initializeReportScreenAndData();
		initializeBrowser();
		setBrowserInfo();
		return driver;

	}

	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 *                                Save Test Results, Closes The driver and
	 *                                Insert the execution to DB for future tracking
	 */
	@AfterMethod(alwaysRun = true)
	public void finishTest() throws IOException, ClassNotFoundException {
		ScreenDocs.saveScreenDoc(classname);
		driver.quit();
		test.assignCategory(Global.dataInput.category);
		FetchTestData.mpTestData.clear();
		if (InputData.insertRecordToDB.equals("Yes")) {
			Global.dataInput.addTestResultToDB();
		}

	}

	/**
	 * Kill the Opened Browser Instance
	 */
	@AfterSuite
	public void cleanUpSuite() {
		cleanChrome();
	}

}
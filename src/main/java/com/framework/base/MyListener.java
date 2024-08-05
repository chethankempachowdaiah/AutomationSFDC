package com.framework.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.DateTimeUtilities;
import com.framework.utilities.GarbageCollector;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;

/**
 * @author Priyanka.Acharya
 * 
 *         Test Listener used for reporting and capturing exceptions and
 *         failures
 *
 */
public class MyListener extends Base implements ITestListener, IInvokedMethodListener {

	/**
	 * Invoked Once the test is started for test initialization, extent report
	 * initialization, data input setup and other time stamp capture
	 */
	public void onTestStart(ITestResult result) {
		testName = result.getMethod().getMethodName();
		logger.info("==================on test start===================================");
		logback.info("==================on test start===================================");
		test = reports.createTest(result.getMethod().getMethodName());
		// test.log(Status.INFO, result.getMethod().getMethodName() + " test is
		// started");

		addOn_1 = AdditionalUtilities.currentSystemTime("yyMMddhh") + AdditionalUtilities.generateRandomDigits(4);
		addOn_2 = AdditionalUtilities.currentSystemTime("yyMMddhh") + AdditionalUtilities.generateRandomDigits(5);
		addOn_3 = AdditionalUtilities.currentSystemTime("yyMMddhh") + AdditionalUtilities.generateRandomDigits(3);
		
		getTestData();
		testStartTime = DateTimeUtilities.currentSystemDate(InputData.db_timeFormat);
		failReason = "";
	}

	/**
	 * Upon Successful execution,it sets the test status as PASS
	 */
	public void onTestSuccess(ITestResult result) {
		logger.info("=================on test success==================================");
		logback.info("=================on test success==================================");
		test.log(Status.PASS, result.getMethod().getMethodName() + " test is passed");
		reports.flush();
		testStatus = "PASS";
		testEndTime = DateTimeUtilities.currentSystemDate(InputData.db_timeFormat);

	}

	/**
	 * Invoked if the test is failed and capture failed step details and sets status
	 * as FAIL
	 */
	public void onTestFailure(ITestResult result) {
		logger.info("=================on test failure==================================");
		logback.info("=================on test failure==================================");
		reports.flush();
		try {
			ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric,
					result.getMethod().getMethodName() + "test is failed");
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getMethod().getMethodName() + " test is failed", ExtentColor.RED));
			String screenshotPath = getScreenShot("");
			test.fail("screenshot Here :", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

			softassert.assertTrue(false);
			testStatus = "FAIL";
			testEndTime = DateTimeUtilities.currentSystemDate(InputData.db_timeFormat);
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invoked if the test is skipped
	 */
	public void onTestSkipped(ITestResult result) {
		logger.info("======================on test skipped===============================");
		logback.info("======================on test skipped===============================");
		test.log(Status.SKIP, result.getMethod().getMethodName() + " test is skipped");
		reports.flush();
		InputData.insertRecordToDB = "No";
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("======================on test sucess within percentage==============");
		logback.info("======================on test sucess within percentage==============");
	}

	/**
	 * Invoking during the beginning of the test execution , sets up the extent
	 * report and clean up the report dierctory
	 */
	public void onStart(ITestContext context) {
		try {
			initializeConfig();
			logger.info("=======================on start=====================================");
			logback.info("=======================on start=====================================");
			htmlReporter = new ExtentHtmlReporter(Constants.USER_DIR + Constants.EXECUTION_SUMMARY_REPORT);
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);

			htmlReporter.config().setDocumentTitle("Test Execution Summary Report");
			htmlReporter.config().setReportName("Test Execution Summary Report ");
			htmlReporter.config().setTheme(Theme.STANDARD);
			Base.screenShotDir = "\\Automation_MP_ScreenShots\\"
					+ AdditionalUtilities.currentSystemTime("dd-MM-yyyy" + "_" + "hh-mm-ss_a") + "\\";
			deleteExistingFilesInFolder(Constants.REPORTS_DIR + Base.screenShotDir);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Once the Execution is done, it will set the system information in Report
	 */
	public void onFinish(ITestContext context) {
		logger.info("======================on finish=====================================");
		logback.info("======================on finish=====================================");

		reports.setSystemInfo("Environment", InputData.env);
		reports.setSystemInfo("OS", InputData.operatingSystem);
		reports.setSystemInfo("Application", InputData.applicationName);
		reports.setSystemInfo("Browser", InputData.browser);

		reports.setSystemInfo("Automation Tool", InputData.automationTool);
		reports.setSystemInfo("Test Type", InputData.testType);
		reports.setSystemInfo("Project", InputData.project);
		reports.setSystemInfo("Release", InputData.release);

	}

	/**
	 * Method id Invoked before the test and It performs the clean up for garbage
	 * collector
	 */
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		System.out.println("beforeInvocation: runs before every method in the Test Class");
		GarbageCollector.errorBag.clear();
		failedStep = 0;
	}

	/**
	 * Method is invoked after the test and capture the failure if any
	 */
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		System.out.println("afterInvocation: runs after every method in the Test Class");
		if (GarbageCollector.errorBag.size() > 0 || failedStep > 0) {
			testResult.setStatus(ITestResult.FAILURE);
		} else {
			testResult.setStatus(ITestResult.SUCCESS);
		}

	}

	/**
	 * @param passLog
	 * @param screenshot
	 * @param isValidationStep
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              It reports pass status of the test step in the
	 *                              report and generate logs
	 */
	public static void reportStatusPass(String passLog, boolean screenshot, boolean isValidationStep)
			throws IOException, InterruptedException {
		logger.info(passLog);
		logback.info(passLog);
		try {

			if (isValidationStep) {
				test.log(Status.PASS, MarkupHelper.createLabel(passLog, ExtentColor.GREEN));

				ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, passLog);

			}

			if (screenshot) {
				String screenshotPath = getScreenShot("");
				test.pass(passLog, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			if ((!isValidationStep) && (!screenshot)) {
				test.log(Status.PASS, passLog);
			}

		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param passLog
	 * @param stepName
	 * @param isValidationStep
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 *                              It reports pass status of the test step in the
	 *                              report and generate logs
	 * 
	 */
	public static void reportStatusPass(String passLog, String stepName, boolean isValidationStep)
			throws IOException, InterruptedException {
		logger.info(passLog);
		logback.info(passLog);
		try {
			if (isValidationStep) {
				test.log(Status.PASS, MarkupHelper.createLabel(passLog, ExtentColor.GREEN));
				ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, passLog);
			} else {
				test.log(Status.PASS, passLog);
			}

			String screenshotPath = getScreenShot(stepName);
			test.pass(passLog, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

		} catch (InvalidFormatException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @param failLog
	 * @param e
	 * @throws IOException
	 * 
	 *                     It reports fail status of the test step in the report and
	 *                     generate logs and add the exception in the log
	 */
	public static void reportStatusFail(String failLog, Throwable e) throws IOException {
		logger.info(failLog);
		logback.info(failLog);

		try {
			GarbageCollector.addVerificationFailure(e);
			ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, failLog);

			test.log(Status.FAIL, MarkupHelper.createLabel(failLog, ExtentColor.RED));
			String screenshotPath = getScreenShot("");
			test.fail(e.toString(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			failReason = failLog;
			softassert.assertTrue(false);

		} catch (InvalidFormatException ie) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param failLog
	 * @param Screenshot
	 * @throws IOException
	 * 
	 *                     It reports fail status of the test step in the report and
	 *                     generate logs with the screenshots
	 * 
	 */
	public static void reportStatusFail(String failLog, boolean Screenshot) throws IOException {

		logger.info(failLog);
		logback.info(failLog);
		failedStep++;
		test.log(Status.FAIL, MarkupHelper.createLabel(failLog, ExtentColor.RED));
		try {
			if (Screenshot) {
				ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, failLog);
				String screenshotPath = getScreenShot("");
				test.fail("", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			failReason = failLog;
			softassert.assertTrue(false);

		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param stepName
	 * @return
	 * @throws IOException
	 * 
	 *                     Capture the screenshot of the test step
	 */
	public static String getScreenShot(String stepName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination;

		if (InputData.mailableReport.equalsIgnoreCase("Yes")) {
			destination = "C:\\" + Base.screenShotDir + dateName + stepName + ".png";

		} else {
			destination = Constants.REPORTS_DIR + Base.screenShotDir + dateName + stepName + ".png";

		}
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		/*
		 * Screenshot fpScreenshot = new
		 * AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
		 * .takeScreenshot(driver); ImageIO.write(fpScreenshot.getImage(), "png",
		 * finalDestination);
		 */
		return destination;
	}
	
	/**
	 * @param InfoLog
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              It reports Info status of the test step in the
	 *                              report and generate logs
	 */
	public static void reportStatusInfo(String infoLog, boolean screenshot)
			throws IOException, InterruptedException {
		logger.info(infoLog);
		logback.info(infoLog);
		try {
				test.log(Status.INFO, MarkupHelper.createLabel(infoLog, ExtentColor.AMBER));
				ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, infoLog);

			if (screenshot) {
				String screenshotPath = getScreenShot("");
				test.pass(infoLog, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

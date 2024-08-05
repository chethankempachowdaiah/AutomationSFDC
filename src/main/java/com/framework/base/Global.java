package com.framework.base;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.utilities.FetchTestData;
import com.framework.utilities.ScreenDocs;
import com.framework.utilities.XlsAction;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_OM;
import com.sfdc.data.InputData_PartnerCommunities;
import com.sfdc.data.InputData_QM;
import com.sfdc.data.InputData_Sales;
import com.sfdc.data.InputData_Service;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya
 * 
 * 
 *         Global is parent class of every class. Common Objects declared in
 *         Global as staic varibales and initialised in subsequnet child class
 *
 */
public class Global {

	public static WebDriver driver;
	public static Actions action;
	public static String dataFile;

	public static XWPFDocument docxGeneric = null;
	public static XWPFRun runGeneric = null;
	public static FileOutputStream outGeneric = null;

	public static XWPFDocument docxDataSpecific = null;
	public static XWPFRun runDataSpecific = null;
	public static FileOutputStream outDataSpecific = null;

	public static String testName;
	public static String classname;

	public static String browserInfo;
	public static String browserResolution;
	public static String platformInfo;
	public static String screenResolution;

	public static Logger logback;
	public static org.apache.logging.log4j.Logger logger;
	public static SoftAssert softassert;

	public static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports reports;
	public static ExtentTest test;
	protected static int failedStep;
	public static String screenShotDir;

	public static SFDC_AllPageObjects sf;
	public static String addOn_1;
	public static String addOn_2;
	public static String addOn_3;
	
	public static InputData dataInput;
	public static InputData_Communities commData;
	public static InputData_Service serviceData;
	public static InputData_Sales salesData;
	public static InputData_QM qmData;
	public static InputData_OM omData;
	public static InputData_WA waData;
	public static InputData_PartnerCommunities partnerCommData;

	public static String testStatus;
	public static String testStartTime;
	public static String testEndTime;
	public static String executionTime;
	public static String failReason;

	public void getTestData() {
		dataInput = new InputData();
		commData = new InputData_Communities();
		serviceData = new InputData_Service();
		salesData = new InputData_Sales();
		qmData = new InputData_QM();
		omData = new InputData_OM();
		waData = new InputData_WA();
	}

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 * 
	 *                     Read Test Data and assign it to a 2D array based on the
	 *                     testdata file and sheet name provided
	 */
	public Object[][] getDataSets(String fileName, String sheetName) throws IOException {
		XlsAction datasets = new XlsAction(fileName, sheetName);

		int dataRowCount = datasets.getRowCount(sheetName);
		int dataColumnCount = datasets.getColumnCount(sheetName);

		System.out.println("Total Rows................." + dataRowCount);
		System.out.println("Total Columns.............." + dataColumnCount);

		Object dataSet[][] = new Object[dataRowCount - 1][1];
		Hashtable<String, String> dataTable;
		int dataSetCount = 0;

		for (int i = 2; i <= dataRowCount; i++) {

			dataTable = new Hashtable<String, String>();

			for (int j = 0; j < dataColumnCount; j++) {
				dataTable.put(datasets.getCellData(sheetName, 1, j), datasets.getCellData(sheetName, i, j));
			}

			dataSet[dataSetCount][0] = dataTable;
			dataSetCount++;

		}

		for (int k = 0; k < dataSet.length; k++) {
			System.out.println(dataSet[k][0]);
		}
		return dataSet;
	}

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 * 
	 *                     Read Test Data and assign it to a 2D array based on the
	 *                     testdata file , sheet name provided and the Run mode of
	 *                     the given test scenario
	 */
	public Object[][] getDataSetsRunMode(String fileName, String sheetName) throws IOException {
		XlsAction datasets = new XlsAction(fileName, sheetName);

		int dataRowCount = datasets.getRowCount(sheetName);
		int dataColumnCount = datasets.getColumnCount(sheetName);

		System.out.println("Total Rows................." + dataRowCount);
		System.out.println("Total Columns.............." + dataColumnCount);

		Object dataSet[][] = new Object[dataRowCount - 1][1];
		Hashtable<String, String> dataTable;
		int dataSetCount = 0;

		for (int i = 2; i <= dataRowCount; i++) {

			dataTable = new Hashtable<String, String>();

			for (int j = 0; j < dataColumnCount; j++) {
				dataTable.put(datasets.getCellData(sheetName, 1, j), datasets.getCellData(sheetName, i, j));
			}

			if (dataTable.get("RunMode").equalsIgnoreCase("Yes")) {
				dataSet[dataSetCount][0] = dataTable;
				dataSetCount++;
			}
		}

		Object dataSetWithoutNull[][] = new Object[dataSetCount][1];

		for (int k = 0; k < dataSet.length; k++) {
			if (dataSet[k][0] == null) {
				break;
			} else {
				dataSetWithoutNull[k][0] = dataSet[k][0];
			}
		}
		System.out.println(dataSetWithoutNull.length);
		for (int k = 0; k < dataSetWithoutNull.length; k++) {
			System.out.println(dataSetWithoutNull[k][0]);
		}
		return dataSetWithoutNull;
	}

	public static void saveTestData(String fileName, String sheetName, String dataKey, String dataValue, int dataRow)
			throws IOException, InterruptedException {
		try {

			XlsAction testDataReader = new XlsAction(fileName, sheetName);
			testDataReader.setCellData(dataValue, sheetName, dataRow, dataKey);
			System.out.println("Saved  " + dataValue + " as " + dataKey + " in the Data File");

		} catch (FileNotFoundException e) {
			System.out.println("Please Close the file to write into it");
			e.printStackTrace();
		}
	}

	/**
	 * @throws FileNotFoundException
	 * 
	 *                               This function will initialize the Screendoc to
	 *                               create wordinstance for screenshots capture in
	 *                               respective word doc for each test case
	 */
	public void initializeReportScreenAndData() throws FileNotFoundException {
		classname = this.getClass().getSimpleName();
		ScreenDocs.createScreenDoc(classname);
		FetchTestData.loadAllVariablesAndConstants();

	}

	/**
	 * @throws FileNotFoundException
	 * 
	 *                               Performs all necessary Configuration Before
	 *                               Starting the Execution
	 */
	public void initializeConfig() throws FileNotFoundException {
		logback = LoggerFactory.getLogger(Constants.LOGGER_ROLLINGFILE);
		logger = LogManager.getLogger(Base.class);
		softassert = new SoftAssert();
	}

	/**
	 * Open the Browser based on the input
	 * 
	 * 1- For chorme, It opens up ChromeDriver
	 *
	 * 2- For Firefox, It opens up Firefox/GeckoDriver
	 *
	 * 3- For IE, It opens up InternetExplorerDriver
	 * 
	 */
	public void initializeBrowser() {
		try {

			// Open Chrome Browser
			if (InputData.browser.equalsIgnoreCase("chrome")) {
				createDirectory(Constants.REPORTS_DIR, Constants.DOWNLOADS_DIR);
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", Constants.DOWNLOADS_LOCATION);
				prefs.put("download.prompt_for_download", false);
				prefs.put("download.directory_upgrade", true);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("user-data-dir=" + Constants.CHROME_USERDATA_DIR);
				options.addArguments("--start-maximized");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--force-device-scale-factor=1");
				options.addArguments("--disable-gpu");
				driver = new ChromeDriver(options);
			}
			if (InputData.browser.equalsIgnoreCase("Headless")) {
				// driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir=" + Constants.CHROME_USERDATA_DIR);
				options.addArguments("--start-maximized");
				options.addArguments("--window-size=1920,1200");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				driver = new ChromeDriver(options);
			}

			// Open Firefox Browser
			if (InputData.browser.equalsIgnoreCase("mozilla") || InputData.browser.trim().equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER);
				driver = new FirefoxDriver();
			}

			// Open InternetExplorer Browser
			if (InputData.browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", Constants.IEDRIVER);
				InternetExplorerOptions options = new InternetExplorerOptions()
						.introduceFlakinessByIgnoringSecurityDomains();
				driver = new InternetExplorerDriver(options);
			}
			if (InputData.browser.equalsIgnoreCase("SauceLabsChrome")) {
				
			 	createDirectory(Constants.REPORTS_DIR, Constants.DOWNLOADS_DIR);
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", Constants.DOWNLOADS_LOCATION);
				prefs.put("download.prompt_for_download", false);
				prefs.put("download.directory_upgrade", true);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("user-data-dir=" + Constants.CHROME_USERDATA_DIR);
				options.addArguments("--start-maximized");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--force-device-scale-factor=1");
				options.addArguments("--disable-gpu");
				options.setCapability("platform", "Windows 10");
				driver = new RemoteWebDriver(new URL(InputData.sauceLabsURL), options);
			}

			navigateURL();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Unable to initialize Browser : " + InputData.browser);
		}
	}
	/**
	 * Open Mozilla Firfox based on the parameter provided for Incognito mode.
	 * 
	 * If True, It will Open up an Incognito Browser, If No , It will Open up a
	 * Regular Browser
	 *
	 * 
	 */
	public void initializeMozilla(boolean incognito) {
		try {

			// Open Firefox Browser
			if (incognito) {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER);
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("browser.privatebrowsing.autostart", true);
				driver = new FirefoxDriver(capabilities);
			} else {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER);
				driver = new FirefoxDriver();
			}

			// Navigate Application URL and Set Browser Info
			navigateURL();
			setBrowserInfo();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Unable to initialize Mozilla Browser ");
		}
	}

	/**
	 * Opne Chrome based on the parameter provided for Incognito mode.
	 * 
	 * If True, It will Open up an Incognito Browser, If No , It will Open up an
	 * Regular Browser
	 * 
	 */
	public WebDriver intializeChrome(boolean incognito) {
		try {

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
			ChromeOptions options = new ChromeOptions();

			if (incognito) {
				options.addArguments("incognito");
			} else {
				options.addArguments("user-data-dir=" + Constants.CHROME_USERDATA_DIR);
			}

			options.addArguments("--start-maximized");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--force-device-scale-factor=1");
			options.addArguments("--disable-gpu");
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);

			// Navigate Application URL and Set Browser Info
			navigateURL();
			setBrowserInfo();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Unable to initialize Browser : Chrome");
		}
		return driver;
	}
	
	/**
	 * Open Mozilla Firfox based on the parameter provided for Incognito mode.
	 * 
	 * If True, It will Open up an Incognito Browser, If No , It will Open up a
	 * Regular Browser
	 *
	 * 
	 */
	public void initializeMozilla(boolean incognito, boolean isSauceTest) {
		try {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			if (incognito) 
				capabilities.setCapability("browser.privatebrowsing.autostart", true);
			
			// Open Firefox Browser
			if (isSauceTest) {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER);
				capabilities.setCapability("geckodriverversion", "0.26.0");
	            FirefoxOptions firefoxOptions = new FirefoxOptions();
	            firefoxOptions.setCapability("platformName", "Windows 10");
	            firefoxOptions.setCapability("browserVersion", "92.0");
	            firefoxOptions.setCapability("sauce:options", capabilities);
	            driver = new RemoteWebDriver(new URL(InputData.sauceLabsURL), firefoxOptions);
	            
			} else {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER);

				driver = new FirefoxDriver(capabilities);
			}
			
            
			// Navigate Application URL and Set Browser Info
			navigateURL();
			setBrowserInfo();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Unable to initialize Mozilla Browser ");
		}
	}

	/**
	 * Opne Chrome based on the parameter provided for Incognito mode.
	 * 
	 * If True, It will Open up an Incognito Browser, If No , It will Open up an
	 * Regular Browser
	 * 
	 */
	public WebDriver intializeChrome(boolean incognito, boolean isSauceTest) {
		try {

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
			ChromeOptions options = new ChromeOptions();

			if (incognito) {
				options.addArguments("incognito");
			} else {
				options.addArguments("user-data-dir=" + Constants.CHROME_USERDATA_DIR);
			}

			cap.setCapability(ChromeOptions.CAPABILITY, options);
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", Constants.DOWNLOADS_LOCATION);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--force-device-scale-factor=1");
			options.addArguments("--disable-gpu");
			options.setCapability("platform", "Windows 10");
			if (isSauceTest)
				driver = new RemoteWebDriver(new URL(InputData.sauceLabsURL), options);
			else
				driver = new ChromeDriver(cap);

			// Navigate Application URL and Set Browser Info
			navigateURL();
			setBrowserInfo();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Unable to initialize Browser : Chrome");
		}
		return driver;
	}

	/**
	 * @throws InterruptedException
	 * 
	 *                              Maximize the Window , Delete Cookies , Refresh
	 *                              and Navigate URL
	 */
	public void navigateURL() throws InterruptedException {

		// Maximize the windows and delete the cookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);

		// Input the URL
		driver.navigate().refresh();
		driver.get(InputData.url);
		Thread.sleep(2000);
	}

	/**
	 * This method will save Browser Details and Populate the Same in the final
	 * Execution Summary
	 */
	public static void setBrowserInfo() {

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toUpperCase();
		String browserVersion = cap.getVersion().toLowerCase();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		browserInfo = browserName + "_" + browserVersion;
		browserResolution = driver.manage().window().getSize().toString();
		screenResolution = "(" + width + ", " + height + ")";
		platformInfo = Constants.OS_NAME.toLowerCase();

	}

	/**
	 * Kill All Open Chrome/Chrome driver tasks
	 */
	public void cleanChrome() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Close the browser
	 * 
	 */
	public void closeBrowser() throws IOException {
		try {
			Thread.sleep(4000);
			driver.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete existing matching files
	 */
	public void deleteExistingFilesInFolder(String folderName) {

		if (new File(folderName).exists()) {
			File[] listFiles = new File(folderName).listFiles();

			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isFile()) {

					listFiles[i].delete();

				}
			}
		}
	}

	/**
	 * @param encodedBytes
	 * @return
	 * 
	 *         Decode the Password as per Base 64 Encryption
	 */
	public static String decodeBytes(String encodedBytes) {
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		return new String(decodedString);
	}

	/**
	 * @param input
	 * @return
	 * 
	 *         Encode the Password as per Base 64 Encryption
	 */
	public static String encodeBytes(String input) {
		byte[] encodedBytes = Base64.encodeBase64(input.getBytes());
		String encodedString = new String(encodedBytes);
		System.out.println(encodedString);
		return encodedString;
	}

	/**
	 * @param path
	 * @param folder
	 * @return
	 * 
	 *         Create the folder based on the path and folder name input
	 */
	public static String createDirectory(String path, String folder) {
		String folderName = path + folder;
		String directoryPath = "Directory Not Created";
		File file = new File(folderName);

		if (!file.exists()) {
			if (file.mkdir()) {

				directoryPath = folderName + "\\";
			} else {

			}
		} else
			return folderName + "\\";
		return directoryPath;
	}

	/**
	 * @return
	 * 
	 *         Set User Directory Path for Test Execution Report
	 */
	public static String userDirPath() {
		String userDirPath = "";
		try {
			Date date = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
			String strDate = dateformat.format(date);
			createDirectory(Constants.REPORTS_DIR + "\\", "execution_screens");
			userDirPath = createDirectory(Constants.REPORTS_DIR + "\\execution_screens\\", "ScreenshotsDoc_" + strDate);

		} catch (NullPointerException e) {
			e.printStackTrace();

		}
		return userDirPath;
	}

}

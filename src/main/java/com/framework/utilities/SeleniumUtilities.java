package com.framework.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.base.Base;
import com.framework.base.Global;

public class SeleniumUtilities extends Base {

	public void openURL(String URL) {
		driver.get(URL);
	}

	public void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
	}

	public WebElement findElement(By by) throws NoSuchElementException, IOException {
		WebElement field = null;
		try {
			field = driver.findElement(by);
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
		}
		return field;
	}

	public List<WebElement> findElements(By by) throws NoSuchElementException {
		List<WebElement> allData = null;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			allData = driver.findElements(by);
		} catch (NoSuchElementException ex) {
			logger.info("Element is NOT found.");
		}
		return allData;
	}

	public void enterText(WebElement element, String text) {

		try {
			waitElementToBeClickable(element);
			element.sendKeys(text);
			highLightElement(element);
			wait(100);
			element = null;
		} catch (Exception e) {
			logger.info("No Element Found to enter text" + element);
		}
	}

	public void enterTextWithoutHighlight(WebElement element, String text) {

		try {
			waitElementToBeClickable(element);
			element.sendKeys(text);
		} catch (Exception e) {
			logger.info("No Element Found to enter text" + element);
		}
	}


	public void enterText(WebElement element, Keys text) {

		try {
			waitElementToBeClickable(element);
			element.sendKeys(text);
			highLightElement(element);
			wait(100);
			element = null;
		} catch (Exception e) {
			logger.info("No Element Found to enter text" + element);
		}
	}

	public void clickOnElement(WebElement element) throws Exception {
		try {
			waitElementToBeClickable(element);
			highLightElement(element);
			element.click();
			wait(1000);
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.info("No Element Found to click with: " + element);
		}
	}

	public void clickOnElement(By by) throws Exception {
		try {
			waitElementToBeClickable(findElement(by));
			highLightElement(findElement(by));
			findElement(by).click();
			wait(1000);
		} catch (Exception e) {
			logger.info("No Element Found to click with: " + findElement(by));
		}
	}

	public void clickElementByJSE(WebElement ele) {
		waitElementToBeClickable(ele);
		highLightElement(ele);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", ele);

	}

	public static Boolean moveToThenSlowClickElement(WebElement toElement) throws InterruptedException {
		final Actions clickOnElementAndHold = new Actions(driver);
		final Actions release = new Actions(driver);
		clickOnElementAndHold.moveToElement(toElement).clickAndHold(toElement).perform();

		Thread.sleep(2000);

		release.release(toElement).perform();

		Action hoverOverCheckBox = clickOnElementAndHold.build();
		hoverOverCheckBox.perform();

		return true;
	}

	public void doubleclickElementByJSE(List<WebElement> ele) {
		waitElementToBeClickable(ele.get(0));
		((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", ele);
	}

	public void doubleclickElementByJSE(WebElement ele) {
		waitElementToBeClickable(ele);
		((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", ele);
	}

	public void clickOnElementNumberoftimes(WebElement element, int iTimes) throws Exception {
		try {
			waitElementToBeClickable(element);
			highLightElement(element);
			for (int i = 0; i < iTimes; i++) {
				element.click();
				wait(2000);
			}
		} catch (Exception e) {
			logger.info("No Element Found to click with: " + element);
		}
	}

	public void ClickByJQuery(String elementCSS) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String query = "document.querySelector(\"" + elementCSS + "\").click();";
		System.out.println(query);
		executor.executeScript(query);
	}

	public void ClickByJQueryElementsList(String elementCSS, int elementCount) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("document.querySelectorAll(\"" + elementCSS + "\")[" + elementCount + "].click()");
		executor.executeScript("document.querySelectorAll(\"" + elementCSS + "\")[" + elementCount + "].click()");
	}

	public void clickOnElementFromPoint(WebElement ele) {
		waitElementToBeClickable(ele);
		highLightElement(ele);
		Point loc = ele.getLocation();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element_to_click = (WebElement) executor
				.executeScript("return document.elementFromPoint(arguments[0], arguments[1]);", loc.getX(), loc.getY());
		element_to_click.click();
	}

	public void enterKeys() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.chord(Keys.CONTROL, Keys.SPACE)).perform();
	}

	/**
	 * Method developed on @Date: 15.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To press Enter key
	 * 
	 */
	public void clickEnter() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	public void enterTextByJSE(WebElement ele, String value) {
		waitElementToBeVisible(ele);
		highLightElement(ele);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value', arguments[1])", ele, value);
	}

	public void clearText(WebElement element) throws Exception {
		try {
			waitElementToBeVisible(element);
			element.clear();
			highLightElement(element);
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
		}
	}

	public void clearTextWithoutHighlight(WebElement element) throws Exception {
		try {
			waitElementToBeVisible(element);
			element.clear();
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
		}
	}

	public void clearAndEnterText(WebElement element, String text) throws Exception {
		try {
			waitElementToBeVisible(element);
			element.clear();
			wait(1000);
			element.sendKeys(text);
			highLightElement(element);
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
		}
	}

	public void SelectElementByJSE(WebElement dropDownListBox, String Data) {
		waitElementToBeVisible(dropDownListBox);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript(
					"var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }",
					dropDownListBox, Data);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void selectTextFromDropDown(WebElement element, String text) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
			highLightElement(element);
			//		waitTillElementToBeSelected(element);

		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
		}
	}

	public void selectIndexFromDropDown(WebElement element, int index) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			sel.selectByIndex(index);
			highLightElement(element);
			wait(1000);
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
		}
	}

	public void selectValueFromDropDown(WebElement element, String value) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			sel.selectByValue(value);
			highLightElement(element);
			wait(1000);
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
		}
	}

	public String getSelectedTextFromDropDown(WebElement element) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			String text = sel.getFirstSelectedOption().getText();
			highLightElement(element);
			wait(1000);
			return text;
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
			return "";
		}
	}

	public String getSelectedTextFromDropDownWithYellowHightlight(WebElement element) throws Exception {
		try {
			sf.seleU.waitElementToBeVisible(element);
			Select sel = new Select(element);
			String text = sel.getFirstSelectedOption().getText();
			sf.seleU.highLightElementYellow(element);
			sf.seleU.hardwait(1000);
			return text;
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
			return "";
		}
	}

	public String getSelectedOptionValue(WebElement element) {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			String text = sel.getFirstSelectedOption().getAttribute("value");
			highLightElement(element);
			wait(1000);
			return text;
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
			return "";
		}
	}

	public String getSelectedOptionText(WebElement element) {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			String text = sel.getFirstSelectedOption().getText();
			highLightElement(element);
			wait(1000);
			return text;
		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
			return "";
		}
	}

	public List<String> getDropDownOptions(WebElement ele) {
		waitElementToBeVisible(ele);
		List<WebElement> opt = new ArrayList<WebElement>();
		opt = new Select(ele).getOptions();
		List<String> optionText = new ArrayList<String>();
		for (int i = 0; i < opt.size(); i++) {
			optionText.add(opt.get(i).getText());
		}
		return optionText;
	}

	public void withOutSelectClassDropDownOptions(List<WebElement> ele, String value) throws InterruptedException {
		List<WebElement> opt = new ArrayList<WebElement>();
		opt = ele;
		for (int i = 0; i < opt.size(); i++) {
			Thread.sleep(1000);
			String text = opt.get(i).getText().trim();
			if (text.equals(value)) {
				opt.get(i).click();
				logger.info(value + " is correctly shown and selected");
				System.out.println(value + " is correctly shown and selected");
				break;
			} else {
				System.out.println(value + " value is not selected " + value);
			}
		}
	}

	public void robotClick(WebElement ele) throws AWTException {
		waitElementToBeVisible(ele);
		highLightElement(ele);

		Robot robot = new Robot();
		Point loc = ele.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		robot.mouseMove(x, y);

		// Clicks Left mouse button
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		System.out.println("Browse button clicked");

	}

	public void refreshPage() {
		driver.navigate().refresh();
		timeOut(30);
		waitTillLoading();
		wait(2000);
	}

	public void navigateBack() {
		driver.navigate().back();
		timeOut(30);
		waitTillLoading();
		wait(2000);
	}

	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
			logger.info("Page Title is: " + title);
		} catch (Exception e) {
			logger.info("Could NOT find page with Title" + title);
		}
		return title;
	}

	public String getCurrentUrl() {
		String url = null;
		try {
			url = driver.getCurrentUrl();
			logger.info("Page Url is: " + url);
		} catch (Exception e) {
			logger.info("Could NOT find page with Url" + url);
		}
		return url;
	}

	public void ScrolltoElement(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			highLightElement(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ScrolltoElementPageCenter(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
			highLightElement(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollToBottom() {
		try{
			JavascriptExecutor jse = (JavascriptExecutor) driver;  		
			jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollByCoOrdinates(int scrollcount) {
		for (int i = 0; i < scrollcount; i++) {
			((JavascriptExecutor) Global.driver).executeScript("window. scrollBy(200,300)");
		}
	}

	public void scrollUpByCoOrdinates() {
		((JavascriptExecutor) Global.driver).executeScript("window. scrollBy(200,-150)");
	}

	public void closeRecentlyOpenedWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				driver.close(); // closing child window
				driver.switchTo().window(parentWindow); // control shifted to parent window
			}
		}
	}

	public boolean isElementPresent(List<WebElement> elementList) {
		try {
			if (elementList.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			waitElementToBeVisible(element);
			if (element.isDisplayed()) {
				highLightElement(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementEnabled(WebElement element) {
		try {
			waitElementToBeVisible(element);
			if (element.isEnabled()) {
				highLightElement(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}
	public boolean isElementSelected(WebElement element) {
		try {
			waitElementToBeVisible(element);
			if (element.isSelected()) {
				highLightElement(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayedWithYellowHighlight(WebElement element) {
		try {
			waitElementToBeVisible(element);
			if (element.isDisplayed()) {
				highLightElementYellow(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAlertPresent() {
		try {
			waitTillAlertPresent();
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public boolean isElementPresent(By by) {

		List<WebElement> elementList = findElements(by);
		if (elementList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementPresent(WebElement element) {
		try {			
			waitElementToBeVisible(element);
			element.getSize();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementPresentWebDriverWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {	 
			wait.until(ExpectedConditions.visibilityOf(element));
			highLightElement(element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTextFromWebElement(By by) throws Exception {
		WebElement element = null;
		String gettext = null;
		try {
			element = findElement(by);
			gettext = element.getText();
			logger.info("Element text is: " + gettext);

		} catch (NoSuchElementException e) {
			logger.info("No element with name " + element + "was found.");
		}
		return gettext;
	}

	public String getTextFromWebElementWithYellowHighlight(WebElement element) throws Exception {
		String gettext = null;
		try {
			sf.seleU.waitElementToBeVisible(element);
			gettext = element.getText();
			sf.seleU.highLightElementYellow(element);
			if (gettext.length() == 0) {
				gettext = element.getAttribute("value");
			}
			logger.info("Element text is: " + gettext);

		} catch (NoSuchElementException e) {
			logger.info("No element with name " + element + "was found.");
		}
		return gettext;
	}

	public void highLightElement(WebElement element) {
		waitElementToBeVisible(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: cyan; border: 2px solid red;');", element);

	}

	public void highLightElementYellow(WebElement element) {
		waitElementToBeVisible(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

	}

	public void highlightFailedEle(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background:  orangered; border: 2px solid blue;');",
				element);

	}

	public String getTextFromWebElement(WebElement element) throws Exception {
		String gettext = null;
		try {
			waitElementToBeVisible(element);
			gettext = element.getText();
			highLightElement(element);
			if (gettext.length() == 0) {
				gettext = element.getAttribute("value");
			}
			logger.info("Element text is: " + gettext);

		} catch (NoSuchElementException e) {
			logger.info("No element with name " + element + "was found.");
		}
		return gettext;
	}

	public void waitElementToBeVisible(WebElement element) {

		logger.info("Waiting for Element to be Visible");
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(150));
			wait.withTimeout(Duration.ofSeconds(8));
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			logger.info("Unable to find web element");
		}
	}

	public void waitElementToBeInvisible(WebElement element) {

		logger.info("Waiting for Element to be Invisible");
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(250));
			wait.withTimeout(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOf(element));

		} catch (Exception e) {
			logger.info("Unable to find web element");
		}
	}

	

	public void waitElementToBeClickable(WebElement element) {

		logger.info("Waiting for Element to be Clickable");
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(250));
			wait.withTimeout(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));

		} catch (Exception e) {
			logger.info("Unable to find web element");
		}
	}

	public void waitTillAlertPresent() {

		logger.info("Waiting for Element to be Clickable");
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(250));
			wait.withTimeout(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());

		} catch (Exception e) {
			logger.info("No alert is present");
		}
	}

	public void waitTillElementToBeSelected(WebElement element) {

		logger.info("Waiting for Element to be Clickable");
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(250));
			wait.withTimeout(Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeSelected(element));

		} catch (Exception e) {
			logger.info("No alert is present");
		}
	}

	public void waitTillLoading() {
		try {
			Thread.sleep(200);
			WebDriverWait wait = new WebDriverWait(driver, 200);
			if (isElementPresent(By.className("rc-loading-animation"))) {
				wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("rc-loading-animation"))));
			}
		} catch (Exception e) {

			logger.info("Element is still available in DOM " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void waitForLoading() throws Exception {
		Thread.sleep(500);
		new WebDriverWait(driver, 800).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[contains(@class,'nds-spinner_brand nds-spinner nds-is')])[1]")));
		Thread.sleep(500);
	}

	public void hardwait(int timeout) {
		try {
			synchronized (driver) {
				driver.wait(timeout);
			}
		} catch (InterruptedException e) {
			logger.info("==================== wait as timed out after: " + timeout + " milliseconds");
		}
	}

	public void wait(int timeInMiliSeconds) {
		try {
			Thread.sleep(timeInMiliSeconds);
		} catch (InterruptedException e) {
			logger.info("Could NOT execute wait method");
		}
	}

	public void implicitlyWait(int timeInSeconds) {
		try {
			driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.info("Could NOT execute wait method");
		}
	}

	public void robotdownKey(int iTimes) {
		Robot robot;
		try {
			robot = new Robot();
			for (int i = 0; i < iTimes; i++) {
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			}

		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public void robotPgDown(int iTimes) {
		Robot robot;
		try {
			robot = new Robot();
			for (int i = 0; i < iTimes; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			}

		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public void robotPgUp(int iTimes) {
		Robot robot;
		try {
			robot = new Robot();
			for (int i = 0; i < iTimes; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
			}

		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public void robottab() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public void robotPressKey(int iTimes, int key) {
		Robot robot;
		try {
			robot = new Robot();
			for (int i = 0; i < iTimes; i++) {
				robot.keyPress(key);
				wait(1000);
				// robot.keyRelease(key);
			}

		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebElement element, String name) {
		String attribute = null;
		try {
			logger.info("Element with Attribute Name: " + name);

			attribute = element.getAttribute(name);
			System.out.println(attribute);
		} catch (Exception ex) {
			logger.info("No Attribute  with name " + name + "was found.");
		}
		return attribute;
	}

	public String getAttributeInnerText(WebElement element) {
		String attribute = null;
		try {

			attribute = element.getAttribute("innerText");
			System.out.println(attribute);
		} catch (Exception ex) {
			logger.info("No Attribute Inner Text was found.");
		}
		return attribute;
	}

	public void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void paste_Function() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	public void switchToFrame(String frame) {
		try {
			logger.info("Popup/Frame Name or ID is" + frame);
			driver.switchTo().frame(frame);

		} catch (Exception e) {
			logger.info(frame + " Popup/Frame is NOT found");
		}
	}

	public void switchToFrame(int frameindex) {
		try {
			logger.info("Frame Index is" + frameindex);
			driver.switchTo().frame(frameindex);

		} catch (Exception e) {
			logger.info("frameindex :" + frameindex + " Popup/Frame is NOT found");
		}
	}

	public void switchToFrame(WebElement frame) {
		try {
			logger.info("Switching to frame" + frame.toString());
			driver.switchTo().frame(frame);

		} catch (Exception e) {
			logger.info("frame Popup/Frame is NOT found");
		}
	}

	public void switchToElementFrame(List<WebElement> frameElement) {

		List<WebElement> totalframes = driver.findElements(By.xpath("//iframe"));
		for (int i = 0; i < totalframes.size(); i++) {
			driver.switchTo().frame(totalframes.get(i));
			if (frameElement.size() > 0) {
				System.out.println("Found Element in the given frame: " + i);
				break;
			} else {
				driver.switchTo().defaultContent();
			}

		}
	}

	public void timeOut(long val) {
		driver.manage().timeouts().implicitlyWait(val, TimeUnit.SECONDS);
	}

	public int switchWindow(int winNum) {
		Set<String> winIDs = driver.getWindowHandles();
		java.util.Iterator<String> iter = winIDs.iterator();
		int winCount = 1;
		while (winCount <= winNum) {
			// It will set the iterator in the first windowId at first by iter.next, 
			//first window is the parent window id
			String windowToBeSwitched = iter.next(); 
			if (winCount == winNum) {
				driver.switchTo().window(windowToBeSwitched);
				driver.manage().window().maximize();
				break;
			}
			winCount++;
		}
		return winCount;
	}

	public void switchToActiveElement() {
		try {
			driver.switchTo().activeElement();

		} catch (Exception e) {
			logger.info("Could NOT switch to active element");
		}
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			logger.info("Could NOT switch to Default Conetnt");
		}
	}

	public String switchToAlert(String command) {
		try {
			Alert alert = driver.switchTo().alert();

			if (command.equalsIgnoreCase("OK")) {
				String text = alert.getText();
				alert.accept();
				return text;
			} else if (command.equalsIgnoreCase("CANCEL")) {
				String text = alert.getText();
				alert.dismiss();
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.info("Could NOT switch to alert");
		}
		return null;
	}

	public boolean mouseOverAndClickOnElement(WebElement element) throws Exception {
		boolean mouseHover = false;
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click();
		if (null != element) {
			mouseHover = true;
		}
		return mouseHover;
	}

	public void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		action.moveToElement(element).build().perform();
		highLightElement(element);
		actions.contextClick(element).perform();
	}

	public boolean mouseOverOnElement(WebElement myElement) throws Exception {
		boolean mouseHover = false;
		action = new Actions(driver);
		action.moveToElement(myElement).build().perform();
		if (null != myElement) {
			mouseHover = true;
			logger.info("Mouse hovered successfully on " + myElement.getText());
		}
		highLightElement(myElement);
		return mouseHover;
	}

	public void waitForListOfElements(List<WebElement> element) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(Duration.ofMillis(150));
			wait.withTimeout(Duration.ofSeconds(8));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));

		} catch (Exception e) {
			throw new IllegalArgumentException("wait For Condition : visible isn't supported.");
		}
	}

	public void hundredPercentPageZoom() {
		try {
			WebElement html = driver.findElement(By.tagName("html"));
			new Actions(driver)
			.sendKeys(html, Keys.CONTROL, Keys.ADD, Keys.NULL)
			.perform();
		} catch (Exception e) {
			throw new IllegalArgumentException("Screen zoom is not supported");
		}
	}

	public void clearTextByMouseAction(WebElement element) {
		element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));	
	}


	public void softAssert(boolean condition, String passMsg, String failMsg) {
		if(condition) {
			logger.info(passMsg);
		} else {
			logger.info("Soft assert failed due to exception: - "+failMsg);
		}
	}


	public void hardAssert(boolean condition, String passMsg, String failMsg) {
		if(condition) {
			logger.info(passMsg);
		} else {
			throw new WebDriverException("Hard assert failed due to exception: -"+failMsg);
		}
	}


	public boolean isClickable(WebElement element)      
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	public void selectTextFromDropDownWithoutHighlight(WebElement element, String text) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
			waitTillElementToBeSelected(element);

		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
		}
	}

	public void selectTextFromDropDownWithoutHighlight_Fit(WebElement element, String text) throws Exception {
		try {
			waitElementToBeVisible(element);
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
			//			waitTillElementToBeSelected(element);

		} catch (NoSuchElementException ex) {
			logger.info("Unable to find an element");
			ex.printStackTrace();
		}
	}

	public void dragAndDropElement(WebElement sourceElement, WebElement targetElement) {
		try {
			waitElementToBeVisible(sourceElement);
			waitElementToBeVisible(targetElement);
			Actions actions=new Actions(driver);
			sf.seleU.ScrolltoElement(targetElement);
			wait(3000);
			actions.clickAndHold(sourceElement).perform();
			wait(3000);
			actions.release(targetElement).perform();
			//			actions.dragAndDrop(sourceElement, targetElement);
		}catch(NoSuchElementException ex) {
			logger.info("Drag and drop got failed");
			ex.printStackTrace();
		}
	}
	public void enterTextUsingKeys(WebElement element, String text) {

		try {
			waitElementToBeClickable(element);
			String s =Keys.chord(Keys.CONTROL,"a" );
			element.sendKeys(s);
			hardwait(1000);
			element.sendKeys(Keys.DELETE);
			
			element.sendKeys(text);
			highLightElement(element);
			wait(100);
			element = null;
		} catch (Exception e) {
			logger.info("No Element Found to enter text" + element);
		}
	}
}
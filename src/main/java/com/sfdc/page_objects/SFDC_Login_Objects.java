package com.sfdc.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 26/Sept/2019
 *
 *         SFDC login page objects
 */
public class SFDC_Login_Objects {

	public SFDC_Login_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	public WebElement userNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	public WebElement passwordInput;

	@FindBy(how = How.XPATH, using = "//input[@id='Login']")
	public WebElement loginToSandBoxButton;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Verification Code')]//following-sibling::div//input[@type='text']")
	public WebElement verificationCodeInput;

	@FindBy(how = How.XPATH, using = "//input[@value='Verify']")
	public WebElement verifyButton;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Got it')]")
	public WebElement scheduledMaintGotItButton;

	@FindBy(how = How.XPATH, using = "//div[@class='oneUserProfileCard']//h1/a[@class='profile-link-label']")
	public WebElement userProfileLoginName;
}

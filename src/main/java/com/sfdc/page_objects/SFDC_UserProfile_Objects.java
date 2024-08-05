package com.sfdc.page_objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti,Date:15/Apr/2021
 *
 *         SFDC User Profile/Settings objects
 */
public class SFDC_UserProfile_Objects {

	public SFDC_UserProfile_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//a[.='Language & Time Zone']"),
			@FindBy(xpath = "//a[.='Langue et fuseau horaire']") })
	public WebElement languageAndTimeSettingLink;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'languageLocale')]")
	public List<WebElement> languageSelectBox;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'locale')]")
	public WebElement localeSelectBox;
	
	@FindAll({ @FindBy(xpath = "//input[@value='Enregistrer']"),
		@FindBy(xpath = "//input[@value='Save']")})
	public WebElement saveButton;
	
	@FindBy(xpath = "//a[@class='footer-language-toggle']")
	public WebElement languageToggle;

	
}

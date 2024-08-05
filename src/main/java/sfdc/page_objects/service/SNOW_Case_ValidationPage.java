package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SNOW_Case_ValidationPage {
	public SNOW_Case_ValidationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='sysparm_search']")
	public WebElement snowGlobalSearch;
	
	@FindBy(how = How.XPATH, using = "//iframe[@id='gsft_main']")
	public WebElement incidentFrame;
	
	@FindBy(how = How.XPATH, using = "//section[@class='record-container']//a[@title]")
	public WebElement incidentLink;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Unique ID']")
	public WebElement incidentID;
	
	@FindBy(how = How.XPATH, using = "//select[@id='sys_select.incident.u_portfolio']//option[@selected='SELECTED']")
	public WebElement portfolioOption;
	
	@FindBy(how = How.XPATH, using = "//select[@id='incident.state']//option[@selected='SELECTED']")
	public WebElement statusOption;
	
	@FindBy(how = How.XPATH, using = "//input[@id='incident.u_salesforce_case']")
	public WebElement caseId;
	
	@FindBy(how = How.XPATH, using = "//input[@id='sys_display.incident.assignment_group']")
	public WebElement snowAssignmentGrp;
	
	@FindBy(how = How.XPATH, using = "//select[@id='incident.priority']//option[@selected='SELECTED']")
	public WebElement priorityOption;
	
	@FindBy(how = How.XPATH, using = "//input[@id='incident.short_description']")
	public WebElement shortDescriptionField;
	
	@FindBy(how = How.XPATH, using = "//input[@id='sys_original.incident.description']")
	public WebElement descriptionField;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Closure Information')]")
	public WebElement snowClosureInfoTab;
	
	@FindBy(how = How.XPATH, using = "//select[@id='incident.close_code']//option[@selected='SELECTED']")
	public WebElement snowReason;
	
	@FindBy(how = How.XPATH, using = "//button[@id='user_info_dropdown']")
	public WebElement snowUserInfo;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Logout')]")
	public WebElement snowLogout;
}

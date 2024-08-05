package com.sfdc.page_objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 24/Sept/2020
 * 
 * 
 *         SFDC Files page objects
 */
public class SFDC_Files_Objects {

	public SFDC_Files_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(how = How.XPATH, using = "//a[@data-itemid='ContentDocument']/span/span[contains(.,Files)]"),
	@FindBy(how = How.XPATH, using = "//a[@title='Files']/span/span[contains(.,'Files')]") })
	public WebElement FilesMenu;

	@FindBy(how = How.XPATH, using = "//span[.='Files'][contains(@class,'uiOutputText')]")
	public WebElement filesSpanHeader;

	@FindBy(how = How.XPATH, using = "//a[@title='Owned by Me']")
	public WebElement filesOwnedByMeSection;

	@FindBy(how = How.XPATH, using = "//a[@title='Shared with Me']")
	public WebElement filesSharedWithMeSection;

	@FindBy(how = How.XPATH, using = "//a[@title='Recent']")
	public WebElement filesRecentSection;

	@FindBy(how = How.XPATH, using = "//a[@title='Following']")
	public WebElement filesFollowingSection;

	@FindBy(how = How.XPATH, using = "//a[@title='Libraries']")
	public WebElement filesLibrariesSection;

	@FindAll({ @FindBy(how = How.XPATH, using = "//a[@title='Upload Files']"),
			@FindBy(how = How.XPATH, using = "//span[.='Upload Files']/ancestor::button"),
			@FindBy(how = How.XPATH, using = "//span[.='Upload Files']") })
	public WebElement filesUploadButton;
		
	@FindBy(how = How.XPATH, using = "//span[.='Upload Files']")	
	public WebElement filesUpload;
	
	@FindBy(how = How.XPATH, using = "//input[@type='file']")
	public WebElement filesUploadInput;

	@FindBy(how = How.XPATH, using = "//h2[.='Upload Files']")
	public WebElement filesUploadPopUpHeader;

	@FindBy(how = How.XPATH, using = "//button[contains(@class,'ok')]")
	public WebElement filesUploadDoneButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Done')]/parent::button")
	public WebElement uploadDoneButton;

	@FindBy(how = How.XPATH, using = "//table[contains(@class,'uiVirtualDataTable')]/tbody/tr")
	public List<WebElement> filesTableRows;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'of 1 file uploaded')]")
	public WebElement filesUploadedtext;

	@FindBy(how = How.XPATH, using = "//a[.='New Library']")
	public WebElement newLibraryButton;

	@FindBy(how = How.XPATH, using = "//span[.='Name']/ancestor::div[contains(@class,'uiInput')]/input")
	public WebElement libraryNameTextBox;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'button-container-inner')]/button[@title='Save']")
	public WebElement librarySaveButton;

	@FindBy(how = How.XPATH, using = "//a[.='New Folder']")
	public WebElement newFolderButton;

	@FindBy(how = How.XPATH, using = "//span[.='Folder Name']/ancestor::div[contains(@class,'uiInput')]//input")
	public WebElement folderNameTextBox;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'uiTooltip')]/button[contains(@class,'save')]")
	public WebElement folderSaveButton;

	@FindBy(how = How.XPATH, using = "//a[.='Add Files']")
	public WebElement addFilesButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'1 file was uploaded')]")
	public WebElement uploadSuccessMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'itemTitle')]//ancestor::a")
	public List<WebElement> filesNames;

	@FindBy(how = How.XPATH, using = "//a[@title='Show More']")
	public WebElement showMoreIcon;

	@FindBy(how = How.XPATH, using = "//a[@title='Delete'][@role='menuitem']")
	public WebElement deleteFileMenuItem;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//button[@title='Delete']")
	public WebElement deleteFileButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'was deleted')]")
	public WebElement deleteSuccessMessage;

	@FindBy(how = How.XPATH, using = "//table//a[contains(@class,'icon')]")
	public WebElement libraryActionIcon;

	@FindBy(how = How.XPATH, using = "//tbody//tr//a")
	public WebElement firstLibraryOrFolderFromList;

	@FindBy(how = How.XPATH, using = "//tbody//tr//a//span[contains(@class,'itemTitle')]")
	public WebElement firstLibraryOrFolderName;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Show Actions')]")
	public WebElement libraryDropdown;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Edit Library Details'][@role='menuitem']")
	public WebElement editLibraryDetails;
	
}

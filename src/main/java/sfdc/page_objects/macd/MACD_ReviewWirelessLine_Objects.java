package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_ReviewWirelessLine_Objects {
	public MACD_ReviewWirelessLine_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Rogers Wireless line')]")
	public WebElement wirelessLineHeader;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add add-ons']")
	public WebElement Add_addOnBtn;	
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Back to previous']")
	public WebElement backToPrevious;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Remove existing add-ons']")
	public WebElement removeExistingAddOn;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class='description']"),
		@FindBy(how = How.XPATH, using = "//div[contains(@class,'value')]") })
	public List<WebElement> productList;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]")
	public WebElement wireLessAddOnChangesText;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[1]/span) | //div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[1]/div[1]")
	public List<WebElement> wireLessAddOnChangesProduct;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[2]/span) | //div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[2]/div[1]")
	public List<WebElement> wireLessAddOnChangesProductAction;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[contains(@class,'table-td')]")
	public List<WebElement> wireLessAddOnChangesProductPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div/div/button[contains(@class,'back-button')]")
	public List<WebElement> wireLessAddOnChangesProductCancelChanges;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div//following::div[contains(@class,'icon_right')]/input[@class='slds-input']")
	public List<WebElement> wireLessAddOnChangesProductEffectiveDate;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div//following::div[contains(@class,'icon_right')]/input/following-sibling::lightning-button-icon/button/lightning-primitive-icon")
	public List<WebElement> wireLessAddOnChangesCalendarIconButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div[1]/div")
	public List<WebElement> wireLessAddOnChangesBothDPProductTogether;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div/div/div[contains(@class,'table-td')]/parent::div/following-sibling::div/div[contains(@class,'table-td')]")
	public List<WebElement> wireLessAddOnChangesBothDPActionTogether;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div//following::div[contains(@class,'icon_right')]/input)[last()]")
	public List<WebElement> wireLessAddOnChangesEffectiveDateTogether;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Proceed to order summary']")
	public WebElement proceedToOrderSummaryButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'You have successfully made changes to your add-ons')]")
	public WebElement addOnChangesMessage;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Please review the changes before proceeding to order summary')]")
	public WebElement addOnChangesSubMessage;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='You have successfully made changes to your add-ons']")
	public WebElement msgBarOnAddonsSummary;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Remove existing add-ons']/preceding::div[text()='Device Protection'][@class='description']")
	public WebElement deviceProtection_InWirelssAddOn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Wireless plan')]/parent::button[@class='addon-button']")
	public WebElement changeWirelessPlanBtn;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'prevBtn')]")
	public WebElement previousBtn;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Remove current add-ons']")
	public WebElement removeCurrentAddOnsBtn;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Discard')]")
	public WebElement discardChangesBtn;
	
	@FindBy(how = How.XPATH, using = "//header[@class='nds-modal__header']")
	public WebElement discardChangesHeader;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'primary-button')]//span[contains(text(),'Discard changes')]")
	public WebElement discardChangesBtnOnPopUp;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'secondary-button')]//span[contains(text(),'Cancel')]")
	public WebElement discardChangesCancel;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'header-text1')]")
	public WebElement ctnBlockHeader;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-edit')]")
	public WebElement removeBlk;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes to your Wireless add-ons:')]/../following-sibling::div//div[contains(text(),'Apple Care')]/../following::div[contains(@class,'icon_right')]//button[@disabled]")
	public WebElement appleCareEffectiveDateCalendarIcon;
}

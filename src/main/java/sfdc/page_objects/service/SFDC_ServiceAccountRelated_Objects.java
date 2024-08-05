package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_ServiceAccountRelated_Objects {
	
	/**
	 * @author Pankaj Agarwal, Date 06/April/2020
	 * 
	 *         Service Account PrRelated Page objects
	 * 
	 * 
	 */

	public SFDC_ServiceAccountRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@id='relatedListsTab__item']")
	public WebElement serviceRelatedTab;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Related']")
	public WebElement serviceAccountRelatedTab;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-grid slds-page-header forceRelatedListCardHeader')]//span[contains(text(),'Billing Assets')]/../../../../../following-sibling::a/div/span")
	public WebElement billing_AssetViewAll;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-grid slds-page-header forceRelatedListCardHeader')]//span[contains(text(),'Service Assets')]/../../../../../following-sibling::a/div/span")
	public WebElement service_AssetViewAll;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Asset-Name']")
	public WebElement assetProductInputEnter;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Service Assets']//following::div[@class='slds-card__footer']/span")
	public List<WebElement> serviceAssetViewAllClick;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Updated')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> productItemsNameInServiceAssetList;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Asset Name']/parent::a")
	public WebElement assetNameSort;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//iframe[@title='accessibility title']")
	public WebElement rdiIframe;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Speed']//following-sibling::td")
	public WebElement rdiSpeedText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Offer Type']//following-sibling::td")
	public WebElement rdiOfferTypeText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Port Type']//following-sibling::td")
	public WebElement rdiPortTypeText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Port Speed']//following-sibling::td")
	public WebElement rdiPortSpeedText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='EVC']//following-sibling::td")
	public WebElement rdiEVCText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Contract Term']//following-sibling::td")
	public WebElement rdiContractTermText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='SpecSheet']//following-sibling::td")
	public WebElement rdiSpecSheetText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'Product Code')]//following-sibling::td")
	public WebElement rdiProductCodeText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='Encapsulation Type']//following-sibling::td")
	public WebElement rdiEncapsulationTypeText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='DNS']//following-sibling::td")
	public WebElement rdiDNSText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[text()='VLAN Ids']//following-sibling::td")
	public WebElement vLanIDText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'IP Version')]//following-sibling::td")
	public WebElement rdiIPVersionText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'IPv4 WAN')]//following-sibling::td")
	public WebElement rdiIPv4WANText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'VLAN')]//following-sibling::td")
	public WebElement rdiVLanIdText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'IPv4 LAN')]//following-sibling::td")
	public WebElement rdiIPv4LANText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'IPv6 LAN')]//following-sibling::td")
	public WebElement rdiIPv6LANText;
	
	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'IPv6 WAN')]//following-sibling::td")
	public WebElement rdiIPv6WANText;

	
}

package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_OrderDecomposition_Objects {

	public SFDC_OrderDecomposition_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@class='title'][contains(.,'Order Decomposition')]")
	public WebElement orderDecompositionTab;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Product'][@class='oi-node__label']")
	public List<WebElement> orderDecompositionProductTag;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Product'][@class='oi-node__label']/following-sibling::c-navigator/a")
	public List<WebElement> orderDecompositionProductName;
	
	@FindBy(how = How.XPATH, using = "//div[@class='oi-node__attributes-header']/button")
	public List<WebElement> orderDecompositionAttributeButton;
	
	// Cable L2 Attribute
	@FindBy(how = How.XPATH, using = "(//span[text()='PromoCode'])[last()]/parent::div[@data-attr-code='ATTR_TECH_PROMO_CODE']")
	public List<WebElement> orderDecompositionPromoCodeValue;
	
	@FindBy(how = How.XPATH, using = "//div[@data-attr-code='ATTR_DOWNLOAD_SPEED']")
	public List<WebElement> orderInternetProductSpeed;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'PromoCode')]")
	public List<WebElement> orderDecompositionPromoCodeValue1;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_CBL_INTERNET_BILLING_CFS_INSTALLATION_WORK_ORDER_APPOINTMENT_DATE']")
	public List<WebElement> cFSInstallation_Work_Order_Appointment_Date_Time;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_CBL_INTERNET_BILLING_CFS_PSI_WORK_ORDER_APPOINTMENT_DATE']")
	public List<WebElement> cFS_PSI_Work_Order_Appointment_Date_Time;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_CBL_INTERNET_BILLING_CFS_INSTALLATION_WORK_ORDER_NUMBER']")
	public List<WebElement> cFS_Installation_WorkOrderNo;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_CBL_INTERNET_BILLING_CFS_PSI_WORK_ORDER_NUMBER']")
	public List<WebElement> cFS_PSI_WorkOrderNo;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_CBL_INTERNET_BILLING_CFS_CAN_NUMBER']")
	public List<WebElement> cFS_CAN_No;
	
	@FindBy(how = How.XPATH, using = "//*[@data-attr-code = 'ATTR_TECH_PROMO_CODE']")
	public List<WebElement> cFS_PromoCode;

	// RDI Attributes
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_EVC']")
	public WebElement evcID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_ENCAPSULATION_TYPE']")
	public WebElement enacpsulationType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_VLAN_IDS']")
	public WebElement vLANID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_DNS']")
	public WebElement dNSRequired;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_IP_VERSION']")
	public WebElement ipVersionBlock;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_IPV4_WAN_BLOCK']")
	public WebElement ip4WANBlock;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_IPV4_LAN_BLOCK']")
	public WebElement ip4LANBlock;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_IPV6_WAN_ASSIGN']")
	public WebElement ip6WANBlock;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_IPV6_LAN_ASSIGN']")
	public WebElement ip6LANBlock;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_EXISTING_PORT_IPS']")
	public WebElement existingPortableIP;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_ACCESS_PROVIDER']")
	public WebElement accessProvider;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_SERVICE_PROVIDER']")
	public WebElement serviceProvider;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_CLLI_CLFI']")
	public WebElement clFI;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_DEMARCATION']")
	public WebElement demarcationLOC;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_TERM']")
	public List<WebElement> contractTerm;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_ACCESS_CIRC_ID']")
	public WebElement accessCIRCID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_PROD_CODE_TECH']")
	public WebElement productCode;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_PROD_CODE_TECH']")
	public List<WebElement> terminatingProductCode;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_HANDOFF_INTERFACE']")
	public WebElement handOffInterface;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_MODEM_MODEL']")
	public WebElement model;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_DEVICE_ID']")
	public WebElement deviceID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_CUSTOMER_PORT_ID']")
	public WebElement customerPortID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBER_BANDWIDTH']")
	public WebElement ethernetBandwidth;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_ACCESS_TYPE']")
	public WebElement ethernetAccessType;
	
	// Technical Attribute
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_CHARGE_CODE']")
	public WebElement fibreInternetProductCode;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_PRODUCT_CIRCUIT_TYPE']")
	public WebElement technicalCircuitType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_PRODUCT_CLLI_CLFI']")
	public WebElement technicalCLLCLFI;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_PRODUCT_DEMARCATION_POINT']")
	public WebElement technicalDemarcationLOC;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_PRODUCT_HAND_OFF_INTERFACE']")
	public WebElement technicalHandOFF;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_INSTALLATION_ORACLE_ID']")
	public WebElement technicalOracleID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_NETWORK_ACCESS_INSTALLATION_PHUB']")
	public WebElement technicalPHUB;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_TERMINATING_EQUIPMENT_PRODUCT_EQUIPMENT_MODEL']")
	public WebElement technicalEquipmentModel;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_TERMINATING_EQUIPMENT_PRODUCT_PORT_CONFIG']")
	public WebElement technicalPortConfig;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_ORDER_TYPE']")
	public WebElement technicalOrderType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_PORT_SPEED']")
	public WebElement technicalPortSpeed;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_PORT_TYPE']")
	public WebElement technicalPortType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_SERVICE_TYPE']")
	public WebElement technicalServiceType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_SPEED']")
	public WebElement technicalProductSpeed;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_VLAN_ID_LIST']")
	public WebElement technicalvLanID;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_DNS']")
	public WebElement technicalDnsRequired;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_ENCAPSULATION_TYPE']")
	public WebElement technicalEncapsulationType;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_EVC']")
	public WebElement technicalEvc;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_EVC_NOTES']")
	public WebElement technicalEVCNotes;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_IP_V4_LAN_BLOCK']")
	public WebElement technicalIPV4LAN;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_IP_V4_WAN_BLOCK']")
	public WebElement technicalIPV4WAN;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_IP_V6_LAN_BLOCK']")
	public WebElement technicalIPV6LAN;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_IP_V6_WAN_BLOCK']")
	public WebElement technicalIPV6WAN;
	
	@FindBy(how = How.XPATH, using = "//div/div[@data-attr-code = 'ATTR_RDI_FIBRE_INTERNET_PRODUCT_PORTABLE_IP_LIST']")
	public WebElement technicalPortableIpList;
	
	
}

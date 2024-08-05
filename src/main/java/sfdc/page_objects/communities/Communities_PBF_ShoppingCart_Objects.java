package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:07/07/2021
 *
 *         Communities> PBF Shopping Cart page objects
 */
public class Communities_PBF_ShoppingCart_Objects {

	public Communities_PBF_ShoppingCart_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Shopping cart') or contains(text(),'Panier d')]")
	public WebElement shoppingCartHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Service Address') or contains(text(),'Adresse de service')]")
	public WebElement serviceAddressSubHeading;

	@FindBy(xpath = "//div[.='Service Address' or .='Adresse de service']/following-sibling::div")
	public WebElement serviceAddressText;

	@FindAll({@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add business product(s)')]"),
		@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add business products') or contains(@name,'addProduct') or contains(text(),'Ajouter des produits d')]")})
	public List<WebElement> addBusinessProductsButton;


	@FindBy(how = How.XPATH, using = "//div[.='At this time, there are no Rogers Internet services available at this location.']")
	public WebElement noServiceErrorMessage;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Remove this site') or contains(text(),'Retirer ce site')]")
	public List<WebElement> removeThisSiteButton;

	@FindBy(how = How.XPATH, using = "//*[text()='Buy Business Internet' or text()='Acheter Internet Affaires']")
	public WebElement buyBusinessProductsButton;

	@FindBy(how = How.XPATH, using = "//div[@c-pbfosquoteproductitems_pbfosquoteproductitems='' and contains(@class,'text-body--regular')]")
	public WebElement addedProductName;

	@FindBy(how = How.XPATH, using = "(//div[@c-pbfosquoteproductitems_pbfosquoteproductitems='' and contains(@class,'text-body--regular')])[5]")
	public WebElement addedProductSecondName;

	@FindBy(how = How.XPATH, using = "//div[.='Unit price' or .='Prix unitaire']/parent::div/parent::div/following-sibling::div/div/div[2]/span")
	public WebElement addedProductUnitPrice;

//	@FindBy(how = How.XPATH, using = "(//div[.='Line total']/parent::div/parent::div/following-sibling::div/div/following-sibling::div/following-sibling::c-pbf-discount-calculation/div/div/following-sibling::div[contains(@class,'redcolor')])[1]")
//	public WebElement addedSecondProductUnitPrice;
	
	@FindBy(how = How.XPATH, using = "(//div[.='Line total']/parent::div/parent::div/following-sibling::div/div/div/following-sibling::div/span/following-sibling::button[contains(.,'Remove')]/preceding-sibling::span)[last()]")
	public WebElement addedSecondProductUnitPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'redcolor')]")
	public WebElement addedProductUnitPriceWithPromo;

	@FindBy(how = How.XPATH, using = "//div[.='Quantity' or .='Quantité']/parent::div/parent::div/following-sibling::div/div/div[3]")
	public WebElement addedProductQty;

	@FindBy(how = How.XPATH, using = "(//div[.='Quantity']/parent::div/parent::div/following-sibling::div/div/div[3])[2]")
	public WebElement addedSecondProductQty;

	@FindBy(how = How.XPATH, using = "(//div[.='Line total']/parent::div/parent::div/following-sibling::div/following-sibling::div/following-sibling::div/div/div/span)[last()]")
	public WebElement addedSecondProductLineTotal;
	
	@FindBy(how = How.XPATH, using = "//div[.='Line total' or .='Prix de la ligne']/parent::div/parent::div/following-sibling::div/div/div[4]/span")
	public WebElement addedProductLineTotal;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Ethernet Fibre')]//ancestor::div[contains(@class,'grid nds')]//div[2][contains(@class,'large')]/span[contains(text(),'$')]")
	public WebElement ethernetAccessUnitPrice;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Ethernet Fibre')]//ancestor::div[contains(@class,'grid nds')]//div[4][contains(@class,'large')]/span[contains(text(),'$')]")
	public WebElement ethernetAccessLineTotal;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Ethernet Fibre')]//ancestor::div[contains(@class,'grid nds')]//div[2][contains(@class,'redcolor')])[2]")
	public WebElement ethernetAccessSecondUnitPrice;

	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Ethernet Fibre')]//ancestor::div[contains(@class,'grid nds')]//div[4][contains(@class,'redcolor')]/span[contains(text(),'$')])[2]")
	public WebElement ethernetAccessSecondLineTotal;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'redcolor')]/span")
	public WebElement addedProductLineTotalWithPromo;
  
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'term') or contains(text(),'Abonnement de 36 mois')]"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Term') or contains(text(),'Abonnement de 36 mois')]")})

	public WebElement addedProductTerm;

	@FindBy(xpath = "//div[contains(text(),'Included') or contains(text(),'Comprend')]")
	public WebElement addedProductIncludedFeatures;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Hide details') or contains(text(),'Masquer les détails')]")
	public List<WebElement> hideDetailsLink;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Show more details ') or contains(text(),'Voir plus de détails')]"),
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Show more details ') or contains(text(),'Voir plus de détails')]")})
	public List<WebElement> showMoreDetailsLink;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Monthly fees subtotal') or contains(text(),'Total partiel des frais mensuels')]//following-sibling::div[contains(text(),'$')]")
	public WebElement addedProductMonthlyFeesSubtotal;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'One-time fees subtotal') or contains(text(),'Total partiel des frais uniques')]//following-sibling::div[contains(text(),'$')]")
	public WebElement addedProductOneTimeFeesSubtotal;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Internet Installation') or contains(text(),'Installation Internet')]//following-sibling::div[4]"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Ethernet Fibre Network Access Installation')]//following-sibling::div[4]")})
	public WebElement addedProductInstallationLinetotal;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total monthly fees') or contains(text(),'Total des frais mensuels')]")
	public WebElement addedProductTotalMonthlyFeesLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Total one-time fees') or contains(text(),'Total des frais uniques')]")
	public WebElement addedProductTotalOneTimeFeesLabel;

	@FindBy(how = How.XPATH, using = "//div[@class='price']")
	public WebElement addedProductTotalMonthlyFeesValue;

	@FindBy(how = How.XPATH, using = "//div[@class='price']/following-sibling::div//div[contains(@class,'price-secondary')][2]")
	public WebElement addedProductTotalMonthlyFeesSubValue;

	@FindBy(how = How.XPATH, using = "(//div[@class='price'])[2]")
	public WebElement addedProductTotalOneTimeFeesValue;

	@FindBy(how = How.XPATH, using = "(//div[@class='price'])[2]/following-sibling::div//div[contains(@class,'price-secondary')][2]")
	public WebElement addedProductTotalOneTimeFeesSubValue;

	//	@FindBy(how = How.XPATH, using = "//button[.='Remove'][not(contains(@class,'mob'))]")
	//	public WebElement removeProductButton;

	@FindAll({ @FindBy(xpath = "(//button[.='Remove' or .='Retirer'][contains(@class,'nds-button')])[last()]"),
		@FindBy(xpath = "//button[.='Remove' or .='Retirer'][not(contains(@class,'mob'))]")})
	public WebElement removeProductButton;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Proceed to checkout') or contains(text(),'Passer à la caisse')]")
	public WebElement proceedToCheckoutButton;

	@FindBy(how = How.XPATH, using = "//div[text()='Ethernet Fibre Network Access E100']")
	public WebElement ethernetAccessE100PlanLabel;

	@FindBy(how = How.XPATH, using = "//div[text()='Ethernet Fibre Network Access E1000']")
	public WebElement ethernetAccessE1000PlanLabel;

	@FindBy(xpath = "//*[.='Monthly fees']//following-sibling::div//div[contains(text(),'TV')]")
	public WebElement productNameTV;

	@FindAll({
		@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//button[.='Remove'][not(contains(@class,'vi'))]"),
		@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//button[.='Remove'][not(contains(@class,'mob'))]")})
	public WebElement removeTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//*[contains(text(),'Hide details')]")
	public WebElement hideDetailsTVAddons;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary//div[contains(@class,'body')]")
	public WebElement addonsNameTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary/div/div[2]/span")
	public WebElement unitPriceAddonsTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary/div/div[3]")
	public WebElement quantityAddonsTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary/div/div[4]/span")
	public WebElement linePriceAddonsTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary//*[contains(text(),'Edit')]")
	public WebElement addonsEditTV;

	@FindBy(xpath = "//*[.='Monthly fees']")
	public WebElement monthlyFeesSectionLabelTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]/div/div[2]/span")
	public WebElement unitPriceTV; 

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]/div/div[4]/span")
	public WebElement linePriceTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]/div/div[3]")
	public WebElement addedProductQuantityTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary/div/div[2]/span")
	public WebElement addedOnsUnitPriceTV;

	@FindBy(xpath = "//div[contains(text(),'TV')]//ancestor::div[contains(@class,'p-top')]//c-pbf-os-add-on-summary/div/div[4]/span")
	public WebElement addedOnsLinePriceTV;

	@FindBy(xpath = "//*[.='One-time fees']/../c-pbf-os-quote-summary-one-time-items")
	public WebElement oneTimeInstallationFeesSectionTV;

	@FindBy(xpath = "(//div[.='Installation']//following-sibling::div/div[contains(text(),'$')])[3]")
	public WebElement oneTimeInstallationFeesTV;

	@FindBy(xpath = "//div[.='Need to add a new site or edit selected sites?']")
	public WebElement needNewSiteEditSiteLabel;

	@FindBy(xpath = "//button[.='Add sites']")
	public WebElement addSiteButton;

	@FindBy(xpath = "//div[contains(@class,'serviceAddress')]")
	public List<WebElement> addedSitesBlocks;


}

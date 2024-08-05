package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:2-Feb-2022
 *
 *         PBF> PBF Order Review page objects
 */
public class SFDC_PBF_Multisite_OrderReview_Objects {

	public SFDC_PBF_Multisite_OrderReview_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add contact') or contains(text(),'Ajouter contact')]")
	public List<WebElement> addContactButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Monthly fees']//following-sibling::div//div[@class='nds-grid']//div[@c-pbfosquoteproducts_pbfosquoteproducts='' and contains(@class,'text-body--regular')]")
	public List<WebElement> addedProductName;
	
	@FindBy(how = How.XPATH, using = "//div[@c-pbfosquotesummarymainproductdesc_pbfosquotesummarymainproductdesc='' and contains(text(),' term')]")
	public List<WebElement> addedProductTerm;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid']//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'text-body--regular')]")
	public List<WebElement> addedAddOnsName;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid']//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'text-body--regular')]//ancestor::div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'nds-wrap')]//div[3]")
	public List<WebElement> addedAddOnsQty;
	
	@FindBy(how = How.XPATH, using = "//div[text()='One-time fees']")
	public List<WebElement> oneTimeFeesForEachSection;
	
}

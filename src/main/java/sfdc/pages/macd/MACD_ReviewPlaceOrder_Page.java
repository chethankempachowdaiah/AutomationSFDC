package sfdc.pages.macd;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.PDFHelper;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_ReviewPlaceOrder_Page extends MyListener{

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_ReviewPlaceOrder_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate existing addOn for Apple care in one time section
	 * @throws Exception 
	 * 
	 */
	public void validate_ProductInOneTimeSection(String addOnDP, String price) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "ValidateProductInOneTimeSection@: ";

			if(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.size() > 1) {
				sf.seleU.ScrolltoElementPageCenter(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.get(0));

				String productName[] = sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.get(0)).split(" ");
				String finalProduct = productName[1] + " " +productName[2];

				if(finalProduct.trim().equals(addOnDP) 
						&& sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.get(1)).trim().replaceAll("[^\\d.]", "").equals(price) 
						&& sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.get(2)).trim().equals("1")
						&& sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.appleCareProdDtlsInOneTimeSec.get(3)).trim().replaceAll("[^\\d.]", "").equals(price)
						&& sf.seleU.isElementDisplayed(sf.macdReviewPlaceOrdObj.oneTimeFeesSubTotalText)
						&& sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.oneTimeFeesSubTotalPrice).trim().replaceAll("[^\\d.]", "").equals(price)) {

					reportStatusPass(methodName+ "Product is present as expected with details as" +
							" Product Name : " + addOnDP + " Product Price : " + price + " Product Quantity : " + "1"
							,true, false);	

				} else {
					reportStatusFail("Product is present in the one time section but the attributes are not matched", true);
				}
			} else {
				reportStatusFail("Product is not present the review order page", true);
			}

		}catch(Exception e) {
			reportStatusFail("Error in validating the product in the one time section. ", e);
			e.printStackTrace();
		}
	}


	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate Total One Time Fees Section
	 * @throws Exception 
	 * 
	 */
	public void validateTotalOneTimeScetion(String price) throws IOException {

		try {
			//Validate Recently Made Summary Changes Text
			sf.seleU.wait(2000);
			verifyFieldValue("Recently Made Summary Changes Text", sf.macdReviewPlaceOrdObj.recentlyMadeWirelessServiceText, sf.waData.recentlyMadeWirelessServiceText);
			//Validate Unit Price
			sf.seleU.wait(2000);
			verifyFieldValue("Total One Time Fees Subtext", sf.macdReviewPlaceOrdObj.totalOneTimeFeesSubText, sf.waData.oneTimeFeesSubText_MACD);
			//Validate Line Total
			verifyFieldValue("Total One Time Fees", sf.macdReviewPlaceOrdObj.totalOneTimeFees, price);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating the Total One Time Fees details in the Review Place Order Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate added items name and price in Cart on the Shopping Cart
	 * Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_ExtraAddOn_ChangeInFee_Section(String[] item, String[] action, String price[], int quantity) throws Exception {
		try {
			methodName = "WACC_ExtraAddOn_ChangeInFee_Section_Page@: ";
			String action_itemName = ""; 
			for(int i = 0; i<item.length; i++) {

				action_itemName = action[i].toLowerCase()+ " " +item[i]; // It will combine the action and the product name i.e. adding/removing extra feature addon
				if(!action_itemName.trim().contains("Apple Care")) {
					sf.seleU.wait(8000);
					WebElement eleItem = driver.findElement(By.xpath("//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+action_itemName+"']"));
					WebElement eleUnitPrice = driver.findElement(By.xpath("(//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+action_itemName+"']/parent::div/following-sibling::div/div)[1]"));
					WebElement eleQuantity = driver.findElement(By.xpath("(//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+action_itemName+"']/parent::div/following-sibling::div/div)[2]"));
					WebElement eleLinePrice = driver.findElement(By.xpath("(//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+action_itemName+"']/parent::div/following-sibling::div/div)[3]"));
					// perform the validation for the unit price and the quantity
					sf.seleU.ScrolltoElementPageCenter(eleItem);
					if(validate_itemAndPrice(eleItem,eleUnitPrice, eleQuantity, eleLinePrice, item[i], price[i], quantity )) {
						reportStatusPass(methodName + item[i] + " is matched under extra addon change in fee with price " + price[i] + " and Quantity is: "+ quantity, true, true);
					} else {
						reportStatusFail("Error in validating the the extra addon change in the fee section", true);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in add item value on MACD Review Order page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate total monthly fees change for the extra addon or device protection.
	 * Method to validate difference in New Total Monthly Fees and Current Monthly fees subtotal
	 * Apple care does not apply in the monthly section
	 * @throws Exception
	 * 
	 */
	public void verify_CalculateTotalMonthlyFeesChanges(String price[]) throws Exception {
		try {
			methodName = "WACC Monthly Fees Changes@: "; double monthlyFeesChange = 0.00;double newMonthlyTotalFees = 0.00;
			double currentMonthlyFeesSubtotal = 0.00; double diffMonthlyFeesAmount = 0.00;
			sf.seleU.ScrolltoElementPageCenter(sf.macdReviewPlaceOrdObj.newTotalMonthlyFee);	

			for (int j=0; j<price.length; j++) {
				// Add the individual change in the fee of the product
				monthlyFeesChange = AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(price[j]) + monthlyFeesChange);
			}
			verifyFieldValue_WithFormat("Total Change in monthly fees", sf.macdReviewPlaceOrdObj.monthlyFeesChang, String.valueOf(monthlyFeesChange));						
			newMonthlyTotalFees = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.newTotalMonthlyFee) 
					+ "." + sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.newTotalMonthlySubFee)) ;			
			currentMonthlyFeesSubtotal = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.currentMonthlyFeesSubTotal).replaceAll("[^0-9.]", ""));
			diffMonthlyFeesAmount =  AdditionalUtilities.roundOffDouble2DecimalPlaces(newMonthlyTotalFees - currentMonthlyFeesSubtotal);				

			if(diffMonthlyFeesAmount == monthlyFeesChange) {
				reportStatusPass("Prices are matched between total monthly change in fee " + monthlyFeesChange + 
						" and " + "difference fee amount " + diffMonthlyFeesAmount, true, true);
			} else {
				reportStatusFail("Price didn't matched as exepected for the difference amount", true);
			}			   
		}
		catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate monthly in the unit and line price in the change in the fee section
	 * Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_monthlyIsDisplayedInChangeInFeeSection(String item) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";

			WebElement eleItem = driver.findElement(By.xpath("//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+item+"']"));
			WebElement eleUnitPrice = driver.findElement(By.xpath("//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+item+"']/parent::div/following-sibling::div/div[1]"));		
			WebElement eleLinePrice = driver.findElement(By.xpath("//div[contains(text(),'Changes in fee')]/../../following-sibling::div/div/div[text()='"+item+"']/parent::div/following-sibling::div/div[3]"));

			sf.seleU.ScrolltoElement(eleItem);

			if(sf.seleU.getTextFromWebElement(eleUnitPrice).trim().contains("/mo")) {
				reportStatusPass(methodName + item + "monthly is displayed for the uit price", true, true);
			} else {
				reportStatusFail("<Monthly is not displayed for the unit price", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in verifying the monthly price", true);
			e.printStackTrace();
		}

	}

	public boolean validate_itemAndPrice(WebElement eleItem, WebElement eleUnitPrice, WebElement eleQuantity,WebElement eleLinePrice, String item, String price, int quantity) throws Exception {
		try {
			if (sf.seleU.isElementDisplayed(eleItem) && sf.seleU.isElementDisplayed(eleUnitPrice) && sf.seleU.isElementDisplayed(eleQuantity)) {
				double linePrice = Double.parseDouble(sf.seleU.getTextFromWebElement(eleLinePrice).replaceAll("/mo", "").replaceAll("\\$", "").trim());
				double temp =Double.parseDouble(price)*(double)quantity;
				//quantity == Integer.parseInt(sf.seleU.getTextFromWebElement(eleQuantity)) 
				if(linePrice== temp) {
					return true;
				}				
			} else {
				reportStatusFail(item + price + " has not present on WACC Shopping Cart page!", true);
				return false;
			}
		} catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();				
		}
		return false;
	}
	
	/**
	 * Method developed on @Date: 26.04.2022 by @author Pankaj Agarwal
	 * 
	 *                     1- Verify 
	 *                     2- Verify the product items prices in the pdf before transaction section
	 * 
	 */
	public void verifyItemsBeforeTransactionsInPDF(String action, String fileName) throws Exception {

		// Retrieve data required for different products for which the PDF prices need to be verified
	//	Hashtable<String, String> productsWithPrice = new Hashtable<String, String>();
		LinkedHashMap<String, String> productsWithPrice = new LinkedHashMap<String, String>();
		//Verify prices for the existing items in the 
		
		if(!waData.WACC_PlanName.equals("NA")) {
			productsWithPrice.put(waData.WACC_PlanName, waData.WACC_Plan_Price);
		}
		
		if(!waData.WACC_Multiple_AddOn.equals("NA")) {
			for(int i = 0; i<waData.WACC_ProductsInPDFBeforeTransactions.length; i++) {
				productsWithPrice.put(waData.WACC_ProductsInPDFBeforeTransactions[i], waData.WACC_ProductsPricesInPDFBeforeTransactions[i]);
			}
		}		
		// Read pdf and extract the text from the PDF
		String pdfText = PDFHelper.readPDFFileLineByLine(fileName);	
	
		// extract the text only for the recently items added or removed
		String pdfBeforeTransactionsItems = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf("Pricing Details before Transaction".toUpperCase()), pdfText.toUpperCase().indexOf(action.toUpperCase()));
		System.out.println("Before transactions items are" + " are =====>>>" + pdfBeforeTransactionsItems);
		// Verify the products in the pdf which were added or removed
		verifyProductPriceInPDF(productsWithPrice,action,pdfBeforeTransactionsItems,"Pricing Details before Transaction");
	}

	/**
	 * Method developed on @Date: 26.04.2022 by @author Pankaj Agarwal
	 * 
	 *                     1- Verify
	 * 
	 *                     2- Click on Download PDF button
	 * 
	 *                     3- Verify the products discounts and
	 *                     compare the prices in pdf
	 * 
	 */
	public void verifyItemsAddedRemovedInPDF(String action, String fileName) throws Exception {
		
		// Retrieve data required for different products for which the PDF prices need to be verified
		LinkedHashMap<String, String> productsWithPrice = new LinkedHashMap<String, String>();
		//Verify prices for Internet product
		if(!waData.WACC_Multiple_AddOn.equals("NA")) {
			for(int i = 0; i<waData.WACC_Multiple_AddOn.length; i++) {
				productsWithPrice.put(waData.WACC_Multiple_AddOn[i], waData.WACC_Multiple_AddOnPrice[i]);
			}
		}		
		// Read pdf and extract the text from the PDF
		String pdfText = PDFHelper.readPDFFileLineByLine(fileName);	
	
		PDFHelper.IsTextPresentInPDF(pdfText, "Proposal");
		PDFHelper.IsTextPresentInPDF(pdfText, "Transaction Summary");

		// extract the text only for the recently items added or removed
		String pdfBeforeItemsAdded_Removed = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf(action.toUpperCase()), pdfText.toUpperCase().indexOf("Pricing Details after Transaction".toUpperCase()));
		System.out.println(action + " are =====>>>" + pdfBeforeItemsAdded_Removed);
		// Verify the products in the pdf which were added or removed
		verifyProductPriceInPDF(productsWithPrice,action,pdfBeforeItemsAdded_Removed,"");
	}

	/**
	 * Method developed on @Date: 26.04.2022 by @author Pankaj Agarwal
	 * 
	 *                     1- Verify
	 * 
	 *                     2- Click on Download PDF button
	 * 
	 *                     3- Verify the products discounts and
	 *                     compare the prices in pdf
	 * 
	 */
	public void verifyItemsAfterTransactionInPDF(String action, String location) throws Exception {

		LinkedHashMap<String, String> productsWithPrice = new LinkedHashMap<String, String>();
		// Retrieve data required for different products from the sheet which the PDF prices need to be verified
		if(!waData.WACC_ProductsInPDFAfterTransactions[0].equals("NA")) {
			for(int i = 0; i<waData.WACC_ProductsInPDFAfterTransactions.length; i++) {
				productsWithPrice.put(waData.WACC_ProductsInPDFAfterTransactions[i], waData.WACC_ProductsPricesInPDFAfterTransactions[i]);
			}
		}	

		if(!waData.WACC_PlanName.equals("NA")) {
			productsWithPrice.put(waData.WACC_PlanName, waData.WACC_Plan_Price);
		}

		//	extractTextProductPriceInPDF(productsWithPrice, action, fileName, Constants.TERMSANDCONDITIONS_INTERNET_IBLC);
		// Read pdf and extract the text from the PDF
		String pdfText = PDFHelper.readPDFFileLineByLine(location);

		// extract the text only after the transaction
		String productsInPDFAfterTransactions = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf("Pricing Details after Transaction".toUpperCase()));
		System.out.println(" After transaction " + productsInPDFAfterTransactions);	

		// verify the product and the prices in the pdf after transaction section
		verifyProductPriceInPDF(productsWithPrice,action,productsInPDFAfterTransactions,"Pricing Details after Transaction");
	}

	/**
	 * @throws IOException Added by Pankaj agarwal
	 * 
	 *                     1- Verify PDF items as matched from the expected sheet values
	 *                     2- Verify the prices in the before transaction, after transaction and in the items added/removed section
	 *                     3. Verify the effective date in the items added/removed section
	 */
	public void verifyProductPriceInPDF(LinkedHashMap<String, String> productsWithPrice, String action, String pdfText, String transaction)
			throws Exception {

		// PDF reader variables and location of PDF file
		String[] allPrices;
		String actual_Price;
		String productName;
		String expected_ExcelPrice;
		String quantity = "1";;
		String effective_date;
		

		try {
			// Forloop to loop thru all products in the productsWithPrice & validate the prices
			for (String name: productsWithPrice.keySet())         //iteration over keys  
			{  
				//returns the value to which specified key is mapped  
				expected_ExcelPrice = productsWithPrice.get(name);  
				System.out.println("Product Name is "+name+" Price is "+expected_ExcelPrice);  
				productName = name;

				// Get prices array for the specific product from the UI
				if(transaction.contains("Pricing Details after Transaction") || transaction.contains("Pricing Details before Transaction")) {
					allPrices = GetPricesForProductFromPDFAfterTransactions(productName, pdfText, action);
				}
				else {
					allPrices = GetPricesForProductFromPDF(productName, pdfText, action);
					System.out.println(waData.WACC_Expected_Effective_DateOption.get(productName));
					String expectedEffectiveDate = waData.WACC_Expected_Effective_DateOption.get(productName);
					
					// verify the effective Date in the pdf
					effective_date = GeteffectiveDateForProductFromPDF(productName, pdfText, action);				
					System.out.println("actual " + effective_date);
					verifyTextValue("Effective Dates for product " + productName,effective_date,expectedEffectiveDate.trim());			
				}
				
				if (allPrices != null) {
					actual_Price = (allPrices[allPrices.length - 1].replace("$", "")).replace("\r", "");
			
					// verify price of the given product
					PDFHelper.ValidatePricesOnQuantity(productName, quantity, expected_ExcelPrice, actual_Price);

				} else {
					reportStatusFail(methodName + ": No products for " + productName + " were selected in the quote",
							true);
				}
			} 

		} catch (Throwable e) {
			reportStatusFail(methodName + ": Error in  verifying the prices in PDF", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException Added by Pankaj Agarwal
	 * 
	 *                     1-Click on Download PDF button
	 * 
	 */
	public void clickDownloadPDFButton() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();

			// Verify Download PDF Button is displayed
			if (sf.seleU.isElementDisplayed(sf.gdPdf.downloadPDFButton.get(0))) {
				reportStatusPass(methodName + ":  Download PDF button is displayed", true, true);
			} else {
				reportStatusFail(methodName + ":  Error in Downloading Quote PDF", true);
			}

			// Delete Pre-Existing Downloaded files
			deleteExistingFilesInFolder(Constants.DOWNLOADS_LOCATION);

			// Click on Download Button
			sf.seleU.clickElementByJSE(sf.gdPdf.downloadPDFButton.get(0));
			reportStatusPass(methodName + ":  Clicked on Download PDF Button", true, false);
			sf.seleU.wait(7000);
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(methodName + ": Error in clicking on the Download Button", e);
			e.printStackTrace();
		}
	}

	public static String[] GetPricesForProductFromPDF(String productName, String pdfText, String action) throws IOException{
		String extractedText;
		String[] lines;
		String[] prices = null;
		int lineSplitIndex;

		try {
			if(action.contains("Item(s) Added"))
				lineSplitIndex = 2;
			else
				lineSplitIndex = 3;

			if(productName.contains("Unlimited Canada to US/Intl SMS/MMS")) {
				String addOnProductName[] = productName.split(" ");
				String addOnProduct = addOnProductName[0] + " " +addOnProductName[1] + " " +addOnProductName[2];
				System.out.println(addOnProduct);

				if (pdfText.toUpperCase().contains(addOnProduct.toUpperCase())){
					productName = addOnProduct;
					if(action.contains("Item(s) Added"))
						lineSplitIndex = 3;
					else
						lineSplitIndex = 4;
				}
			}

			if (pdfText.toUpperCase().contains(productName.trim().toUpperCase())) {
				// Split the string to get the products prices and quantity
				extractedText = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf(productName.toUpperCase()));
				
				// split based on line
				lines = extractedText.split("\n");
		
				// extract the exact line for the product price
				prices = lines[lineSplitIndex].split(" ");
				System.out.println(prices[0]);
			}

		}catch (Throwable e) {
			reportStatusFail(methodName + ": Error in clicking on the Download Button", e);
			e.printStackTrace();
		}
		return prices;
	}

	public static String[] GetPricesForProductFromPDFAfterTransactions(String productName, String pdfText, String action) throws IOException{
		String extractedText;
		String[] lines;
		String[] productPrice = null;
		
		int lineSplitIndex = 0;

		try {

			if(productName.contains("Unlimited Canada to US/Intl SMS/MMS")) {
				String addOnProductName[] = productName.split(" ");
				String addOnProduct = addOnProductName[0] + " " +addOnProductName[1] + " " +addOnProductName[2];
				System.out.println(addOnProduct);

				if (pdfText.toUpperCase().contains(addOnProduct.toUpperCase())){
					productName = addOnProduct;
					lineSplitIndex = 2;
				}
			}

			if(productName.contains("Unlimited Wireless Business")) {
				if (pdfText.toUpperCase().contains(productName.toUpperCase()))
					lineSplitIndex = 4;				
			}

			if (pdfText.toUpperCase().contains(productName.trim().toUpperCase())) {
				// Split the string to get the products prices and quantity
				extractedText = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf(productName.toUpperCase()));
				System.out.println(extractedText);
				// split based on line
				lines = extractedText.split("\n");
				// System.out.println(lines[0]);

				// extract the exact line for the product price
				productPrice = lines[lineSplitIndex].split(" ");
				System.out.println(productPrice[0]);
			}

		}catch (Throwable e) {
			reportStatusFail(methodName + ": Error in extracting the item price after transaction", e);
			e.printStackTrace();
		}
		return productPrice;
	}

	
	/**
	 * @throws IOException Added by Pankaj agarwal on 2ndMay2022
	 *         
	 *                     1. Verify the effective date in the items added/removed section by the extracting the index of the
	 *                     by the extracting the index of the product with respect to date in the extracted string of a pdf
	 */
	public static String GeteffectiveDateForProductFromPDF(String productName, String pdfText, String action) throws IOException{
		String extractedText;
		String[] lines;
		String[] prices = null;
		String effectiveDate = "";
		int lineSplitIndex;

		try {	
			if(action.contains("Item(s) Added"))
				lineSplitIndex = 1;
			else
				lineSplitIndex = 2;
			
			if(productName.contains("Unlimited Canada to US/Intl SMS/MMS")) {
				String addOnProductName[] = productName.split(" ");
				String internetProduct = addOnProductName[0] + " " +addOnProductName[1] + " " +addOnProductName[2];
				System.out.println(internetProduct);

				if (pdfText.toUpperCase().contains(internetProduct.toUpperCase())){
					productName = internetProduct;
			
					if(action.contains("Item(s) Added"))
						lineSplitIndex = 2;
					else
						lineSplitIndex = 3;
				}	
			}

			if (pdfText.toUpperCase().contains(productName.trim().toUpperCase())) {

				// Split the string to get the products prices and quantity
				extractedText = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf(productName.toUpperCase()));
		
				// split based on line
				lines = extractedText.split("\n");
				
				// extract the exact line for the effective date
				if(action.contains("Item(s) Added"))
					effectiveDate = lines[lineSplitIndex].split(":")[1].trim();
				else
					effectiveDate = lines[lineSplitIndex].trim();			
					System.out.println(effectiveDate);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + ": Error in extracting the effective date", e);
			e.printStackTrace();
		}
		return effectiveDate;
	}


	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Verify the matched with the expected and the actual value
	 * @throws Exception 
	 * 
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 14.04.2022 by @author Pankaj Agarwal
	 * 
	 * Verify the matched with the expected and the actual value
	 * @throws Exception 
	 * 
	 */
	public static void verifyTextValue(String fieldName, String actualText, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (actualText.contains(expectedText)) {
				reportStatusPass(" Validated " + fieldName + " is matched " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is not matched " +actualText
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue_WithFormat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).replaceAll("/mo", "").replaceAll("\\$", "").replaceAll(",", "").trim().contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText().replaceAll("/mo", "").replaceAll("\\$", "")
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date:18.04.2022  by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Terms and Condition link on Review & Place Order Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_TermsAndConditionLink(String url) throws Exception {
		// TODO Auto-generated method stub
		sf.seleU.waitTillLoading();
		try {
			methodName = "Review & Place Order Page@: ";
			String str = sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.termsAndConditionLink);
			if (sf.seleU.getTextFromWebElement(sf.macdReviewPlaceOrdObj.termsAndConditionLink).contains(url)) {
				reportStatusPass(methodName + " Terms and Condition link has matched.", true, true);
			} else {
				reportStatusFail("Terms and Condition link has matched on Order Summary Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Terms and Condition link on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 19.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  validate text in PDF
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_text_InPDF(String text, String downloadLocation)throws Exception {
		// TODO Auto-generated method stub
		try {			
			String pdfcontent = PDFHelper.readPDFFileLineByLine(downloadLocation);			
			if (pdfcontent.contains(text)) {
				reportStatusPass(" Validated text: " + text + " in PDF ", true, true);				
			} else {
				reportStatusFail(text + " not present in PDF ", true);
			}		
		}catch (Exception e) {
			reportStatusFail("Error in Download order Summary button on Order Summary Page.", true);
		}
	}

}



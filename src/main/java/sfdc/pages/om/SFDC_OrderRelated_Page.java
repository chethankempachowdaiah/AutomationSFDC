package sfdc.pages.om;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Order Related Tab
 */
public class SFDC_OrderRelated_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_OrderRelated_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Order Related tab
	 * 
	 *                     Verify Orchestartion Plan is displayed
	 * 
	 *                     Click on orchestration plan number
	 * 
	 */
	public void verifyOrchestrationPlanInOrder() throws IOException {
		try {
			String methodName = "SFDC_Order_Related@: ";

			// Click on Order Related tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			// Verify Orchestartion Plan is displayed
			sf.seleU.wait(5000);
			sf.seleU.waitElementToBeVisible(sf.orRelated.orderOrchestrationPlanNumber);
			sf.seleU.wait(4000);
	//		sf.seleU.ScrolltoElement(sf.orRelated.orderOrchestrationPlanNumber);
			if (sf.seleU.isElementDisplayed(sf.orRelated.orderOrchestrationPlanNumber)
					&& sf.seleU.getTextFromWebElement(sf.orRelated.orderOrchestrationPlanNumber).length() > 0) {

				Global.dataInput.orchestrationPlanName = sf.seleU
						.getTextFromWebElement(sf.orRelated.orderOrchestrationPlanNumber);

				reportStatusPass(methodName + " Verified orchestartion plan number in Order Relted Tab", true, true);

			} else {
				reportStatusFail(" Error in Viewing Orchestration Plan in Order", true);
			}
			sf.seleU.wait(6000);

			// Click on orchestration plan number
			sf.seleU.clickElementByJSE(sf.orRelated.orderOrchestrationPlanNumber);
			reportStatusPass(methodName + " Clicked on Orchestration Plan in Order", true, false);

			sf.seleU.hardwait(5000);

			/*
			 * Applicable for cancelled order : (flow needs to be updated ) if a order is
			 * cancelled, then a Superseded order will be created and the orchestration plan
			 * will move over there
			 */

			//				if (sf.seleU.isElementDisplayed(sf.orRelated.supersedingOrderSection))
			//					sf.seleU.ScrolltoElement(sf.orRelated.supersedingOrderSection);
			//
			//				if (sf.seleU.getTextFromWebElement(sf.orRelated.supersedingOrderNumber).length() > 0) {
			//
			//					reportStatusPass(methodName + " Verified Superseding Order in Order Relted Tab, Order Number :"
			//							+ sf.seleU.getTextFromWebElement(sf.orRelated.supersedingOrderNumber), true, true);
			//
			//					// Click on Superseding Order number
			//					sf.seleU.clickElementByJSE(sf.orRelated.supersedingOrderNumber);
			//					reportStatusPass(methodName + " Clicked on Superseding Order", true, false);
			//
			//					sf.seleU.hardwait(5000);
			//
			//				} else {
			//					reportStatusFail(" Error in Viewing Superseding Order Section", true);
			//				}
			//				sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Orchestration Plan in Order", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Order Related tab
	 * 
	 *                     Verify Orchestartion Plan is displayed
	 * 
	 *                     Click on orchestration plan number
	 * 
	 */
	public void verifyOrchestrationPlanForSupersedingOrder() throws IOException {
		try {
			String methodName = "SFDC_OrchestrationPlan_For_SupersedingOrder@: ";

			// Click on Order Related tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTabs);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			// Verify Orchestartion Plan is displayed
			sf.seleU.wait(6000);
			if (sf.seleU.isElementDisplayed(sf.orRelated.orderOrchestrationPlanNumber)
					&& sf.seleU.getTextFromWebElement(sf.orRelated.orderOrchestrationPlanNumber).length() > 0) {

				Global.dataInput.orchestrationPlanName = sf.seleU
						.getTextFromWebElement(sf.orRelated.orderOrchestrationPlanNumber);

				reportStatusPass(methodName + " Verified orchestartion plan number in Order Relted Tab", true, true);

			} else {
				reportStatusFail(" Error in Viewing Orchestration Plan in Order", true);
			}
			sf.seleU.wait(6000);

			// Click on orchestration plan number
			sf.seleU.clickElementByJSE(sf.orRelated.orderOrchestrationPlanNumber);
			reportStatusPass(methodName + " Clicked on Orchestration Plan in Order", true, false);

			sf.seleU.hardwait(5000);

	

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Orchestration Plan in Order", e);
			e.printStackTrace();
		}
	}
/* Method used to click on respective lette from the Activity Tab*/
	
	public void clickonletterlink(String letter) throws IOException {
		try {
			
			// Click on Order Related tab
			
			
			sf.seleU.wait(6000);
			
			/*sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			sf.dataInput.primaryContact = sf.seleU.getTextFromWebElement(sf.orDetails.customerAuthorValueText);*/
			if(letter.equals("WelcomeLetter")) {
			if (sf.seleU.isElementDisplayed(sf.manQue.Welcomeletter)){

				sf.seleU.clickElementByJSE(sf.manQue.Welcomeletter);
				
				sf.seleU.wait(2000);
				}
			}else if(letter.equals("WelcomeLetterCable")) {
				if (sf.seleU.isElementDisplayed(sf.manQue.WelcomeletterCable)){

					sf.seleU.clickElementByJSE(sf.manQue.WelcomeletterCable);
					
					sf.seleU.wait(2000);
				}
			}else if(letter.equals("Appointment Letter")) {
				if (sf.seleU.isElementDisplayed(sf.manQue.Instlttr)){

					sf.seleU.clickElementByJSE(sf.manQue.Instlttr);
					sf.seleU.wait(2000);
				}
			}
			else if(letter.equals("Closure Letter")) {
				if (sf.seleU.isElementDisplayed(sf.manQue.Closureletter)){

					sf.seleU.clickElementByJSE(sf.manQue.Closureletter);
					
					sf.seleU.wait(2000);
				}
			}
			else {
				reportStatusFail(" Error in clicking on the letter link", true);
			}
			sf.seleU.wait(6000);

			}

	

		catch (Throwable e) {
			reportStatusFail(" Error in clicking on the letter", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Click on Order Related tab
	 * 
	 *                     Verify Orchestartion Plan is displayed
	 * 
	 *                     Click on orchestration plan number
	 * 
	 */
	public void verifySupersededOrder() throws IOException {
		try {
			String methodName = "SFDC_verify_Superseded_Order@: ";

			// Click on Order Related tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			/*
			 * Applicable for cancelled order : (flow needs to be updated ) if a order is
			 * cancelled, then a Superseded order will be created and the orchestration plan
			 * will move over there
			 */

			if (sf.seleU.isElementDisplayed(sf.orRelated.supersedingOrderSection))
				sf.seleU.ScrolltoElement(sf.orRelated.supersedingOrderSection);

			if (sf.seleU.getTextFromWebElement(sf.orRelated.supersedingOrderNumber).length() > 0) {

				reportStatusPass(methodName + " Verified Superseding Order in Order Relted Tab, Order Number :"
						+ sf.seleU.getTextFromWebElement(sf.orRelated.supersedingOrderNumber), true, true);
				
				sf.omData.supersededOrderNumber = sf.seleU.getTextFromWebElement(sf.orRelated.supersedingOrderNumber).trim();

				// Click on Superseding Order number
				sf.seleU.clickElementByJSE(sf.orRelated.supersedingOrderNumber);
				reportStatusPass(methodName + " Clicked on Superseding Order", true, false);

				sf.seleU.hardwait(5000);

			} else {
				reportStatusFail(" Error in Viewing Superseding Order Section", true);
			}
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Superseded Order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     EXtract all the product from order product items
	 * 
	 * 
	 */
	public void extractProductItemsInOrderRelated(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);
			sf.seleU.hardwait(2000);

			// Click on Quote line items view all
			sf.seleU.clickElementByJSE(sf.qr.orderProductItemsViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on order Product items view all", true, false);

			if (sf.qr.quoteLineItemsProductName.size() > 0) {
				for (int i = 0; i < sf.qr.productItemsName.size(); i++) {
					sf.seleU.wait(3000);
					Global.dataInput.orderRelatedProductItemsName
					.add(sf.seleU.getTextFromWebElement(sf.qr.productItemsName.get(i)).trim());
					// Matching with RDI Product Name With the product which was added during the
					// intial stage
					// on rogers business solution page

					reportStatusPass(methodName + "Product is found in the Product items list "
							+ sf.seleU.getTextFromWebElement(sf.qr.productItemsName.get(i)), true, false);
				}
			}

			else {
				sf.seleU.hardwait(2000);
				reportStatusFail(methodName + "Product is not found in the Order related product items list ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Compare Order product with Quote Line items from Quote
	 * 
	 * 
	 */
	public void compareOrderProductWithQuoteLineItesm(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(2000);
			compareValuesFromList(Global.dataInput.orderRelatedProductItemsName,
					Global.dataInput.quoteLineItemsProductName, "Product Items List");

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}



	/**
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Filter and verify all the product from order product items
	 * 
	 * 
	 */
	public void filterOrderProductItemsAndVerify(String expected_Product_Name, String expected_Quantity) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			boolean isFound = false; int quantity = 0; String actQuantity; 
			int index = 0;

			//			sf.seleU.waitElementToBeVisible(sf.orRelated.orderProductViewALLClick);
			//			// Click on related account viewAll in contact details page
			//			sf.seleU.clickElementByJSE(sf.orRelated.orderProductViewALLClick);
			//			reportStatusPass(methodName + "Clicked on the Related Accounts View all", true, false);
			//			sf.seleU.wait(2000);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterClick);
			reportStatusPass(methodName + "Clicked on related accounts Quick Filter button", true, false);
			sf.seleU.wait(1000);

			// Enter the account name to be filtered
			sf.seleU.clearAndEnterText(sf.orRelated.orderProductInputEnter, expected_Product_Name);
			reportStatusPass(methodName + "Enter the Account name as " + expected_Product_Name, true, false);
			sf.seleU.hardwait(3000);

			// Click on apply button after entering the account name
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterApplyButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Clicked on quick filter apply button ", true, false);

			// Click on Quick filter hide button
			sf.seleU.clickElementByJSE(sf.cd.contactHideFilterClick);

			//			String[] expProductValue = product_Name.split(",");
			//			List<String> expProductList = new ArrayList<String>(Arrays.asList(expProductValue));
			//			String product = expProductList.get(0);

			//			String[] expProductQuantityValue = expProductQuantity.split(",");
			//			List<String> expProductQuantityValueList = new ArrayList<String>(Arrays.asList(expProductQuantityValue));
			//			String quantity = expProductQuantityValueList.get(1);

			for (int i = 0; i < sf.orRelated.orderProductNameInFilteredTable.size(); i++) {
				sf.seleU.wait(2000);

				// Matching with Order Product Name Abbreviation type and extract the name and the quantity

				if (sf.seleU.getTextFromWebElement(sf.orRelated.orderProductNameInFilteredTable.get(i)).trim()
						.contains(expected_Product_Name.trim())) {

					if(sf.orRelated.orderProductNameInFilteredTable.size() > 1) {
						quantity = quantity + 1;
					} else {
						actQuantity = sf.seleU.getTextFromWebElement(sf.orRelated.orderProductQuantityInFilteredTable.get(i));

						int splitQuantity = actQuantity.indexOf(".");
						quantity = Integer.parseInt(actQuantity.substring(0, splitQuantity));
						System.out.println(quantity);
					}

					isFound = true;
					index = i;

				}
			}

			if(isFound == true) {
				System.out.println(expected_Quantity);
				if(quantity == Integer.parseInt(expected_Quantity.trim())) {
					reportStatusPass(methodName
							+ "Record is found in the order product list "
							+ sf.seleU.getTextFromWebElement(sf.orRelated.orderProductNameInFilteredTable.get(index)) + "with quantity as "
							+ quantity, true, true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Verify the RDI products and extract the product speed
	 * 
	 * 
	 */
	public void extractCableProductDetailsInOrderRelated(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			sf.seleU.hardwait(2000);
			// Click on Quote line items view all
			sf.seleU.clickElementByJSE(sf.qr.orderProductItemsViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on Product items view all", true, false);
			sf.seleU.wait(2000);

			// Verify all the below once for IBLC
			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
				filterOrderProductItemsAndVerify(dataTable.get("IBLC Product_1").split(",")[0], dataTable.get("IBLC_Lines").split(",")[0]);
				filterOrderProductItemsAndVerify(dataTable.get("IBLC Product_1").split(",")[1], dataTable.get("IBLC_Lines").split(",")[1]);
				filterOrderProductItemsAndVerify(dataTable.get("Equipment_Details_Name"), dataTable.get("Equipment_Details_Quantity"));
				filterOrderProductItemsAndVerify(dataTable.get("Installation_Detail_Product_Name").split(",")[1], dataTable.get("Line_And_Jack_Qty").split(",")[1]);
				//			filterOrderProductItemsAndVerify(dataTable.get("Installation_Detail_Product_Name").split(",")[0], "1"); // change it
				//			filterOrderProductItemsAndVerify(dataTable.get("Long Distance Plan"), dataTable.get("Long Distance Quantity"));
			}

			// Verify Tv Product product 
			if(!dataTable.get("TV Product Name").trim().equals("NA")) {
				for(int i =0; i<4; i++) {			
					filterOrderProductItemsAndVerify(dataTable.get("Rogers TV AddOn Product").split(",")[i], dataTable.get("Rogers TV AddOn Quantity").split(",")[i]);
				}
			}

			// Verify Internet Product product 

			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {
				filterOrderProductItemsAndVerify(dataTable.get("Internet Product Name"), "1");
				filterOrderProductItemsAndVerify(dataTable.get("Internet Equipment Details"), "1");
			}


		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Verify the RDI products and extract the product speed
	 * 
	 * 
	 */
	public void extractCableTVProductDetailsInOrderRelated(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			sf.seleU.hardwait(2000);
			// Click on Quote line items view all
			sf.seleU.clickElementByJSE(sf.qr.orderProductItemsViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on Product items view all", true, false);
			sf.seleU.wait(2000);

			fetchProductFromTable(sf.qr.productItemsName, sf.qr.productItemQuantity, dataTable.get("Office 365 AddOn"), dataTable.get("Office 365 AddOn Quantity"));
			fetchProductFromTable(sf.qr.productItemsName, sf.qr.productItemQuantity, dataTable.get("Rogers TV AddOn Product"), dataTable.get("Rogers TV AddOn Quantity"));
			fetchProductFromTableWithOutSplit(sf.qr.productItemsName, sf.qr.productItemQuantity, dataTable.get("Internet Product Name"), dataTable.get("Internet Product Quantity No"));
			fetchProductFromTableWithOutSplit(sf.qr.productItemsName, sf.qr.productItemQuantity, dataTable.get("Internet Equipment Details"), dataTable.get("Internet Product Quantity No"));
			fetchProductFromTableWithOutSplit(sf.qr.productItemsName, sf.qr.productItemQuantity, dataTable.get("TV Product Name"), dataTable.get("Internet Product Quantity No"));


		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Order Related tab
	 * 
	 *                     Verify Orchestartion Plan is displayed
	 * 
	 *                     Click on orchestration plan number
	 * 
	 */
	public void verifyNoOrchestrationPlanInOrder() throws IOException {
		try {
			String methodName = "SFDC_Order_Related@: ";

			// Click on Order Related tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			// Verify Orchestartion Plan is displayed
			if (sf.seleU.isElementDisplayed(sf.orRelated.orderOrchestrationPlanNumber)
					&& sf.seleU.getTextFromWebElement(sf.orRelated.orderOrchestrationPlanNumber).length() > 0) {

				reportStatusFail(methodName + " Orchestration PlanNumber is displayed for order in draft status", true);

			} else {
				reportStatusPass(" No Orchestration Plan after selecting the order in draft status", true, false);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing No Orchestration Plan for order in draft status", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 *                     PA-OMPI2Sp1
	 *                     Fetch Product from a Table [based on order product name and quantity]
	 *  ** For multiple product in single data cell in the excel sheet
	 */

	public void fetchProductFromTable(List<WebElement> actualProductList, List<WebElement> actualProductQuantityList, String expProduct, String expProductQuantity)
			throws IOException {
		try {
			int k=0;
			String methodName = "SFDC_Fetch Product Name";
			String[] expProductValue = expProduct.split(",");
			List<String> expProductList = new ArrayList<String>(Arrays.asList(expProductValue));
			String product = expProductList.get(0);

			String[] expProductQuantityValue = expProductQuantity.split(",");
			List<String> expProductQuantityValueList = new ArrayList<String>(Arrays.asList(expProductQuantityValue));
			String quantity = expProductQuantityValueList.get(1);

			for (int i = 0; i < actualProductList.size(); i++) {

				for (int j = 0; j < expProductList.size(); j++) {
					//			System.out.println("Size is" + expProductList.size());

					String actualProductName = sf.seleU.getTextFromWebElement(actualProductList.get(i));
					String actProductQuantityNo = sf.seleU.getTextFromWebElement(actualProductQuantityList.get(i)).trim();	

					System.out.println("Actual " + actualProductName + "actProductQuantityNo " + actProductQuantityNo );
					System.out.println("excel data " + expProductList.get(j).trim() + "Quantity " + expProductQuantityValueList.get(j).trim());

					String dataQuantity = expProductQuantityValueList.get(j);

					if (actualProductName.trim().contains(expProductList.get(j).trim()) &&
							actProductQuantityNo.contains(expProductQuantityValueList.get(j).trim())) {

						reportStatusPass(methodName + " Validated And Matched Product is " + expProductList.get(j) + " with quantity as " + 
								expProductQuantityValueList.get(j), true, true);

						// To come out of the outer for loop so that once all the verification is done then come out of it,
						// outer forlopp will go on until actualproduct size list is completed

						//						if(j == expProductList.size()-1) {
						//							i=actualProductList.size();
						//						}	 
						k = i;
						i = k;
						break;
					}
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Invalid Fetch From Product Table", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 *                     
	 *                     Fetch Product from a Table (verify only product NAme and quantity)
	 *                     ** Only for one product in single data cell in the excel sheet
	 * 
	 */

	public void fetchProductFromTableWithOutSplit(List<WebElement> actualProductList, List<WebElement> actualProductQuantityList, String expProduct, String expProductQuantity)
			throws IOException {
		try {

			String methodName = "SFDC_Fetch Product_Name_WithOut_Split";


			for (int i = 0; i < actualProductList.size(); i++) {

				String actualProductName = sf.seleU.getTextFromWebElement(actualProductList.get(i));
				String actProductQuantityNo = sf.seleU.getTextFromWebElement(actualProductQuantityList.get(i)).trim();	

				System.out.println("Actual " + actualProductName + "actProductQuantityNo " + actProductQuantityNo );
				System.out.println("excel data " + expProduct + "Quantity " + expProductQuantity.trim());


				if (actualProductName.trim().contains(expProduct.trim()) &&
						actProductQuantityNo.contains(expProductQuantity.trim())) {

					reportStatusPass(methodName + " Validated And Matched Product is " + expProduct + " with quantity as " + 
							expProductQuantity, true, true);

					break;
				}
			}


		} catch (Throwable e) {
			reportStatusFail(" Invalid Fetch From Product Table", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Order Related tab
	 * 

	 * 
	 */
	public void verifyOrderRelatedTabButton() throws IOException {
		try {
			String methodName = "SFDC_Order_Related@: ";

			// Click on Order Related tab
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Order Related Tab", e);
			e.printStackTrace();
		}
	}

	public void compareValuesFromList(List<String> knownVal, List<String> el, String fieldName)
			throws IOException, InterruptedException {
		String methodName = "SFDC_Compare_List_Values@: ";
		if (knownVal.size() == el.size()) {

			Collections.sort(knownVal);
			Collections.sort(el);

			for (int i = 0; i < el.size(); i++) {
				if (knownVal.get(i).equalsIgnoreCase(el.get(i))) {
					System.out.println("Value matched for " + knownVal.get(i) + " and " + el.get(i));
					reportStatusPass("Value matched for Quote line items with Product name in Order Related "
							+ knownVal.get(i) + " and " + el.get(i), true, false);

				} else {
					logger.info("Value does not matched for " + knownVal.get(i) + " and " + el.get(i));
					System.out.println("Value does not matched for " + knownVal.get(i) + " and " + el.get(i));
					reportStatusFail(methodName + "Value doesn't matched", true);
				}
			}
		} else {
			reportStatusFail(methodName + "Size mismatched", true);
			System.out.println("Values count is mismatched for " + fieldName);
		}
	}
}

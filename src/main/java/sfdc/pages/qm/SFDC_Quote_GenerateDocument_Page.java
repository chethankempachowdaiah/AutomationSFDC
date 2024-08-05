package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date: 30/Jan/2019
 * 
 *         SFDC Quote_GenerateDocument Page(Opportunity>create Quote>Configure
 *         Quote>Accept Quote>Generate Document)
 *
 */
public class SFDC_Quote_GenerateDocument_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_Quote_GenerateDocument_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Quote_GenearteDocument@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify 'PDFGenerationComplete!' alert and click on OK
	 * 
	 *                     2- Verify PDF is generated
	 * 
	 *                     3- Click on send email button
	 */
	public void sendQuoteNextButton() throws IOException {
		try {

			if (InputData.env.equals("ITDEVSTAGE") && !sf.seleU.getCurrentUrl().contains("partnercommunity")) {
				sf.seleU.waitElementToBeVisible(sf.gdPdf.accessbilityTitleFrame.get(0));
				sf.seleU.switchToDefaultContent();

				sf.seleU.switchToFrame(sf.gdPdf.accessbilityTitleFrame.get(0));
				sf.seleU.waitElementToBeVisible(sf.gdPdf.generateDocumentNextButton.get(0));

				// Click on Next button
				sf.seleU.clickElementByJSE(sf.gdPdf.generateDocumentNextButton.get(0));

			} else {
				sf.seleU.wait(20000);
				sf.seleU.switchToDefaultContent();
				sf.seleU.switchToElementFrame(sf.gdPdf.generateDocumentNextButton);
				sf.seleU.wait(15000);

				// Click on Next button
				sf.seleU.clickElementByJSE(sf.gdPdf.generateDocumentNextButton.get(0));
				sf.seleU.wait(50000);
			}

			reportStatusPass(methodName + "  Clicked on PDF Generation Next Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in  sending quote email", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Click on next in generate pdf
	 */
	public void generatePDFNextButton() throws IOException {
		try {
	
			sf.seleU.wait(30000);
			sf.seleU.waitElementToBeClickable(sf.gdPdf.generateDocumentNextButton.get(0));


			// Click on Next button
			sf.seleU.clickElementByJSE(sf.gdPdf.generateDocumentNextButton.get(0));
			
			sf.seleU.wait(40000);

			reportStatusPass(methodName + "  Clicked on PDF Generation Next Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in  Clicking Next in Generate PDF", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     1- Click on next button after sendemail & accept quote functionality
	 */
	public void sendQuoteNextButtonAfterResumeQuote() throws IOException {
		try {
			sf.seleU.wait(10000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.switchToElementFrame(sf.gdPdf.generateDocumentNextSecondButton);

			sf.seleU.clickElementByJSE(sf.gdPdf.generateDocumentNextSecondButton.get(0));
			reportStatusPass(methodName + "  Clicked on PDF Genaration Next Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in next button click after email and accept quote", e);
			e.printStackTrace();
		}
	}

	public void sendQuoteEmailDebug3() throws IOException {

		try {

			// Verify 'PDFGenerationComplete!' alert and click on OK

			int counter = 0;
			boolean isQuotePdfAlertPresent = false;

			while (!isQuotePdfAlertPresent) {

				sf.seleU.switchToDefaultContent();
				sf.seleU.refreshPage();
				sf.seleU.hardwait(9000);

				try {
					sf.seleU.switchToElementFrame(sf.gdPdf.pdfGenerationCompleteAlertText);
				} catch (Exception e) {
					sf.seleU.hardwait(9000);
					sf.seleU.refreshPage();
					sf.seleU.hardwait(15000);
					sf.seleU.switchToElementFrame(sf.gdPdf.pdfGenerationCompleteAlertText);
				}
				if (sf.seleU.isElementPresent(sf.gdPdf.pdfGenerationCompleteAlertText)) {
					isQuotePdfAlertPresent = true;
					sf.seleU.clickElementByJSE(sf.gdPdf.pdfGenerationCompleteAlertOkButton);
					break;
				}

				if (counter > 3) {
					break;
				}
				counter++;
			}

			// Verify PDF is generated
			if (isQuotePdfAlertPresent) {
				reportStatusPass(methodName + "  Alert '" + Global.dataInput.quotePDFAlertText + "' is verified", true,
						true);
			} else {
				reportStatusFail(methodName + " No such alert '" + Global.dataInput.quotePDFAlertText + "' is prsent",
						true);
			}

			sf.seleU.refreshPage();
			sf.seleU.wait(15000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.gdPdf.accessbilityTitleFrame.get(0));
			if (sf.seleU.isElementDisplayed(sf.gdPdf.sendEmailButton)) {
				reportStatusPass(methodName + "  Quote PDF is generated successfully", true, true);
			} else {
				reportStatusFail(methodName + " Error in generating Quote PDF", true);
			}

			// Click on send email button
			sf.seleU.clickElementByJSE(sf.gdPdf.sendEmailButton);
			sf.seleU.wait(25000);

			reportStatusPass(methodName + "  Clicked on Send Email Button", true, false);

		} catch (

				Throwable e) {
			reportStatusFail(" Error in  sending quote email", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException Added by Chethan K
	 * 
	 *                     1- Verify
	 * 
	 *                     2- Click on Download PDF button
	 * 
	 *                     3- Verify the products discounts and
	 *                     compare the prices in pdf
	 * 
	 */
	public void verifyAllProductPricesInPDF(Hashtable<String, String> dataTable, String fileName) throws Exception {

		// Retrieve data required for different products for which the PDF prices need to be verified
		Hashtable<String, String> productsWithPrice = new Hashtable<String, String>();
		//Verify prices for Internet product
		if(!InputData_Communities.commPBFAddProductName.equals("NA")) {

			productsWithPrice.put(InputData_Communities.commPBFAddProductName, dataTable.get("Product Price"));
			productsWithPrice.put("Internet Installation", InputData_Communities.commPBFInstallationFees);

			if(!InputData_Communities.commPBFBusinessSubProducts.get(0).equals("NA")) {
				for(int i = 0; i<InputData_Communities.commPBFBusinessSubProducts.size(); i++) {
					productsWithPrice.put(InputData_Communities.commPBFBusinessSubProducts.get(i), dataTable.get("Internet Features Price"));
				}
			}

			// Verify the prices for the office 365 product
			if(!InputData_Communities.commPBFOffAddOnName.equals("NA")) {
				productsWithPrice.put(InputData_Communities.commPBFOffAddOnName, InputData_Communities.commPBFOffAddOnPrice);
			}	
		}		
		//Verify prices for TV
		if(!InputData_Communities.commPBFAddTVProductName.equals("NA")) {	
			productsWithPrice.put(InputData_Communities.commPBFAddTVProductName, InputData_Communities.commPBFTVProductPrice);
			productsWithPrice.put("Installation", InputData_Communities.commPBFTVAddOnInstallationPrice);
			productsWithPrice.put(InputData_Communities.commPBFTVAddOnName, InputData_Communities.commPBFTVAddOnPrice);

		}
		downloadPDFAndVerifyProductPriceInPDF(productsWithPrice, fileName, Constants.TERMSANDCONDITIONS_INTERNET_IBLC);
		sf.seleU.switchToDefaultContent();
	}

	/**
	 * @throws IOException Added by Chethan K
	 * 
	 *                     1- Verify PDF is generated
	 * 
	 *                     2- Click on Download PDF button
	 * 
	 *                     3- Verify the microsoft office addons discounts and
	 *                     compare the prices in pdf
	 * 
	 */
	public void downloadPDFAndVerifyProductPriceInPDF(Hashtable<String, String> productsWithPrice, String fileName, String validateURL)
			throws Exception {

		// PDF reader variables and location of PDF file
		String pdfText;
		String[] allPrices;
		String actual_Price;
		String productName;
		String expected_ExcelPrice;
		String quantity;

		try {
			clickDownloadPDFButton();
			// Read pdf and extract the text from the PDF
			pdfText = PDFHelper.ExtractTextFromPDF(Constants.DOWNLOADS_LOCATION + fileName);

			// Validate text for PDFs
			//			if (fileName.contains(("Accepted"))) {
			PDFHelper.IsTextPresentInPDF(pdfText, "Agreement");
			//				PDFHelper.IsTextPresentInPDF(pdfText, "Accepted Date");
			//				//Validate the terms and conditions url in PDF
			//				PDFHelper.IsTextPresentInPDF(pdfText, validateURL);
			//			} else if (fileName.contains(("Proposal"))) {
			//				PDFHelper.IsTextPresentInPDF(pdfText, "Proposal");
			//				if (InputData.env.equals("ITDEVSTAGE"))
			//				PDFHelper.IsTextPresentInPDF(pdfText, "Created Date");
			//				else
			//					PDFHelper.IsTextPresentInPDF(pdfText, "Presented Date");
			//			}

			// Forloop to loop thru all products in the productsWithPrice & validate the prices
			for (String name: productsWithPrice.keySet())         //iteration over keys  
			{  
				//returns the value to which specified key is mapped  
				expected_ExcelPrice = productsWithPrice.get(name);  
				System.out.println("Product Name is "+name+" Price is "+expected_ExcelPrice);  
				productName = name;

				// Get prices array for the specific product from the UI
				allPrices = PDFHelper.GetPricesForProductFromPDF(productName, pdfText);
				if (allPrices != null) {
					actual_Price = (allPrices[allPrices.length - 1].replace("$", "")).replace("\r", "");

					// Get the quantity of the specific product from the excel
					if(InputData_Communities.commPBFOffAddOnName.equals(productName)) {
						quantity = String.valueOf(InputData_Communities.commPBFOffAddOnQty);
					} else if(InputData_Communities.commPBFTVAddOnName.equals(productName)) {
						quantity = String.valueOf(InputData_Communities.commPBFTVAddOnQty);
					} else {
						// for Internet and Tv product quantity will be 1 always 
						quantity = "1";
					}

					// verify price of the given product
					PDFHelper.ValidatePricesOnQuantity(productName, quantity, expected_ExcelPrice, actual_Price);
					reportStatusPass(
							methodName + ":  Verified the prices in PDF for the " + productName + " as :" + actual_Price,
							true, false);
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
	 * @throws IOException Added by Chethan K
	 * 
	 *                     1-Click on Download PDF button
	 * 
	 */
	public void clickDownloadPDFButton() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			//	sf.seleU.switchToFrame(sf.gdPdf.accessbilityTitleFrame.get(0));
			//	sf.seleU.switchToElementFrame(sf.gdPdf.downloadPDFButton);

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

	/**
	 * @throws IOException Added by Chethan K
	 * 
	 *                     1-Click on Download PDF button
	 * 
	 */
	public void clickDownloadPDFButton_FromMailinator() throws IOException {
		try {
			sf.seleU.wait(10000);
			sf.seleU.switchToDefaultContent();
			//			sf.seleU.switchToFrame(sf.gdPdf.accessbilityTitleFrame.get(0));
			//			sf.seleU.switchToElementFrame(sf.gdPdf.downloadPDFButton);

			// Verify Download PDF Button is displayed
			if (sf.seleU.isElementDisplayed(sf.mailinator.pdfDownloadButton)) {
				reportStatusPass(methodName + ":  Download PDF button is displayed", true, true);
			} else {
				reportStatusFail(methodName + ":  Error in Downloading Quote PDF", true);
			}

			// Delete Pre-Existing Downloaded files
			deleteExistingFilesInFolder(Constants.DOWNLOADS_LOCATION);

			// Click on Download Button
			sf.seleU.clickElementByJSE(sf.mailinator.pdfDownloadButton);
			reportStatusPass(methodName + ":  Clicked on Download PDF Button", true, false);
			sf.seleU.wait(7000);
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(methodName + ": Error in clicking on the Download Button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException Added by Chethan K
	 * 
	 *                     1- Verify the content in pdf
	 * @throws InterruptedException 
	 * 
	 */
	public void verifyContentFromPDF(String fileName, String validateURL) throws IOException, InterruptedException {
		String pdfText;
		if (fileName.contains(("Accepted"))) {
			// Read pdf and extract the text from the PDF
			try {
				pdfText = PDFHelper.ExtractTextFromPDF(Constants.DOWNLOADS_LOCATION + fileName);
				// Validate the terms and conditions url in PDF
				PDFHelper.IsTextPresentInPDF(pdfText, validateURL);
			} catch (IOException e) {
				reportStatusFail(methodName + ": Error in verifying PDF content", e);
				e.printStackTrace();
			}
		}
	}
}

package sfdc.pages.om;

import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;


/**
 * @author Pankaj Agarwal, Date: 04/16/2021
 *
 *         SFDC Order Decomposition Tab
 */
public class SFDC_OrderDecomposition_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public String evcID, vLANID, encapsulationType, dNSRequired, ipVersionBlock, ip4WANBlock, ip4LANBlock, ip6WANBlock, ip6LANBlock, accessProvider, 
	serviceProvider, clFI, demarcationLOC, contractTerm, accessCIRCID, productCode, handOffInterface, model, deviceID, customerPortID, technicalCircuitType, 
	technicalCLLCLFI, technicalDemarcationLOC, technicalHandOFF, technicalOracleID, technicalPHUB, technicalEquipmentModel, technicalPortConfig,
	technicalOrderType, technicalPortSpeed, technicalPortType, technicalServiceType, technicalProductSpeed, technicalvLanID, technicalDnsRequired,
	technicalEncapsulationType, technicalEvc, technicalEVCNotes, technicalIPV4LAN, technicalIPV4WAN, technicalIPV6LAN, technicalIPV6WAN, 
	ethernetAccessType, ethernetBandwidth, existingPotableIP, terminatingProductCode, fibreInternetProductCode;

	public SFDC_OrderDecomposition_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Order Decomposition tab
	 * 
	 *                    
	  
	 */
	public void clickDecompositionInOrder() throws IOException {
		try {
			String methodName = "SFDC_Order_Decompostion@: ";

			// Verify decomposotion tab is displayed 

			if (sf.seleU.isElementDisplayed(sf.orDecompose.orderDecompositionTab)){ 
				reportStatusPass(methodName +
						" Verified orchestartion plan number in Order Relted Tab" +   sf.seleU.getTextFromWebElement(sf.orDecompose.orderDecompositionTab), true, true);

				sf.seleU.clickElementByJSE(sf.orDecompose.orderDecompositionTab);

			} else {
				reportStatusFail(" Error in Displaying Decomposition Tab", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Decomposition Tab in Order", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Order Decomposition tab
	 * 
	 *                    
	 * 
	 */
	public void verifyDecompositionInOrder() throws IOException {
		try {
			String methodName = "SFDC_Order_Decompostion@: ";

			sf.seleU.wait(2000);
			// Verify decomposition Product Name is displayed 
			if(sf.orDecompose.orderDecompositionProductTag.size() > 0) {					
				for( int i=0; i<sf.orDecompose.orderDecompositionProductTag.size(); i++) {

					if (sf.seleU.isElementDisplayed(sf.orDecompose.orderDecompositionProductTag.get(i))){ 
						sf.seleU.ScrolltoElement(sf.orDecompose.orderDecompositionProductTag.get(i));
						reportStatusPass(methodName +
								" Verified Product Name in Order Decomposition Tab" +   
								sf.seleU.getTextFromWebElement(sf.orDecompose.orderDecompositionProductName.get(i)), true, true);

						//		 sf.seleU.clickElementByJSE(sf.orDecompose.orderDecompositionProductName.get(i));

					} 
				} 
			}else {
				reportStatusFail(" Error in Displaying Product in Decomposition Tab", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Decomposition Tab in Order", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Order Decomposition tab
	 * 
	 *                    
	 * 
	 */
	public void verifyDecompositionAttributeInOrder() throws IOException {
		try {
			String methodName = "SFDC_Order_Attribute_Decompostion@: ";

			// Verify decomposition Attribute is displayed 

			sf.seleU.wait(2000);
			if(sf.orDecompose.orderDecompositionAttributeButton.size() > 0) {					
				for( int i=0; i<sf.orDecompose.orderDecompositionAttributeButton.size(); i++) {

					if (sf.seleU.isElementDisplayed(sf.orDecompose.orderDecompositionAttributeButton.get(i))){ 
						sf.seleU.ScrolltoElement(sf.orDecompose.orderDecompositionAttributeButton.get(i));
						reportStatusPass(methodName +
								" Verified Attribute is present in Order Decomposition Tab" +  " for product " +
								sf.seleU.getTextFromWebElement(sf.orDecompose.orderDecompositionProductName.get(i)), true, true);
					} 
				} 
			}else {
				reportStatusFail(" Error in Displaying Attribute inDecomposition Tab", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Displaying Attribute inDecomposition Tab", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Order Decomposition tab
	 * 
	 *                    
	 * 
	 */
	public void extractAttributeValueInOrder(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Order_Attribute_Decompostion@: ";
			boolean isFoundSpeed = false; boolean isFound = false;
			// Extract promo code value
			sf.seleU.clickElementByJSE(sf.orDecompose.orderDecompositionTab);
			reportStatusPass(methodName +" CLicked on Order Decomposition Tab", true, true);
			sf.seleU.wait(4000);

//			if(sf.orDecompose.orderDecompositionAttributeButton.size() > 0) {					
//
//				if(!dataTable.get("Internet Product Name").equals("NA")) {
//					for( int j=0; j<sf.orDecompose.orderDecompositionAttributeButton.size(); j++) {
//
//						if (!sf.seleU.getTextFromWebElement(sf.orDecompose.orderInternetProductSpeed.get(j)).equals("")
//								&& sf.seleU.isElementDisplayed(sf.orDecompose.orderInternetProductSpeed.get(j))){
//
//							sf.seleU.ScrolltoElement(sf.orDecompose.orderDecompositionProductName.get(j));					
//							sf.dataInput.quoteInternetUploadSpeed = sf.seleU.getTextFromWebElement(sf.orDecompose.orderInternetProductSpeed.get(j)).split(":")[1].replaceAll(" ", "");
//
//							reportStatusPass(methodName +" Internet speed extracted value value is " + sf.dataInput.quoteInternetUploadSpeed, true, true);
//							isFoundSpeed = true;
//							break;
//						} 
//
//					}
//				}
//
//			}else {
//				reportStatusFail(" Error in extracting speed", true);
//			}
			
			// verify promo code
						try {
							// *******Fetch Promo code:
							if(sf.orDecompose.cFS_CAN_No.size() > 0) {
								System.out.println(sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PromoCode.get(0)));
							//	sf.dataInput.promo_Code = sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PromoCode.get(0)).split(":")[1].trim().substring(0, 12);
								sf.dataInput.promo_Code =sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PromoCode.get(0)).split(":")[1].replaceAll(" ", "");
								System.out.println(sf.dataInput.promo_Code );
							}	
							
						} catch (Throwable e) {
							reportStatusFail(" Error in Displaying Promo Code", e);
							e.printStackTrace();
						}

			//			if((isFound && isFoundSpeed) == true) {
			//				reportStatusPass(methodName +" Promo code extracted value value is " + sf.dataInput.quoteInternetUploadSpeed + 
			//						" Promo code extracted value value is " + sf.dataInput.promo_Code, true, true);
			//			} else {
			//				reportStatusFail(" Error in extracting prmocode value", true);
			//			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Displaying Attribute inDecomposition Tab", e);
			e.printStackTrace();
		}
	}

	/**PA PI05 - SP05
	 * @throws IOException
	 * 
	 *                     Verify work order attribute value in order decomposition tab
	 * 
	 *                    
	 * 
	 */
	public void extractWorkOrderAttributeValueInOrderDecomposition(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Order_Attribute_Decompostion@: ";
			boolean isFoundSpeed = false; boolean isFound = false;
			// Extract promo code value
			sf.seleU.clickElementByJSE(sf.orDecompose.orderDecompositionTab);
			reportStatusPass(methodName +" CLicked on Order Decomposition Tab", true, true);
			sf.seleU.wait(4000);

			//**** Verify CFS PSI work order appointment date :
			try {

				if(sf.orDecompose.cFS_PSI_Work_Order_Appointment_Date_Time.size() > 0) {

					//		System.out.println(sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PSI_Work_Order_Appointment_Date_Time.get(0)));
					String work_OrderCFS_PSI_Date_Time[] = sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PSI_Work_Order_Appointment_Date_Time.get(0)).split(" ");
					String work_OrderCFS_PSI_StartTime = work_OrderCFS_PSI_Date_Time[9].trim();
					String work_OrderCFS_PSI_EndTime = work_OrderCFS_PSI_Date_Time[11].substring(0, 7).trim();
					String work_OrderCFS_PSI_Date = work_OrderCFS_PSI_Date_Time[8].trim();

					sf.omData.enterAppointmentDate = DateTimeUtilities.currentSystemDate("yyyy-MM-dd");
					//	sf.omData.enterAppointmentDate = "2021-09-09";
					compareFieldValue("Pre site inspection work order Appointment Date", work_OrderCFS_PSI_Date, sf.omData.enterAppointmentDate);
				}
			} catch (Throwable e) {
				reportStatusFail(" Error in Displaying PSI work order appointment date", e);
				e.printStackTrace();
			}

			//****** Verify CFS Installation work order appointment date :
			try {
				if(sf.orDecompose.cFSInstallation_Work_Order_Appointment_Date_Time.size() > 0) {
					System.out.println(sf.seleU.getTextFromWebElement(sf.orDecompose.cFSInstallation_Work_Order_Appointment_Date_Time.get(0)));
					String work_OrderInstallationDate_Time[] = sf.seleU.getTextFromWebElement(sf.orDecompose.cFSInstallation_Work_Order_Appointment_Date_Time.get(0)).split(" ");
					String work_OrderInstallationStartTime = work_OrderInstallationDate_Time[9].trim();
					String work_OrderInstallationEndTime = work_OrderInstallationDate_Time[11].substring(0, 7).trim();
					String work_OrderInstallationDate = work_OrderInstallationDate_Time[8].trim();

					sf.omData.enterPostAppointmentDate = DateTimeUtilities. currentSystemDatePlus(7, "yyyy-MM-dd");
					//	sf.omData.enterAppointmentDate = "2021-09-09";
					compareFieldValue("CFS installation work order Appointment Date", work_OrderInstallationDate, sf.omData.enterPostAppointmentDate);
				}
			} catch (Throwable e) {
				reportStatusFail(" Error in Displaying installation  work order appointment date", e);
				e.printStackTrace();
			}

			try {

				// *******Verify Can NO:
				if(sf.orDecompose.cFS_CAN_No.size() > 0) {
					System.out.println(sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_CAN_No.get(0)));
					String cFS_PSI_CANNo = sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_CAN_No.get(0)).split(":")[1].trim().substring(0, 12);
					System.out.println(cFS_PSI_CANNo);
					compareFieldValue("CAN NO ", cFS_PSI_CANNo, sf.omData.canNOInCreateAccount);
				}	

			} catch (Throwable e) {
				reportStatusFail(" Error in Displaying CAN NO", e);
				e.printStackTrace();
			}

			try {
				//********Verify Work Order No:		
				if(sf.orDecompose.cFS_Installation_WorkOrderNo.size() > 0) {
					
					String cFS_PSI_WorkOrderNo = sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_PSI_WorkOrderNo.get(0)).split(":")[1].trim().substring(0, 7);
					System.out.println(cFS_PSI_WorkOrderNo);
					compareFieldValue("PSI Work Order No", cFS_PSI_WorkOrderNo, sf.omData.preSiteInspection_WorkOrderNo);

					System.out.println(sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_Installation_WorkOrderNo.get(0)));
					String cFS_Installation_WorkOrderNo = sf.seleU.getTextFromWebElement(sf.orDecompose.cFS_Installation_WorkOrderNo.get(0)).split(":")[1].trim().substring(0, 7);
					System.out.println(cFS_Installation_WorkOrderNo);
					compareFieldValue("Installtion Work Order No", cFS_Installation_WorkOrderNo, sf.omData.installation_WorkOrderNo);
				}

			} catch (Throwable e) {
				reportStatusFail(" Error in Displaying installation  work order", e);
				e.printStackTrace();
			}
			
		
		} catch (Throwable e) {
			reportStatusFail(" Error in Displaying Attribute value for work order inDecomposition Tab", e);
			e.printStackTrace();
		}
	}

	/**PA PI05 - SP05
	 * @throws IOException
	 * 
	 *                     Verify work order attribute value in order decomposition tab
	 * 
	 *                    
	 * 
	 */
	public void extractAndVerifyRDIAttributesInDecomposition(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Extract_AndVerifyRDIAttributesIn_Decomposition@: ";
			boolean isFoundSpeed = false; boolean isFound = false;

			// Extract promo code value
			sf.seleU.clickElementByJSE(sf.orDecompose.orderDecompositionTab);
			reportStatusPass(methodName +" CLicked on Order Decomposition Tab", true, true);
			sf.seleU.wait(4000);

			sf.seleU.ScrolltoElement(sf.orDecompose.evcID);					
			evcID = sf.seleU.getTextFromWebElement(sf.orDecompose.evcID).split(":")[1].replaceAll(" ", "");
			System.out.println(evcID);
			compareFieldValue("EVC ID ", evcID, sf.dataInput.evcID);

			encapsulationType = sf.seleU.getTextFromWebElement(sf.orDecompose.enacpsulationType).split(":")[1].replaceAll(" ", "");
			System.out.println(encapsulationType);
			compareFieldValue("ENcapsulation  Type", encapsulationType, sf.omData.encapsulationType);

			vLANID = sf.seleU.getTextFromWebElement(sf.orDecompose.vLANID).split(":")[1].replaceAll(" ", "");
			System.out.println(vLANID);
			compareFieldValue("VLAN ID", vLANID, sf.omData.vLANID);

			dNSRequired = sf.seleU.getTextFromWebElement(sf.orDecompose.dNSRequired).split(":")[1].replaceAll(" ", "");
			System.out.println(dNSRequired);
			compareFieldValue("dns Required ", dNSRequired, sf.omData.dnsRequired);

			ipVersionBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ipVersionBlock).split(":")[1].replaceAll(" ", "");
			System.out.println(ipVersionBlock);
			compareFieldValue("ipVersionBlock", ipVersionBlock, sf.omData.iPversion);

			if(dataTable.get("iPversion").trim().equals("IPv4")){

				ip4WANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip4WANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip4WANBlock);
				compareFieldValue("ip4WANBlock", ip4WANBlock, sf.omData.ipV4WAnBlockSpecSheet);

				ip4LANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip4LANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip4LANBlock);
				compareFieldValue("ip4LANBlock", ip4LANBlock, sf.omData.ipV4LAnBlockSpecSheet);

			} else if (dataTable.get("iPversion").equals("IPv6")) {

				ip6LANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip6LANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip6LANBlock);
				compareFieldValue("ip6LANBlock", ip6LANBlock, sf.omData.ipV6LAnBlockSpecSheet);

				ip6WANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip6WANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip6WANBlock);
				compareFieldValue("ip6WANBlock", ip6WANBlock, sf.omData.ipV6WAnBlockSpecSheet);

			}	else {

				ip4WANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip4WANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip4WANBlock);
				compareFieldValue("ip4WANBlock", ip4WANBlock, sf.omData.ipV4WAnBlockSpecSheet);

				ip4LANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip4LANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip4LANBlock);
				compareFieldValue("ip4LANBlock", ip4LANBlock, sf.omData.ipV4LAnBlockSpecSheet);

				ip6LANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip6LANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip6LANBlock);
				compareFieldValue("ip6LANBlock", ip6LANBlock, sf.omData.ipV6LAnBlockSpecSheet);

				ip6WANBlock = sf.seleU.getTextFromWebElement(sf.orDecompose.ip6WANBlock).split(":")[1].replaceAll(" ", "");
				System.out.println(ip6WANBlock);
				compareFieldValue("ip6WANBlock", ip6WANBlock, sf.omData.ipV6WAnBlockSpecSheet);
			}
			
			if(!sf.omData.existingPortableIP.trim().equals("NA")){

				existingPotableIP = sf.seleU.getTextFromWebElement(sf.orDecompose.existingPortableIP).split(":")[1].replaceAll("[^a-zA-Z0-9]", "");
				System.out.println(existingPotableIP);
				compareFieldValue("existingPotableIP", existingPotableIP, sf.omData.existingPortableIP.replaceAll("[^a-zA-Z0-9]", ""));
			}

			accessProvider = sf.seleU.getTextFromWebElement(sf.orDecompose.accessProvider).split(":")[1].replaceAll(" ", "");
			System.out.println(accessProvider);
			compareFieldValue("accessProvider", accessProvider, sf.omData.accessProvider);

			serviceProvider = sf.seleU.getTextFromWebElement(sf.orDecompose.serviceProvider).split(":")[1].replaceAll(" ", "");
			System.out.println(serviceProvider);
			compareFieldValue("serviceProvider", serviceProvider, sf.omData.accessProvider);

			clFI = sf.seleU.getTextFromWebElement(sf.orDecompose.clFI).split(":")[1].replaceAll(" ", "");
			System.out.println(clFI);
			compareFieldValue("clFI", clFI, sf.dataInput.cLFIValue);

			demarcationLOC = sf.seleU.getTextFromWebElement(sf.orDecompose.demarcationLOC).split(":")[1].replaceAll(" ", "");
			System.out.println(demarcationLOC);
			compareFieldValue("demarcationLOC", demarcationLOC, sf.omData.demarcationLOC);

			contractTerm = sf.seleU.getTextFromWebElement(sf.orDecompose.contractTerm.get(0)).split(":")[1].replaceAll(" ", "");
			System.out.println(contractTerm);
			compareFieldValue("contractTerms", contractTerm, sf.omData.contractTerms);

			accessCIRCID = sf.seleU.getTextFromWebElement(sf.orDecompose.accessCIRCID).split(":")[1].replaceAll(" ", "");
			System.out.println(accessCIRCID);
			compareFieldValue("accessCIRCID", accessCIRCID, sf.dataInput.oracleNumber);

			productCode = sf.seleU.getTextFromWebElement(sf.orDecompose.productCode).split(":")[1].replaceAll(" ", "");
			System.out.println(productCode);
			verifyStringPresent(productCode);

			handOffInterface = sf.seleU.getTextFromWebElement(sf.orDecompose.handOffInterface).split(":")[1].replaceAll(" ", "");
			System.out.println(handOffInterface);
			compareFieldValue("handOffInterface", handOffInterface, sf.omData.handOffInterface);

			ethernetBandwidth = sf.seleU.getTextFromWebElement(sf.orDecompose.ethernetBandwidth).split(":")[1].replaceAll(" ", "");
			System.out.println(ethernetBandwidth);
			verifyStringPresent(ethernetBandwidth);

			ethernetAccessType = sf.seleU.getTextFromWebElement(sf.orDecompose.ethernetAccessType).split(":")[1].replaceAll(" ", "");
			System.out.println(ethernetAccessType);
			verifyStringPresent(ethernetAccessType);

			handOffInterface = sf.seleU.getTextFromWebElement(sf.orDecompose.handOffInterface).split(":")[1].replaceAll(" ", "");
			System.out.println(handOffInterface);
			compareFieldValue("handOffInterface", handOffInterface, sf.omData.handOffInterface);

			model = sf.seleU.getTextFromWebElement(sf.orDecompose.model).split(":")[1].replaceAll(" ", "");
			System.out.println(model);
			compareFieldValue("model", model, "Nokia 7210 SAS-D");

			deviceID = sf.seleU.getTextFromWebElement(sf.orDecompose.deviceID).split(":")[1].replaceAll(" ", "");
			System.out.println(deviceID);
			compareFieldValue("deviceID", deviceID, sf.dataInput.deviceID);

			customerPortID = sf.seleU.getTextFromWebElement(sf.orDecompose.customerPortID).split(":")[1].replaceAll(" ", "");
			System.out.println(customerPortID);
			compareFieldValue("customerPortID", customerPortID, sf.dataInput.customerPortNo);
			
			terminatingProductCode = sf.seleU.getTextFromWebElement(sf.orDecompose.terminatingProductCode.get(3)).split(":")[1].replaceAll(" ", "");
			System.out.println(terminatingProductCode);
			verifyStringPresent(terminatingProductCode);

			// Verify Technical Attribute
			technicalCircuitType = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalCircuitType).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalCircuitType);
			compareFieldValue("technicalCircuitType", technicalCircuitType, "GigE");
			
			fibreInternetProductCode = sf.seleU.getTextFromWebElement(sf.orDecompose.fibreInternetProductCode).split(":")[1].replaceAll(" ", "");
			System.out.println(fibreInternetProductCode);
			verifyStringPresent(fibreInternetProductCode);

			technicalCLLCLFI = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalCLLCLFI).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalCLLCLFI);
			compareFieldValue("technicalCLLCLFI", technicalCLLCLFI, sf.dataInput.cLFIValue);

			technicalDemarcationLOC = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalDemarcationLOC).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalDemarcationLOC);
			compareFieldValue("technicalDemarcationLOC", technicalDemarcationLOC, sf.omData.demarcationLOC);

			technicalHandOFF = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalHandOFF).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalHandOFF);
			compareFieldValue("technicalHandOFF", technicalHandOFF, sf.omData.handOffInterface);

			sf.seleU.ScrolltoElement(sf.orDecompose.technicalPHUB);
			technicalOracleID = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalOracleID).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalOracleID);
			compareFieldValue("technicalOracleID", technicalOracleID, sf.dataInput.oracleNumber);

			technicalPHUB = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalPHUB).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalPHUB);
			compareFieldValue("technicalOracleID", technicalPHUB, sf.omData.pHUBValue);

			technicalEquipmentModel = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalEquipmentModel).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalEquipmentModel);
			compareFieldValue("technicalEquipmentModel", technicalEquipmentModel, "Nokia 7210 SAS-D");

			technicalPortConfig = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalPortConfig).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalPortConfig);
			compareFieldValue("technicalPortConfig", technicalPortConfig, sf.dataInput.portConfigNo);

			technicalOrderType = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalOrderType).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalOrderType);
			if(technicalOrderType.trim().equals("Standard on-net".trim().replaceAll(" ", "")))
				compareFieldValue("technicalOrderType", technicalOrderType, "Standard on-net");
			else
				compareFieldValue("technicalOrderType", technicalOrderType, "Standard near-net");

			sf.seleU.ScrolltoElement(sf.orDecompose.technicalPortSpeed);
			technicalPortSpeed = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalPortSpeed).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalPortSpeed);
			compareFieldValue("technicalPortSpeed", technicalPortSpeed, "Auto");

			technicalPortType = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalPortType).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalPortType);
			compareFieldValue("technicalPortType", technicalPortType, "Dedicated");

			technicalServiceType = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalServiceType).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalServiceType);
			compareFieldValue("technicalServiceType", technicalServiceType, sf.omData.dedicatedInternetProduct);

			technicalProductSpeed = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalProductSpeed).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalProductSpeed);
			System.out.println(sf.dataInput.quoteInternetUploadSpeed);
			compareFieldValue("technicalProductSpeed", technicalProductSpeed, sf.dataInput.quoteInternetUploadSpeed);

			technicalvLanID = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalvLanID).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalvLanID);
			compareFieldValue("technicalvLanID", technicalvLanID, sf.omData.vLANID);

			technicalDnsRequired = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalDnsRequired).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalDnsRequired);
			compareFieldValue("technicalDnsRequired", technicalDnsRequired, sf.omData.dnsRequired);

			sf.seleU.ScrolltoElement(sf.orDecompose.technicalEncapsulationType);
			technicalEncapsulationType = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalEncapsulationType).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalEncapsulationType);
			compareFieldValue("technicalEncapsulationType", technicalEncapsulationType, sf.omData.encapsulationType);

			technicalEvc = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalEvc).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalEvc);
			compareFieldValue("technicalEvc", technicalEvc, sf.omData.evcID);

			technicalEVCNotes = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalEVCNotes).split(":")[1].replaceAll(" ", "");
			System.out.println(technicalEVCNotes);
			compareFieldValue("technicalEVCNotes", technicalEVCNotes, sf.dataInput.opportunityAdditionalInfo);

			if(dataTable.get("iPversion").trim().equals("IPv4")){

				technicalIPV4LAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV4LAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4LAN);
				compareFieldValue("technicalIPV4LAN", technicalIPV4LAN, sf.omData.ipV4LAnBlockSpecSheet);

				technicalIPV4WAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV4WAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4WAN);
				compareFieldValue("technicalIPV4WAN", technicalIPV4WAN, sf.omData.ipV4WAnBlockSpecSheet);

			} else if (dataTable.get("iPversion").equals("IPv6")) {

				technicalIPV6LAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV6LAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV6LAN);
				compareFieldValue("technicalIPV6LAN", technicalIPV6LAN, sf.omData.ipV6LAnBlockSpecSheet);

				technicalIPV6WAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV6WAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4WAN);
				compareFieldValue("technicalIPV4WAN", technicalIPV4WAN, sf.omData.ipV6WAnBlockSpecSheet);

			}	else {

				technicalIPV4LAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV4LAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4LAN);
				compareFieldValue("technicalIPV4LAN", technicalIPV4LAN, sf.omData.ipV4LAnBlockSpecSheet);

				technicalIPV4WAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV4WAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4WAN);
				compareFieldValue("technicalIPV4WAN", technicalIPV4WAN, sf.omData.ipV4WAnBlockSpecSheet);

				technicalIPV6LAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV6LAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV6LAN);
				compareFieldValue("technicalIPV6LAN", technicalIPV6LAN, sf.omData.ipV6LAnBlockSpecSheet);

				technicalIPV6WAN = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalIPV6WAN).split(":")[1].replaceAll(" ", "");
				System.out.println(technicalIPV4WAN);
				compareFieldValue("technicalIPV6WAN", technicalIPV6WAN, sf.omData.ipV6WAnBlockSpecSheet);
			}
			
			if(!sf.omData.existingPortableIP.trim().equals("NA")){

				existingPotableIP = sf.seleU.getTextFromWebElement(sf.orDecompose.technicalPortableIpList).split(":")[1].replaceAll("[^a-zA-Z0-9]", "");
				System.out.println(existingPotableIP);
				compareFieldValue("existingPotableIP", existingPotableIP, sf.omData.existingPortableIP.replaceAll("[^a-zA-Z0-9]", ""));
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Displaying Attribute value for RDI inDecomposition Tab", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().equals(expectedText)) {
				reportStatusPass(" Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is " + element.getText()
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(fieldName + " is present" + " with value as "
						+ sf.seleU.getTextFromWebElementWithYellowHighlight(element), true, false);
			} else {
				reportStatusFail(fieldName + " is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyStringPresent(String fieldName) throws IOException {

		try {

			// Verify Field is present
			if (!fieldName.isEmpty()) {
				reportStatusPass(fieldName + " is present" + " with value as "
						+ fieldName, true, false);
			} else {
				reportStatusFail(fieldName + " is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void compareFieldValue(String fieldName, String screenText, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (screenText.replaceAll(" ", "").trim().equals(expectedText.replaceAll(" ", ""))) {
				reportStatusPass(" Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is " + screenText
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}






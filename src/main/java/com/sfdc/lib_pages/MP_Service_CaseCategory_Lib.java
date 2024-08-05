package com.sfdc.lib_pages;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;

/**
 * @author Priyanka.Acharya
 * 
 *         Library used for Case categories dropdown validations
 *
 */
public class MP_Service_CaseCategory_Lib {

	/**
	 * @param dataTable
	 * @throws FileNotFoundException
	 * 
	 *                               Idenity Each Case Record
	 */
	public static void identifyCase(Hashtable<String, String> dataTable) throws FileNotFoundException {

		InputData.caseIdentifier = dataTable.get("Sl No") + "_"
				+ AdditionalUtilities.formatString(dataTable.get("Product")) + "_"
				+ AdditionalUtilities.formatString(dataTable.get("Category")) + "_"
				+ AdditionalUtilities.formatString(dataTable.get("Type")) + "_"
				+ AdditionalUtilities.formatString(dataTable.get("Sub-Type"));
		ScreenDocs.createScreenDoc(InputData.caseIdentifier);

	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   Create Product map , category map and type map
	 */
	public static void prepareDateToVerifyAllOptions(Hashtable<String, String> dataTable) throws Exception {

		// Create Product map
		if (!InputData.prodct_map.containsKey(dataTable.get("Product"))) {
			InputData.category_list = new ArrayList<String>();

			InputData.category_list.add(dataTable.get("Category"));
			InputData.prodct_map.put(dataTable.get("Product"), InputData.category_list);

		} else {
			if (!InputData.prodct_map.get(dataTable.get("Product")).contains(dataTable.get("Category"))) {
				InputData.category_list = InputData.prodct_map.get(dataTable.get("Product"));
				InputData.category_list.add(dataTable.get("Category"));
				InputData.prodct_map.put(dataTable.get("Product"), InputData.category_list);

			}
		}

		// Create category map
		if (!InputData.category_map.containsKey(dataTable.get("Category"))) {
			InputData.type_list = new ArrayList<String>();

			InputData.type_list.add(dataTable.get("Type"));
			InputData.category_map.put(dataTable.get("Category"), InputData.type_list);

		} else {
			if (!InputData.category_map.get(dataTable.get("Category")).contains(dataTable.get("Type"))) {
				InputData.type_list = InputData.category_map.get(dataTable.get("Category"));
				InputData.type_list.add(dataTable.get("Type"));
				InputData.category_map.put(dataTable.get("Category"), InputData.type_list);

			}
		}

		// Creeate type map
		if (!InputData.type_map.containsKey(dataTable.get("Type"))) {
			InputData.subType_list = new ArrayList<String>();

			InputData.subType_list.add(dataTable.get("Sub-Type"));
			InputData.type_map.put(dataTable.get("Type"), InputData.subType_list);

		} else {
			if (!InputData.type_map.get(dataTable.get("Type")).contains(dataTable.get("Sub-Type"))) {
				InputData.subType_list = InputData.type_map.get(dataTable.get("Type"));
				InputData.subType_list.add(dataTable.get("Sub-Type"));
				InputData.type_map.put(dataTable.get("Type"), InputData.subType_list);

			}
		}
	}

}

package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;

public class FetchTestData extends Base {

	public static HashMap<String, String> hmConfigData;
	public static HashMap<String, String> mpTestData;

	static Properties configProp = new Properties();

	// It will get the value from the property file
	public static String getProperty(String key) {
		if (hmConfigData == null || hmConfigData.size() == 0) {
			hmConfigData = getAllPropertyData();
		}
		return hmConfigData.get(key);
	}

	public static String getMPTestData(String key) {
		if (mpTestData == null || mpTestData.size() == 0) {
			mpTestData = getMPExcelData();
		}
		return mpTestData.get(key);
	}

	public static String cleanseDirPaths(String key) {
		String rawDir = mpTestData.get(key).trim();
		String cleansedDir = null;

		if (rawDir != null && rawDir.length() > 0 && rawDir.charAt(rawDir.length() - 1) == '/') {

			cleansedDir = rawDir.substring(0, rawDir.length() - 1);

		} else {

			cleansedDir = rawDir;

		}

		return cleansedDir;
	}

	// Load All the Data from property to HashMap
	public static HashMap<String, String> getAllPropertyData() {
		try {

			configProp.load(new FileInputStream(new File(Constants.RESOURCES_DIR + "\\dataFiles\\data.properties")));

			try {
				configProp.load(new FileInputStream(new File(Constants.USER_DIR + "\\config.properties")));
			} catch (FileNotFoundException e) {
				// Temp Work Around for jenkins Execution
				configProp.load(new FileInputStream(new File(Constants.JENKINS_DIR_TEMP)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hmConfigData = new HashMap<String, String>();
		for (String key : configProp.stringPropertyNames()) {
			String value = configProp.getProperty(key);
			hmConfigData.put(key, value);

		}
		return hmConfigData;
	}

	// Load All the flow data from excel to HashMap
	public static HashMap<String, String> getMPExcelData() {
		GetExcelData ged = new GetExcelData();
		
		String language=configProp.getProperty("language");
		if(language==null || language.equals("") || language.equalsIgnoreCase("English")) {
			mpTestData = ged.getCompleteFlowData(Constants.MP_TESTDATA_FILE, Constants.MP_EnglishTestDataSheetName,Global.classname);
		}
		else if(language.equalsIgnoreCase("French")){
			mpTestData = ged.getCompleteFlowData(Constants.MP_TESTDATA_FILE,Constants.MP_FrenchTestDataSheetName, Global.classname);
		}
		return mpTestData;
	}

	public static void loadAllVariablesAndConstants() {
		hmConfigData = getAllPropertyData();
		mpTestData = getMPExcelData();
	}

}

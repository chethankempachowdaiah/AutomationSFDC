package com.sfdc.data;

import java.util.Hashtable;

public class DataTable {

	// Internet
	public String product_Type;

	// TV
	public String tv_AddOn;

	public DataTable(Hashtable<String, String> dataTable) {
		setInternetData(dataTable);
		setTVData(dataTable);
	}

	public void setInternetData(Hashtable<String, String> dataTable) {
		product_Type = dataTable.get("Product _Type");
	}

	public void setTVData(Hashtable<String, String> dataTable) {
		tv_AddOn = dataTable.get("Rogers TV AddOn Product");
	}

}

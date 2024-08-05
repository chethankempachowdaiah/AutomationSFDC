package com.sfdc.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class InputData_PartnerCommunities extends InputData {
	
	// Partner Communities
	public static String partnerCommunities_url = getProperty(env + "_" + "url_partnerComm");
	public static String partnerCommunities_dealer_userid = getProperty(env + "_" + "userid_partnerComm_Dealer");
	public static String partnerCommunities_pwd_dealer = getProperty(env + "_" + "password_encoded_partnerComm_dealer");
	public static String partnerCommunities_var_userid =  getProperty(env + "_" + "userid_partnerComm_Var");
	public static String partnerCommunities_pwd_var = getProperty(env + "_" + "password_encoded_partnerComm_var");
	
	// **************//////

	public InputData_PartnerCommunities() {
		
	}
}

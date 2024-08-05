package com.sfdc.lib_pages;

public class SFDC_AllPages_Threadlocal {

    
    protected static final ThreadLocal<SFDC_Login_Page> login = new ThreadLocal<>();
    
    
    
   
	protected static SFDC_Login_Page getLoginPageThreadLocal() {
		return login.get();
	}
	
	public SFDC_AllPages_Threadlocal() {
	    login.set(new SFDC_Login_Page());
		  
	}
}

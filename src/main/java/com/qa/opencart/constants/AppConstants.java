package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";

	public static final int SHORT_DEFAULT_WAIT = 5;
	public static final int MEDIUM_DEFAULT_WAIT = 10;
	public static final int LONG_DEFAULT_WAIT = 20;
	
	public static final int POLLING_DEFAULT_WAIT = 2;
	public static final int ACCOUNTS_PAGE_HEADER_COUNT = 4;
	
	
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = 
				Arrays.asList("My Account", "My Orders", "My Affiliate Account","Newsletter");
	
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_DATA_SHEET_NAME = "register";
	public static final String PRODUCT_DATA_SHEET_NAME = "product";
	public static final String PRODUCT_INFO_DATA_SHEET_NAME = "productinfo";
	public static final String USER_LOGIN_UNSUCCESS_MESSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final String LOGIN_DATA_SHEET_NAME = "login";
	public static final String USER_SEARCH_UNSUCCESS_MESSG = "Products meeting the search criteria";
}

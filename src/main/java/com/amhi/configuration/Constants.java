package com.amhi.configuration;

import java.util.ResourceBundle;

/**
 * 
 * @author shahzad
 *
 */
public class Constants {

	public static final String RESOURCE_BUNDLE_NAME = "db";

	static {
		@SuppressWarnings("unused")
		ResourceBundle res = ResourceBundle
				.getBundle(Constants.RESOURCE_BUNDLE_NAME);
	}

}

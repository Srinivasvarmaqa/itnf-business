package com.itt.browser.common;

import java.util.HashMap;

import com.itt.factoryhelper.BrowserHelperFactory.ALERT;

public class BrowserLocator {
	
	public static class KEYS {
		public static final String SEARCH_BY = "searchBy";
		public static final String SEARCH_VALUE = "searchValue";
		public static final String TIMEOUT = "timeout";
		public static final String VALUE = "value";
		public static final String CLEAR = "clear";
		public static final String TYPE = "type";
		public static final String NAME_OR_HANDLE = "nameOrHandle";
		public static final String ATTRIBUTE = "attribute";
		public static final String WAIT_FOR_ELEMENT = "waitForElement";
		public static final String ALERT_ACTION = "alertAction";
		public static final String ALERT_INPUT = "alertInput";
		public static final String VISIBILITY = "visibility";
		public static final String SWITCH_TO_MAIN_WINDOW = "switchToMainWindow";
		public static final String FRAMES = "frames";
	}

	public static class VALUES {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String XPATH = "xpath";
		public static final String CLASS = "class";
		public static final String CSS_SELECTOR = "cssSelector";
		public static final int DEFAULT_TIMEOUT = getDefaultTimeout();
		public static final String WINDOW = "window";
		public static final String FRAME = "frame";
		public static final String ALERT = "alert";
	}
	
	public static Integer getDefaultTimeout() {
		int defaultTimeout;
		if (System.getProperty("DEFAULT_TIMEOUT") != null) {
			defaultTimeout = Integer.parseInt(System.getProperty("DEFAULT_TIMEOUT"));
		} else {
			defaultTimeout = 15;
		}
		return defaultTimeout;
	}

	/**
	 * Prepares the params to use the xpath identifier
	 * @param value of xpath
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String,String> byXpath(final String xpath) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.SEARCH_BY, VALUES.XPATH);
		params.put(KEYS.SEARCH_VALUE, xpath);
		params.put(KEYS.TIMEOUT, Integer.toString(VALUES.DEFAULT_TIMEOUT));
		return params;
	}

	/**
	 * Prepares the params to use the id identifier
	 * @param value of id
	 * @return HashMap<String, String> to be used for Driver APIs
	 */
	
	public static HashMap<String,String> byId(final String id) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.SEARCH_BY, VALUES.ID);
		params.put(KEYS.SEARCH_VALUE, id);
		params.put(KEYS.TIMEOUT, Integer.toString(VALUES.DEFAULT_TIMEOUT));
		return params;
	}

	/**
	 * Prepares the params to use the name identifier
	 * @param value of name
	 * @return HashMap<String, String> to be used for Driver APIs
	 */
	
	public static HashMap<String,String> byName(final String name) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.SEARCH_BY, VALUES.NAME);
		params.put(KEYS.SEARCH_VALUE, name);
		params.put(KEYS.TIMEOUT, Integer.toString(VALUES.DEFAULT_TIMEOUT));
		return params;
	}

	/**
	 * Prepares the params to use the name identifier
	 * @param value of name
	 * @return HashMap<String, String> to be used for Driver APIs
	 */
	
	public static HashMap<String,String> byClass(final String className) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.SEARCH_BY, VALUES.CLASS);
		params.put(KEYS.SEARCH_VALUE, className);
		params.put(KEYS.TIMEOUT, Integer.toString(VALUES.DEFAULT_TIMEOUT));
		return params;
	}
	/**
	 * Prepares the params to use the cssSelector identifier
	 * @param value of cssSelector
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String,String> byCssSelector(final String cssSelector) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.SEARCH_BY, VALUES.CSS_SELECTOR);
		params.put(KEYS.SEARCH_VALUE, cssSelector);
		params.put(KEYS.TIMEOUT, Integer.toString(VALUES.DEFAULT_TIMEOUT));
		return params;
	}

	/** 
	 * Prepares to send the text
	 * @param byProperty HashMap <String, String>
	 * @param text to be sent
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withText(final HashMap<String, String> byProperty, final String text) {
		byProperty.put(KEYS.VALUE, text);
		return byProperty;
	}

	/**
	 * Prepares the params custom timeout option for longer and lesser wait
	 * @param byProperty HashMap <String, String>
	 * @param timeout is the custom timeout
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withCustomTimeout(final HashMap<String, String> byProperty, final int timeout) {
		byProperty.put(KEYS.TIMEOUT, Integer.toString(timeout));
		return byProperty;
	}

	/**
	 * Prepares the params custom timeout option for longer and lesser wait
	 * @param byProperty HashMap <String, String>
	 * @param timeout is the custom timeout
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withWaitForElement(final HashMap<String, String> byProperty, boolean waitForElement) {
		byProperty.put(KEYS.WAIT_FOR_ELEMENT, String.valueOf(waitForElement));
		return byProperty;
	}

	/**
	 * Adds the key-values for the waitForVisibility API of the Driver
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param visibility true or false based on whether the wait is for element to appear or disappear respectively
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withWaitForVisibility(HashMap<String, String> byProperty, String visibility) {
		byProperty.put(KEYS.VISIBILITY, visibility);
		return byProperty;
	}

	/**
	 * Adds the custom timeout in property map
	 * @param byProperty HashMap<String, String> params that has already been prepared by any of the "by" methods
	 * @param customTimeout string custom timeout value
	 * @return HashMap<String, String> to be used as params for Driver APIs
	 */
	public static HashMap<String, String> withTimeout(HashMap<String, String> byProperty, String customTimeout) {
		byProperty.put(KEYS.TIMEOUT, customTimeout);
		return byProperty;
	}

	/**
	 * Prepares the params clear text from input field before send value
	 * @param byProperty HashMap <String, String>
	 * @param timeout is the custom timeout
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withClearOption(final HashMap<String, String> byProperty, boolean clear) {
		byProperty.put(KEYS.CLEAR, String.valueOf(clear));
		return byProperty;
	}

	/**
	 * @param nameOrHandle
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> byWindow(String nameOrHandle) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.TYPE, VALUES.WINDOW);
		params.put(KEYS.NAME_OR_HANDLE, nameOrHandle);
		return params;
	}

	/**
	 * @param nameOrHandle
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> byFrame(String nameOrHandle) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.TYPE, VALUES.FRAME);
		params.put(KEYS.NAME_OR_HANDLE, nameOrHandle);
		return params;
	}

	/**
	 * Prepares the params clear text from input field before send value
	 * @param byProperty HashMap <String, String>
	 * @param timeout is the custom timeout
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withSwitchToMainWindow(final HashMap<String, String> byProperty, boolean switchToMainWindow) {
		byProperty.put(KEYS.SWITCH_TO_MAIN_WINDOW, String.valueOf(switchToMainWindow));
		return byProperty;
	}

	/**
	 * @param byProperty HashMap <String, String>
	 * @param attributeName
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withAttributeName(final HashMap<String, String> byProperty, String attributeName) {
		byProperty.put(KEYS.ATTRIBUTE, attributeName);
		return byProperty;
	}

	/**
	 * @param byProperty HashMap <String, String>
	 * @param dropdown select by method, index or value or visibleText
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> selectDropDownValue(final HashMap<String, String> byProperty, String selectByMethod, String value) {
		byProperty.put(KEYS.TYPE, selectByMethod);
		byProperty.put(KEYS.VALUE, value);
		return byProperty;
	}

	/**
	 * @param byProperty HashMap <String, String>
	 * @param alert Action like Accept, Dismiss, getAlertText
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withAlertAction(String alertAction) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.TYPE, VALUES.ALERT);
		params.put(KEYS.ALERT_ACTION, alertAction);
		return params;
	}

	/**
	 * @param byProperty HashMap <String, String>
	 * @param alert Action with input text
	 * @return HashMap<String, String> to be used for Driver APIs
	 */

	public static HashMap<String, String> withAlertInput(String alertInput) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(KEYS.TYPE, VALUES.ALERT);
		params.put(KEYS.ALERT_ACTION, ALERT.ALERT_INPUT.toString());
		params.put(KEYS.ALERT_INPUT, alertInput);
		return params;
	}
}

package com.framework.softAssertion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.framework.util.Screenshot;

public class SoftAsserter {

	private static Map<ITestResult,List<String>> verificationFailuresMap = new HashMap<ITestResult,List<String>>();
	private static String className = null;
	private static String methodName = null;
	
	public SoftAsserter(){
		StackTraceElement[] element = new Throwable().getStackTrace();
		className = element[1].getClassName();
		methodName = element[1].getMethodName();
	}
	
	/**
	 * This function is used to assert for the boolean condition true and gives an error massage if not true
	 * @param condition
	 * @param errMsg
	 * @param driver
	 */
	public static void assertTrue(boolean condition, String errMsg, WebDriver driver) {
    	try {
    		Assert.assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
    	}
    }
	
	/**
	 * This function is used to assert for the boolean condition false and gives error message if not false
	 * @param condition
	 * @param errMsg
	 * @param driver
	 */
	public static void assertFalse(boolean condition, String errMsg, WebDriver driver)  {
    	try{
    		Assert.assertFalse(condition);
    	}catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
    	}
    }
 
	/**
	 * This function is used to assert the two values are equal and gives the error message if not equals
	 * @param actual
	 * @param expected
	 * @param errMsg
	 * @param driver
	 */
    public static void assertEquals(Object actual, Object expected, String errMsg, WebDriver driver)  {
    	try {
    		Assert.assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
		}
    }
    
    /**
     * This function is used to assert the two values are equal
     * @param actual
     * @param expected
     * @param driver
     */
    public static void assertEquals(Object actual, Object expected, WebDriver driver)  {
    	try {
    		Assert.assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
		}
    }
    
    /**
	 * This function is used to assert for the boolean condition true
	 * @param condition
	 * @param errMsg
	 * @param driver
	 */
    public static void assertTrue(boolean condition, WebDriver driver) {
    	try {
    		Assert.assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
    	}
    }
    
    /**
	 * This function is used to assert for the boolean condition false
	 * @param condition
	 * @param errMsg
	 * @param driver
	 */
    public static void assertFalse(boolean condition, WebDriver driver)  {
    	try {
    		Assert.assertFalse(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
    	}
    }
    
    /**
     * This function is used to assert the object should not be null
     * @param actual
     * @param driver
     */
    public static void assertNotNull(Object actual, WebDriver driver)  {
    	try {
    		Assert.assertNotNull(actual);
		} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
		}
    }
 
    /**
     * This function is used to assert the object should not be null and prints the error message if not null
     * @param actual
     * @param errMsg
     * @param driver
     */
    public static void assertNotNull(Object actual, String errMsg, WebDriver driver)  {
    	try {
    		Assert.assertNotNull(actual, errMsg);
		} catch(Throwable e) {
    		addVerificationFailure(errMsg+" Exception msg: "+e.getMessage());
    		new Screenshot().takesScreenshot(driver, className, methodName);
		}
    }
 
    /**
     * This function is used to get all the assert failures
     * @return List<String>
     */
	public static List<String> getVerificationFailures() {
		List<String> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<String>() : verificationFailures;
	}
	
	/**
	 * This function is used to add all the assert failures
	 * @param e
	 */
	private static void addVerificationFailure(String e) {
		List<String> verificationFailures = getVerificationFailures();
		verificationFailures.add(e);
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
	}
}

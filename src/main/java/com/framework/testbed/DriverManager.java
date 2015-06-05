package com.framework.testbed;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.constant.FrameworkConstants;
import com.framework.factory.PageFactoryDesign;

public class DriverManager extends DriverConnector{

	public ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
	public ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();

	/**
	 * This function is used to set the WebDriver on local/remote/sauce
	 * @param config
	 */
	public void setWebDriverSession(Map<String, String> config){
		ExecutionMode executionType = ExecutionMode.valueOf(config.get(FrameworkConstants.HUB));
		PageFactoryDesign.getPageFactoryDesign();
		switch (executionType) {
		case Local:
			localDriver.set(super.getWebDriver(config.get("browser")));
			break;
			
		case Remote:
			remoteDriver.set(super.getRemoteWebDriver(config));
			break;
			
		case Sauce:
			remoteDriver.set(super.getSauceRemoteWebDriver(config));
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * This function is used to get the WebDriver instance
	 * @param config
	 * @return WebDriver
	 */
	public WebDriver getWebDriver(Map<String, String> config){
		ExecutionMode executionMode = ExecutionMode.valueOf(DriverVariables.hub);
		WebDriver driver = null;
		switch (executionMode) {
		case Local:
			driver = localDriver.get();
			break;
			
		case Remote:
			driver = remoteDriver.get();
			break;
			
		case Sauce:
			driver = remoteDriver.get();
			break;
			
		default:
			break;
		}
		return driver;
	}
	
	/**
	 * This function is used to close the browser
	 * @param driver
	 */
	public void closeDriver(WebDriver driver){
		if(localDriver.get() != null){
			new DriverWrapper().closeDriver(driver);
			localDriver.remove();
		} else if(remoteDriver.getClass() != null){
			new DriverWrapper().closeDriver(driver);
			remoteDriver.remove();
		}
	}
}

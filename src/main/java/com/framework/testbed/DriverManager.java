package com.framework.testbed;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.framework.factory.PageFactoryDesign;

public class DriverManager extends DriverConnector{

	public ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
	public ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();
	public ThreadLocal<String> sessionId = new ThreadLocal<String>();

	/**
	 * This function is used to set the WebDriver on local/remote/sauce
	 * @param config
	 */
	public void setWebDriverSession(Map<String, String> config){
		ExecutionMode executionType = ExecutionMode.valueOf(DriverVariables.hub);
		PageFactoryDesign.getPageFactoryDesign();
		switch (executionType) {
		case Local:
			localDriver.set(super.getWebDriver(DriverVariables.browserName));
			break;
			
		case Device:
			localDriver.set(super.getMobileDriver());
			break;
			
		case Remote:
			remoteDriver.set(super.getRemoteWebDriver(config));
			break;
			
		case Sauce:
			remoteDriver.set(super.getSauceRemoteWebDriver(config));
			sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
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
	public WebDriver getWebDriver(){
		ExecutionMode executionMode = ExecutionMode.valueOf(DriverVariables.hub);
		WebDriver driver = null;
		switch (executionMode) {
		case Local:
			driver = localDriver.get();
			break;
			
		case Device:
			driver = localDriver.get();
			
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

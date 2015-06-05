package com.framework.testbed;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.framework.ProjectFolder;
import com.framework.constant.FrameworkConstants;
import com.framework.logger.Log4JLogger;
import com.opera.core.systems.OperaDriver;

public class DriverConnector {

	public enum BrowserName{
        FIREFOX,
        IE10,
        IE11,
        SAFARI,
        CHROME,
        OPERA,
        PHANTOMJS,
        ANDROID,
        IOS
    }
	
	public enum ExecutionMode{
		Local,
		Remote,
		Sauce
	}
	
	protected BrowserName browsername;
	protected RemoteWebDriver remoteDriver;
	public WebDriver driver;
	public DriverCapabilities driverCapabilities = new DriverCapabilities();
	
	public RemoteWebDriver getRemoteWebDriver(Map<String, String> config){
		String remoteHost = config.get("HUBHOST");
		String remotePort = config.get("HUBPORT");
		String browser = config.get("BROWSERNAME");
		String version = config.get("BROWSERVERSION");
		String huburl = "http://"+remoteHost+":"+remotePort+"/wd/hub";
		try {
			remoteDriver = new RemoteWebDriver(new URL(huburl), driverCapabilities.setBrowserCapabilities(browser, version));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remoteDriver;
	}
	
	public RemoteWebDriver getSauceRemoteWebDriver(Map<String, String> config){
		String remoteHost = config.get("HUBHOST");
		String remotePort = config.get("HUBPORT");
		String browser = config.get("BROWSERNAME");
		String version = config.get("BROWSERVERSION");
		String osName = config.get("OSNAME");
		String osVersion = config.get("OSVERSION");
		String huburl = "http://"+remoteHost+":"+remotePort+"/wd/hub";
		String name = config.get("JOBNAME");
		try {
			remoteDriver = new RemoteWebDriver(new URL(huburl), driverCapabilities.setSauceBrowserCapabilities(name, browser, version, osName, osVersion));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remoteDriver;
	}
	
	public WebDriver getWebDriver(String browser){
		browsername = BrowserName.valueOf(browser);
		DesiredCapabilities capabilities = null;
		try{
			switch (browsername) {
			case FIREFOX:
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new FirefoxDriver(capabilities);
				break;

			case CHROME:
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				System.setProperty("webdriver.chrome.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.CHROME_DRIVER_EXE_PATH);
				driver = new ChromeDriver();
				break;
				
			case IE10:
				System.setProperty("webdriver.ie.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.IE_DRIVER_EXE_PATH);
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new InternetExplorerDriver(capabilities);
				break;
				
			case IE11:
				System.setProperty("webdriver.ie.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.IE_DRIVER_EXE_PATH);
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new InternetExplorerDriver(capabilities);
				break;
				
			case OPERA:
				capabilities.setCapability("webdriver.opera.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.OPERA_DRIVER_32BIT_EXE_PATH);
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new OperaDriver();
				break;
				
			case SAFARI:
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new SafariDriver();
				break;
				
			case PHANTOMJS:
				capabilities = driverCapabilities.setBrowserCapabilities(browser, null);
				driver = new PhantomJSDriver(capabilities);
				break;
				
			case ANDROID:
				break;
				
			case IOS:
				break;
				
			default:
				new Log4JLogger().info("Driver is not defined, please define the driver");
				break;
			}//End of switch (Launching Browser)
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch(Exception e){
			e.printStackTrace();
			//Need to add the code for screenshot if the locator fails
		}
		return driver;
	}
	
	public static boolean isIE(){
		boolean flag = false;
		if(DriverVariables.browserName.contains("IE")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isFirefox(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("FIREFOX")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isChrome(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("CHROME")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isSafari(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("SAFARI")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isPhantomjs(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("PHANTOMJS")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isOpera(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("OPERA")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isAndroid(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("ANDROID")){
			flag = true;
		}
		return flag;
	}
	
	public static boolean isIOS(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("IOS")){
			flag = true;
		}
		return flag;
	}
}

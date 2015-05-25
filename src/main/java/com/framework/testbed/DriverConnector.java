package com.framework.testbed;

import java.net.MalformedURLException;
import java.net.URL;
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
	
	protected BrowserName browsername;
	public WebDriver driver;
	public DriverCapabilities driverCapabilities = new DriverCapabilities();
	public WebDriver getWebDriver(){
		BrowserName browsername = BrowserName.valueOf(DriverVariables.browserName);
		DesiredCapabilities capabilities;
		String seleniumHost = DriverVariables.seleniumHost;
		String seleniumPort = DriverVariables.seleniumPort;
		if(seleniumHost.equalsIgnoreCase("localhost")){
			switch (browsername) {
			case FIREFOX:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new FirefoxDriver(capabilities);
				break;

			case CHROME:
				//capabilities = driverCapabilities.setCapabilitiesForRemoteDriver();
				System.setProperty("webdriver.chrome.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.CHROME_DRIVER_EXE_PATH);
				driver = new ChromeDriver();
				break;
				
			case IE10:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new InternetExplorerDriver(capabilities);
				break;
				
			case IE11:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new InternetExplorerDriver(capabilities);
				break;
				
			case OPERA:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new OperaDriver();
				break;
				
			case SAFARI:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new SafariDriver();
				break;
				
			case PHANTOMJS:
				capabilities = driverCapabilities.setBrowserCapabilities();
				driver = new PhantomJSDriver(capabilities);
				break;
				
			case ANDROID:
				break;
				
			case IOS:
				break;
				
			default:
				break;
			}//End of switch (Launching Browser)
		}else if(seleniumHost.equalsIgnoreCase("sauce")){
			
		}else{
			capabilities = driverCapabilities.setBrowserCapabilities();
			try {
				driver = new RemoteWebDriver(new URL("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public boolean isIE(){
		boolean flag = false;
		if(DriverVariables.browserName.contains("IE")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isFirefox(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("FIREFOX")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isChrome(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("CHROME")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isSafari(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("SAFARI")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isPhantomjs(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("PHANTOMJS")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isOpera(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("OPERA")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isAndroid(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("ANDROID")){
			flag = true;
		}
		return flag;
	}
	
	public boolean isIOS(){
		boolean flag = false;
		if(DriverVariables.browserName.equalsIgnoreCase("IOS")){
			flag = true;
		}
		return flag;
	}
}

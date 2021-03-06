package com.framework.testbed;

import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.framework.ProjectFolder;
import com.framework.constant.FrameworkConstants;
import com.framework.testbed.DriverConnector.BrowserName;

public class DriverCapabilities {
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	/**
	 * This function is used to setup the driver capabilities for Sauce Labs
	 * @param name
	 * @param browser
	 * @param version
	 * @param osName
	 * @param osVersion
	 * @return DesiredCapabilities
	 */
	public DesiredCapabilities setSauceBrowserCapabilities(String name, String browser, String version, String osName, String osVersion) {
		capabilities.setCapability("name", name);
		capabilities.setCapability("os", osName);
		capabilities.setCapability("os_version", osVersion);
		this.setBrowserCapabilities(browser, version);
		return capabilities;
	}
	
	/**
	 * This function is used to set up the capabilities for the WebDrivers
	 * @param browser
	 * @param version
	 * @return
	 */
	public DesiredCapabilities setBrowserCapabilities(String browser, String version) {
		BrowserName browserName = BrowserName.valueOf(DriverVariables.browserName);
		String profileName = DriverVariables.profileName;
		switch (browserName) {
		case FIREFOX:
			ProfilesIni profileIni = new ProfilesIni();
	        FirefoxProfile profile = profileIni.getProfile(profileName);
	        profile.setAcceptUntrustedCertificates(true);
	        profile.setAssumeUntrustedCertificateIssuer(false);
	        profile.setEnableNativeEvents(true);
	        profile.setPreference("browser.download.folderList", 1);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("browser.download.manager.focusWhenStarting", false);
	        profile.setPreference("browser.download.useDownloadDir", true);
	        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
	        profile.setPreference("browser.download.manager.closeWhenDone", true);
	        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	        profile.setPreference("browser.download.manager.useWindow", false);
	        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
	        if(version != null){
				capabilities.setVersion(version);
			}
			break;

		case CHROME:
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("ignore-certificate-errors");
			options.addArguments("test-type");
			options.addArguments("--start-maximized");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setBrowserName("chrome");
			if(version != null){
				capabilities.setVersion(version);
			}
			break;
			
		case IE10: case IE11:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("enablePersistentHover", false);
			version = browserName.toString().replace("IE", "");
			capabilities.setBrowserName("iexplorer");
			capabilities.setVersion(version);
			break;
			
		case OPERA:
			capabilities = DesiredCapabilities.opera();
			capabilities.setCapability("opera.no_restart", false);
			capabilities.setCapability("opera.no_quit", false);
			capabilities.setCapability("opera.autostart", true);
			capabilities.setCapability("opera.port", -1);
			capabilities.setCapability("opera.profile", "");
			capabilities.setBrowserName("opera");
			if(version != null){
				capabilities.setVersion(version);
			}
			break;
			
		case SAFARI:
			capabilities = DesiredCapabilities.safari();
			capabilities.setBrowserName("safari");
			if(version != null){
				capabilities.setVersion(version);
			}
			capabilities.setPlatform(Platform.ANY);
			break;

		case PHANTOMJS:
			ArrayList<String> cliArgsCap = new ArrayList<String>();
			cliArgsCap.add("--web-security=false");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=true");
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[] { "--logLevel=2" });
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, ProjectFolder.getProjectFolder() + FrameworkConstants.PHANTONJS_DRIVER_EXE_PATH);
			capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capabilities = DesiredCapabilities.phantomjs();
			break;
			
		case DEVICE:
			if(DriverVariables.device.equalsIgnoreCase(FrameworkConstants.IPAD)){
				capabilities = DesiredCapabilities.ipad();
			}
			if(DriverVariables.device.equalsIgnoreCase(FrameworkConstants.IPHONE)){
				capabilities = DesiredCapabilities.iphone();
			}
			if(DriverVariables.device.equalsIgnoreCase(FrameworkConstants.ANDROID)){
				capabilities = DesiredCapabilities.android();
			}
			capabilities.setCapability("device", DriverVariables.device);
			capabilities.setCapability("deviceName", DriverVariables.deviceName);
			capabilities.setCapability("automationName", DriverVariables.automationName);
			capabilities.setCapability("platformVersion", DriverVariables.platformVersion);
			capabilities.setCapability("platformName", DriverVariables.platformName);
			capabilities.setCapability("device-orientation",DriverVariables.deviceOrientation);
			capabilities.setCapability("browserName", DriverVariables.deviceBrowser);
			capabilities.setCapability("appPackage", DriverVariables.appPackage);
			capabilities.setCapability("appActivity", DriverVariables.appActivity);
			capabilities.setCapability("app", DriverVariables.appAPKPath);
			break;
		}
		return capabilities;
	}
}

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
	String version = null;
	
	public DesiredCapabilities setBrowserCapabilities() {
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
			capabilities.setCapability("webdriver.chrome.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.CHROME_DRIVER_EXE_PATH);
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
			System.out.println(ProjectFolder.getProjectFolder() + FrameworkConstants.IE_DRIVER_EXE_PATH);
			System.setProperty("webdriver.ie.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.IE_DRIVER_EXE_PATH);
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
			capabilities.setCapability("webdriver.opera.driver", ProjectFolder.getProjectFolder() + FrameworkConstants.OPERA_DRIVER_32BIT_EXE_PATH);
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
			
		case ANDROID:
			break;
			
		case IOS:
			break;
		}
		return capabilities;
	}
}

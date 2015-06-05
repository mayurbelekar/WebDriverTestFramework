package com.framework.testbed;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.framework.constant.FrameworkConstants;
import com.framework.logger.Log4JLogger;

public class DriverVariables {

	Properties props;
	Map<String, String> config = new HashMap<String, String>();
	public static String locatorPath = null;
	public static String seleniumPort = null;
	public static String seleniumHost = null;
	public static String profileName = null;
	public static String environment = null;
	public static String browserName = null;
	String browser = null;
	String browserVersion = null;
	String osName = null;
	String osVersion = null;
	String remoteHost = null;
	String remotePort = null;
	public static String hub = null;
	public static String screenshotFolder = null;
	
	/**
	 * This function will read all the config parameters from the config file in the test project
	 * @param configData
	 */
	public Map<String, String> setConfigVariables(Properties props, String name){
		//Getting parameters from the config file
		locatorPath = props.getProperty(FrameworkConstants.LOCATOR_PATH);
		browserName = props.getProperty(FrameworkConstants.BROWSER_NAME);
		browser = props.getProperty(FrameworkConstants.BROWSER_NAME);
		browserVersion = props.getProperty(FrameworkConstants.BROWSER_VERSION);
		osName = props.getProperty(FrameworkConstants.OS_NAME);
		osVersion = props.getProperty(FrameworkConstants.OS_VERSION);
		environment = props.getProperty(FrameworkConstants.ENV);
		seleniumPort = props.getProperty(FrameworkConstants.PORT);
		seleniumHost = props.getProperty(FrameworkConstants.HOST);
		profileName = props.getProperty(FrameworkConstants.PROFILE);
		remotePort = props.getProperty(FrameworkConstants.HUB_PORT);
		remoteHost = props.getProperty(FrameworkConstants.HUB_HOST);
		screenshotFolder = props.getProperty(FrameworkConstants.SCREENSHOT_FOLDER);
		hub = props.getProperty(FrameworkConstants.HUB);
		if(seleniumPort == null){
			seleniumPort = "4444";
		}
		if(seleniumHost == null){
			seleniumHost = "localhost";
		}
		if(profileName == null){
			profileName = "default";
		}
		
		//Adding config parameters to map
		config.put("BROWSERNAME", browserName);
		config.put("BROWSERVERSION", browserVersion);
		config.put("OSNAME", osName);
		config.put("OSVERSION", osVersion);
		config.put("HUBPORT", remotePort);
		config.put("HUBHOST", remoteHost);
		//config.put("HUB", hub);
		config.put("JOBNAME", name);
		return config;
	}
	
	public Map<String, String> readConfigFile(String configFilePath) throws IOException{
		props = new Properties();
		File configFile = new File(configFilePath);
		System.out.println("config file name:  " + configFile.getAbsolutePath());
		new Log4JLogger().info(configFile.getAbsolutePath() + " config file name:  " + configFilePath);
		FileReader configFileReader = new FileReader(configFile.getAbsolutePath());
		props.load(configFileReader);
		Map<String, String> config = this.setConfigVariables(props, configFile.getName());
		return config;
	}
}
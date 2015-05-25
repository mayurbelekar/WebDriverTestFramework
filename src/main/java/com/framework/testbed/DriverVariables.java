package com.framework.testbed;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.framework.logger.Log4JLogger;

public class DriverVariables {

	Properties props;
	public static String locatorPath = null;
	public static String browserName = null;
	public static String seleniumPort = null;
	public static String seleniumHost = null;
	public static String browserVersion = null;
	public static String profileName = null;
	public static String osName = null;
	public static String osVersion = null;
	public static String environment = null;
	
	/**
	 * This function will read all the config parameters from the config file in the test project
	 * @param configData
	 */
	public void setConfigVariables(Properties props){
		locatorPath = props.getProperty("LOCATORPATH");
		browserName = props.getProperty("BROWSERNAME");
		browserVersion = props.getProperty("BROWSERVERSION");
		osName = props.getProperty("OSNAME");
		osVersion = props.getProperty("OSVERSION");
		environment = props.getProperty("ENV");
		seleniumPort = props.getProperty("PORT");
		seleniumHost = props.getProperty("HOST");
		profileName = props.getProperty("PROFILE");
		if(seleniumPort == null){
			seleniumPort = "4444";
		}
		if(seleniumHost == null){
			seleniumHost = "localhost";
		}
		if(profileName == null){
			profileName = "default";
		}
	}
	
	public void readConfigFile(String configFilePath) throws IOException{
		props = new Properties();
		File configFile = new File(configFilePath);
		System.out.println("config file name:  " + configFile.getAbsolutePath());
		new Log4JLogger().info(configFile.getAbsolutePath() + " config file name:  " + configFilePath);
		FileReader configFileReader = new FileReader(configFile.getAbsolutePath());
		props.load(configFileReader);
		this.setConfigVariables(props);
	}
	
}

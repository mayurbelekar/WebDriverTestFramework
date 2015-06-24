package com.framework.constant;

import java.io.File;


public class FrameworkConstants {

	//*****                        Drivers executable file                        *****//
	public static final String IE_DRIVER_EXE_PATH = "\\src\\test\\java\\resources\\IEDriverServer.exe";
	public static final String CHROME_DRIVER_EXE_PATH = "\\src\\test\\java\\resources\\chromedriver.exe";
	public static final String OPERA_DRIVER_32BIT_EXE_PATH = "\\src\\test\\java\\resources\\operadriver.exe";
	public static final String OPERA_DRIVER_64BIT_EXE_PATH = "\\src\\test\\java\\resources\\operadriver.exe";
	public static final String PHANTONJS_DRIVER_EXE_PATH = "\\src\\test\\java\\resources\\phantomjs.exe";
	//*****                        Drivers executable file                        *****//
	
	public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
	public static final String LOG4J_PATH = "\\src\\main\\java\\com\\framework\\logger";
	
	//*****                        Config Constant Parameters                        *****//
	public static final String LOCATOR_PATH = "LOCATORPATH";
	public static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + File.separator + "Screenshot";
	public static final String BROWSER_NAME = "BROWSERNAME";
	public static final String BROWSER_VERSION = "BROWSERVERSION";
	public static final String OS_NAME = "OSNAME";
	public static final String OS_VERSION = "OSVERSION";
	public static final String ENV = "ENV";
	public static final String PORT = "PORT";
	public static final String HOST = "HOST";
	public static final String PROFILE = "PROFILE";
	public static final String HUB_PORT = "HUBPORT";
	public static final String HUB_HOST = "HUBHOST";
	public static final String HUB = "HUB";
	//*****                        Config Constant Parameters                        *****//
	
	//*****                        Android Capabilities Paprmeters                        *****//
	public static final String DEVICE = "DEVICE";
	public static final String DEVICE_NAME = "DEVICENAME";
	public static final String DEVICE_BROWSER = "DEVICEBROWSER";
	public static final String AUTOMATION_NAME = "AUTOMATIONNAME";
	public static final String PLATFORM_VERSION = "PLATFORMVERSION";
	public static final String PLATFORM_NAME = "PLATFORMNAME";
	public static final String APP_PACKAGE = "APPPACKAGE";
	public static final String APP_ACTIVITY = "APPACTIVITY";
	public static final String APP_APK_PATH = "APPPATH";
	public static final String DEVICE_ORIENTATION = "DEVICEORIENTATION";
	//*****                        Android Capabilities Paprmeters                        *****//
	
	public static final String IPAD = "ipad";
	public static final String IPHONE = "iphone";
	public static final String ANDROID = "android";
}

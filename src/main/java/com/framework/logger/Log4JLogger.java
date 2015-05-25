package com.framework.logger;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import com.framework.ProjectFolder;
import com.framework.constant.FrameworkConstants;


public class Log4JLogger {

	private Logger logger = Logger.getLogger(Log4JLogger.class);
	
	public Log4JLogger(){
		String log4jPropFile = ProjectFolder.getProjectFolder() + FrameworkConstants.LOG4J_PATH + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jPropFile);
	}
	
	public void info(Object message){
		logger.info(message);
		Reporter.log(message.toString());
	}
	
	public void error(Object message){
		logger.error(message);
		Reporter.log(message.toString());
	}
	
	public void debug(Object message){
		logger.debug(message);
		Reporter.log(message.toString());
	}
}
package com.framework.appium;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class AppiumServer {

	static Process process;

	public void startAppiumServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			process = runtime.exec("\\startServer.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void stopAppiumServer() {
		process.destroy();
	}
	
	public static void main(String [] main){
		System.out.println(new Throwable().getStackTrace()[0].getClassName());
		//new AppiumServer().startAppiumServer();
	}
}
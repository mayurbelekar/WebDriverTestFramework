package com.framework.appium;

import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class AppiumServer {

	CommandLine command;
	DefaultExecutor executor;
	DefaultExecuteResultHandler resultHandler;

	public void startAppiumServer() {
		command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("C:\\AppiumForWindows-1.3.4.1/Appium/node.exe");
		command.addArgument("C:/AppiumForWindows-1.3.4.1/Appium/node_modules/appium/bin/appium.js");
		command.addArgument("--address");
		command.addArgument("127.0.0.1");
		command.addArgument("--port");
		command.addArgument("4724");
		command.addArgument("--no-reset");
		command.addArgument("--log");
		command.addArgument("D:/appiumLogs.txt");
		resultHandler = new DefaultExecuteResultHandler();
		executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopAppiumServer() {
		command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("taskkill");
		command.addArgument("/F");
		command.addArgument("/IM");
		command.addArgument("node.exe");
		resultHandler = new DefaultExecuteResultHandler();
		executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
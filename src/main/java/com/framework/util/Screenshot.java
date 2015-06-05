package com.framework.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;

import com.framework.testbed.DriverVariables;
import com.framework.testbed.DriverConnector.ExecutionMode;

public class Screenshot {

	File screenshotFile = null;
	File screenshotFolder = null;
	File screenshot = null;
	WebDriver augmenterDriver = null;
	public void takesScreenshot(WebDriver driver, ITestResult iTestResult){
		ExecutionMode executionMode = ExecutionMode.valueOf(DriverVariables.hub);
		switch (executionMode) {
		case Local:
			screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			break;

		case Remote: case Sauce:
			augmenterDriver = new Augmenter().augment(driver);
			screenshot = ((TakesScreenshot) augmenterDriver).getScreenshotAs(OutputType.FILE);
			break;
			
		default:
			break;
		}
		String screenshotName = iTestResult.getName();
		String folderName = iTestResult.getTestClass().getName().toString();
		screenshotFolder = new File(DriverVariables.screenshotFolder + File.separator + folderName);
		if(!screenshotFolder.exists()){
			screenshotFolder.mkdir();
		}
		screenshotFile = new File(DriverVariables.screenshotFolder + File.separator + folderName + File.separator + screenshotName + File.separator + ".jpg");
		try {
			FileUtils.copyFile(screenshot, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

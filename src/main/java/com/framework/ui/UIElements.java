package com.framework.ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.testbed.DriverWrapper;


public class UIElements <T extends UIElement>{

	protected String uiLocator; 
	public UIElements(String locator){
		uiLocator = locator;
	}
	
	public String getLocator(){
		return uiLocator;
	}
	
	public List<WebElement> getElementsList(WebDriver driver, String locator) {
		return new DriverWrapper().getWebElements(driver, this);
	}
	
	public List<T> getChildElements(Class<T> klass, WebDriver driver) {
		Constructor<?> constructor = null;
		try {
			constructor = klass.getConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<T> uiElementList = new ArrayList<T>();
		List<WebElement>  elementList = new DriverWrapper().getWebElements(driver, this);
		for (WebElement anElement : elementList){
			try {
				@SuppressWarnings("unchecked")
				T t = (T) constructor.newInstance();
				t.setWebElement(anElement);
				uiElementList.add(t);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return uiElementList;
	}
}

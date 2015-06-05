package com.framework.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryDesign {

	private static PageFactoryDesign pageFactoryDesign;
	
	/**
	 * PageFactoryDesign constructor
	 */
	public PageFactoryDesign(){}
	
	/**
	 * This synchronized function is used to create the object reference of the class
	 * @return PageFactoryDesign
	 */
	public static synchronized PageFactoryDesign getPageFactoryDesign(){
		if(pageFactoryDesign == null){
			pageFactoryDesign = new PageFactoryDesign();
		}
		return pageFactoryDesign;
	}
	
	/**
	 * This function is used to create the page factory for the page objects
	 * @param klass
	 * @param driver
	 * @return <T extends LocatorTypes> T
	 */
	public static <T extends LocatorTypes> T getPageObject(Class<T> klass, WebDriver driver){
		return PageFactory.initElements(driver, klass);
	}
}

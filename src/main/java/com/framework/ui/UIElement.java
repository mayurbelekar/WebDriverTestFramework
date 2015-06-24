package com.framework.ui;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.testbed.DriverWrapper;

public class UIElement {

	protected String uiLocator;
	protected WebElement element;
	
	public UIElement(){}
	
	public UIElement(String locator){
		this.uiLocator = locator;
	}
	
	public void setWebElement(WebElement element){
		this.element = element;
	}
	
	public String getLocator(){
		return uiLocator;
	}
	
	public void click(WebDriver driver){
		new DriverWrapper().click(driver, this);
	}
	
	public void jsClick(WebDriver driver){
		new DriverWrapper().jsClick(driver, this);
	}
	
	public void moveToElement(WebDriver driver){
		new DriverWrapper().getWebElement(driver, this);
	}
	
	public Point getLocation(WebDriver driver){
		Point point = new DriverWrapper().getLocation(driver, this);
		return point;
	}
	
	public Dimension getSize(WebDriver driver){
		Dimension dimension = new DriverWrapper().getSize(driver, this);
		return dimension;
	}
	
	public WebElement getActiveElement(WebDriver driver){
		WebElement element = new DriverWrapper().getActiveElement(driver);
		return element;
	}
	
	public String getAttibuteValue(WebDriver driver, String attributeName){
		String attributeValue = new DriverWrapper().getAttributeValue(driver, this, attributeName);
		return attributeValue;
	}
	
	public void visibleElement(WebDriver driver){
		new DriverWrapper().visibleElement(driver, this);
	}
	
	
	public void hideElement(WebDriver driver){
		new DriverWrapper().hideElement(driver, this);
	}
	
	public void getElementToFront(WebDriver driver){
		new DriverWrapper().getElementToFront(driver, this);
	}
	
	public void sendKeys(WebDriver driver, String text){
		new DriverWrapper().sendKeys(driver, this, text);
	}
	
	public boolean isEnabled(WebDriver driver){
		return new DriverWrapper().isEnabled(driver, this);
	}
	
	public boolean isDisplayed(WebDriver driver){
		return new DriverWrapper().isDisplayed(driver, this);
	}
	
	public boolean isSelected(WebDriver driver){
		return new DriverWrapper().isSelected(driver, this);
	}
	
	public void elementToBeClickable(WebDriver driver){
		new DriverWrapper().elementToBeClickable(driver, this);
	}
}

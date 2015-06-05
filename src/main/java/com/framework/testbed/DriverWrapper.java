package com.framework.testbed;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.logger.Log4JLogger;
import com.framework.ui.UIElement;
import com.framework.ui.UIElements;

public class DriverWrapper {

	WebElement element = null;
	
	public enum LocatorType{
        XPATH,
        CSS,
        CLASSNAME,
        LINK,
        PARTIALLINK,
        ID,
        NAME
    }
	
	/**
	 * Set the url in the browser
	 * @param driver
	 * @param url
	 */
	public void setURL(WebDriver driver, String url){
		new Log4JLogger().info("Launch url in the browser "+url);
		driver.get(url);
	}
	
	/**
	 * Close the current window
	 * @param driver
	 */
	public void closeDriver(WebDriver driver){
		new Log4JLogger().info("Closing the driver "+driver);
		driver.close();
	}
	
	/**
	 * Close all the windows and quit the browser
	 * @param driver
	 */
	public void quitDriver(WebDriver driver){
		new Log4JLogger().info("Quit the driver "+driver);
		driver.quit();
	}
	
	/**
	 * Get the url of the current browser
	 * @param driver
	 * @return String
	 */
	public String getCurrentURL(WebDriver driver){
		new Log4JLogger().info("Current url is "+driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	/**
	 * This function is used to refresh the current page
	 * @param driver
	 */
	public void refresh(WebDriver driver){
		new Log4JLogger().info("Refreshing the page");
		driver.navigate().refresh();
	}
	
	/**
	 * This function is used to navigate to the back page
	 * @param driver
	 */
	public void back(WebDriver driver){
		new Log4JLogger().info("navigate to the back page");
		driver.navigate().back();
	}
	
	/**
	 * This function is used to navigate to the url
	 * @param driver
	 * @param url
	 */
	public void navigateTo(WebDriver driver, String url){
		new Log4JLogger().info("navigate to the url "+ url);
	}
	
	/**
	 * This function is used to delete all the browser cookies
	 * @param driver
	 */
	public void deleteAllCookies(WebDriver driver){
		new Log4JLogger().info("Deleting all the browser cookies");
		driver.manage().deleteAllCookies();
	}
	
	/**
	 * This function is used to delete the cookie by name
	 * @param driver
	 * @param cookieName
	 */
	public void deleteCookie(WebDriver driver, String cookieName){
		new Log4JLogger().info("Delete the browser cookie by name");
		driver.manage().deleteCookieNamed(cookieName);
	}
	
	/**
	 * This function is used to get the cookie by name
	 * @param driver
	 * @param cookieName
	 */
	public Cookie getCookieValue(WebDriver driver, String cookieName){
		new Log4JLogger().info("Get the cookie by name to get all the cookie values");
		return driver.manage().getCookieNamed(cookieName);
	}
	
	/**
	 * Get WebElement using the parameter locator
	 * @param driver
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getWebElement(WebDriver driver, UIElement uiElement){
		String locator = uiElement.getLocator();
		LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
		locator = locator.substring(locator.indexOf("=")+1, locator.length());
		By by = null;
		switch (identifier) {
		case XPATH:
			by = By.xpath(locator);
			break;

		case CSS:
			by = By.cssSelector(locator);
			break;
			
		case CLASSNAME:
			by = By.className(locator);
			break;
			
		case LINK:
			by = By.linkText(locator);
			break;
			
		case PARTIALLINK:
			by = By.partialLinkText(locator);
			break;
			
		case ID:
			by = By.id(locator);
			break;
			
		case NAME:
			by = By.name(locator);
			break;
			
		default:
			break;
		}
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element = driver.findElement(by);
		return element;
	}

	/**
	 * Returns the list of WebElements using the locator
	 * @param driver
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getWebElements(WebDriver driver, UIElements<? extends UIElement> uiElementList){
		String locator = uiElementList.getLocator();
		LocatorType identifier = LocatorType.valueOf(locator.toUpperCase().substring(0, locator.indexOf("=")));
		locator = locator.substring(locator.indexOf("=")+1, locator.length());
		List<WebElement> elements = null;
		By by = null;
		switch (identifier) {
		case XPATH:
			by = By.xpath(locator);
			break;

		case CSS:
			by = By.cssSelector(locator);
			break;
			
		case CLASSNAME:
			by = By.className(locator);
			break;
			
		case LINK:
			by = By.linkText(locator);
			break;
			
		case ID:
			by = By.id(locator);
			break;
			
		case NAME:
			by = By.name(locator);
			break;
			
		default:
			break;
		}
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		elements = driver.findElements(by);
		return elements;
	}
	
	/**
	 * Maximize the size of the browser using the monitor width and height
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver){
		new Log4JLogger().info("Maximizing the window");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		double height = dimension.getHeight();
		double width = dimension.getWidth();
		driver.manage().window().setSize(new org.openqa.selenium.Dimension((int)width, (int)height));
		new Log4JLogger().info("Maximize window size: Width = "+width+" Height = "+height);
	}
	
	/**
	 * This bring the element to the front in case the element hides behind the other element
	 * @param driver
	 * @param elementLocator
	 */
	public void getElementToFront(WebDriver driver, UIElement elementLocator){
		new Log4JLogger().info("Bringing element to front "+elementLocator);
		WebElement element = getWebElement(driver, elementLocator);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if(!element.getCssValue("position").equals("relative") && !element.getCssValue("z-index").equals("1000")){
            if(element.getAttribute("style") != null && element.getAttribute("style").toString().trim().length() == 0){
                js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "position: relative; z-index: 1000;");
            }else{
                String existingStyle = element.getAttribute("style");
                js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "position: relative; z-index: 1000;"+existingStyle);
            }
        }
	}

	/**
	 * This method is used to hide the element using its css properties
	 * @param driver
	 * @param elementLocator
	 */
	public void hideElement(WebDriver driver, UIElement elementLocator){
		new Log4JLogger().info("Hiding the element "+elementLocator);
		WebElement element = getWebElement(driver, elementLocator);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', 'visibility:hidden;')", element);
	}
	
	/**
	 * This method is used to visible the element using its css properties
	 * @param driver
	 * @param elementLocator
	 */
	public void visibleElement(WebDriver driver, UIElement elementLocator){
		new Log4JLogger().info("Hiding the element "+elementLocator);
		WebElement element = getWebElement(driver, elementLocator);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', 'visibility:visible;')", element);
	}
	
	/**
	 * Fluent wait method
	 * @param driver
	 * @return
	 */
	public Wait<WebDriver> webDriverFluentWait(WebDriver driver){
		Wait<WebDriver> fluentWait = null;
		if(driver != null) {
			fluentWait = new FluentWait<WebDriver>(driver)
					.withTimeout(120, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class, TimeoutException.class);	
		}
		return fluentWait;
	}
	
	/**
	 * This function is used to click on the element
	 * @param driver
	 * @param locator
	 */
	public void click(WebDriver driver, UIElement locator){
		new Log4JLogger().info("Clicks on the element "+locator);
		WebElement element = getWebElement(driver, locator);
		element.click();
	}
	
	/**
	 * This function is used to click the element using the JavaScript
	 * @param driver
	 * @param locator
	 */
	public void jsClick(WebDriver driver, UIElement locator){
		new Log4JLogger().info("Clicks on the element using Javascript "+locator);
		WebElement element = getWebElement(driver, locator);
		JavascriptExecutor jsExec = ((JavascriptExecutor) driver);
		jsExec.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * This function is used to enter the text in the textfield
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public void sendKeys(WebDriver driver, UIElement locator, String text){
		new Log4JLogger().info("Entering the text in the element "+locator);
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * This function is used to return the active element
	 * @param driver
	 * @return WebElement
	 */
	public WebElement getActiveElement(WebDriver driver){
		WebElement element = driver.switchTo().activeElement();
		new Log4JLogger().info("Active Element "+element);
		return element;
	}
	
	/**
	 * This method is use to get the size of the element
	 * @param driver
	 * @param locator
	 * @return Dimension
	 */
	public org.openqa.selenium.Dimension getSize(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		int width = element.getSize().width;
		int height = element.getSize().height;
		org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(width, height);
		return dimension;
	}
	
	/**
	 * This method is use to get the location of the element
	 * @param driver
	 * @param locator
	 * @return Location
	 */
	public Point getLocation(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		int x = element.getLocation().x;
		int y = element.getLocation().y;
		Point point = new Point(x, y);
		return point;
	}
	
	/**
	 * This function is used to move the mouse to the desired element location
	 * @param driver
	 * @param locator
	 */
	public void moveToElement(WebDriver driver, UIElement locator){
		new Log4JLogger().info("control moves to the element "+locator);
		WebElement element = getWebElement(driver, locator);
		new Actions(driver).moveToElement(element).perform();
	}
	
	/**
	 * This function is used to drag the source object and drop into the target object
	 * @param driver
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragAndDrop(WebDriver driver, UIElement sourceLocator, UIElement targetLocator){
		new Log4JLogger().info("Drag the source object to the target object");
		WebElement sourceElement = getWebElement(driver, sourceLocator);
		WebElement targetElement = getWebElement(driver, targetLocator);
		new Actions(driver).dragAndDrop(sourceElement, targetElement).perform();
	}
	
	/**
	 * This function is used to get the attribute value
	 * @param driver
	 * @param locator
	 * @param attributeName
	 * @return
	 */
	public String getAttributeValue(WebDriver driver, UIElement locator, String attributeName){
		new Log4JLogger().info("Get the attribute value for "+attributeName);
		WebElement element = getWebElement(driver, locator);
		String attributeValue = element.getAttribute(attributeName);
		return attributeValue;
	}
	
	/**
	 * This function is used to verify the element is enabled or not.
	 * @param driver
	 * @param locator
	 * @return boolean
	 */
	public boolean isEnabled(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		boolean flag = element.isEnabled();
		return flag;
	}
	
	/**
	 * This function is used to verify the element is displayed or not.
	 * @param driver
	 * @param locator
	 * @return flag
	 */
	public boolean isDisplayed(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		boolean flag = element.isDisplayed();
		return flag;
	}
	
	/**
	 * This function is used to verify the element like radiobutton or checkbox is selected or not
	 * @param driver
	 * @param locator
	 * @return flag
	 */
	public boolean isSelected(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		boolean flag = element.isSelected();
		return flag;
	}
	
	/**
	 * This function is used to select the value from dropdown using element click 
	 * @param driver
	 * @param locator
	 * @param value
	 */
	public void selectValue(WebDriver driver, UIElement locator, String value){
		WebElement element = getWebElement(driver, locator);
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement we : options){
			if(we.getText().equalsIgnoreCase(value)){
				we.click();
				break;
			}
		}
	}
	
	/**
	 * This function is used to select the value from the drop down using the value attribute
	 * @param driver
	 * @param locator
	 * @param value
	 */
	public void selectByValue(WebDriver driver, UIElement locator, String value){
		WebElement element = getWebElement(driver, locator);
		new Select(element).selectByValue(value);
	}
	
	/**
	 * This function is used to select the value from the dropdown using the visible text 
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public void selectByVisisbleText(WebDriver driver, UIElement locator, String text){
		WebElement element = getWebElement(driver, locator);
		new Select(element).selectByVisibleText(text);
	}
	
	/**
	 * This function i sused to select the value from the dropdown using the index number
	 * @param driver
	 * @param locator
	 * @param index
	 */
	public void selectByIndex(WebDriver driver, UIElement locator, int index){
		WebElement element = getWebElement(driver, locator);
		new Select(element).selectByIndex(index);
	}
	
	/**
	 * This function is used to check the checkbox
	 * @param driver
	 * @param locator
	 */
	public void check(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		if(!element.isSelected()){
			element.click();
		}
	}
	
	/**
	 * This function is used to uncheck the checkbox
	 * @param driver
	 * @param locator
	 */
	public void uncheck(WebDriver driver, UIElement locator){
		WebElement element = getWebElement(driver, locator);
		if(element.isSelected()){
			element.click();
		}
	}
}
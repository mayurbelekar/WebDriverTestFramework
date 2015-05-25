package com.framework.factory;

public abstract class PageAction {

	protected PageFactoryDesign pageFactoryDesign;
	
	public PageFactoryDesign getPageFactoryDesign(){
		return pageFactoryDesign;
	}
	
	public void setPageFactoryDesign(PageFactoryDesign thePageFactoryDesign){
		pageFactoryDesign = thePageFactoryDesign;
	}
}

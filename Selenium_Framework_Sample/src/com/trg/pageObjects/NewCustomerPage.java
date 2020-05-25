package com.trg.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewCustomerPage {
	private WebDriver driver = null;
    private WebDriverWait wait = null;
    
    public NewCustomerPage (WebDriver driverArg) {
		driver = driverArg;
		wait = new WebDriverWait(driver,20 /*timeout in seconds*/);
	}
}

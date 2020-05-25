package com.trg.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trg.dataObjects.LoginPageDO;

public class LoginPage {

	private WebDriver driver = null;
    private WebDriverWait wait = null;
	
	private By userName = By.xpath("//input[@name='uid']");
	private By passWord = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//input[@name='btnLogin']");
	//By resetBtn = By.xpath("//input[@name='btnReset']");
	
	public LoginPage (WebDriver driverArg) {
		driver = driverArg;
		wait = new WebDriverWait(driver,20 /*timeout in seconds*/);
	}
	
	public void populateLogin(LoginPageDO loginDO) throws Exception {
		enterUserName(loginDO);
		enterPassWord(loginDO);
		clickSubmitBtn(loginDO);
		//clickResetBtn(loginDO);
	}

	public void clickSubmitBtn(LoginPageDO loginDO) throws Exception{
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		//driver.findElement(loginBtn).click();
		
	}

	public void enterPassWord(LoginPageDO loginDO) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(passWord)).sendKeys(loginDO.getPassWord());
		//driver.findElement(passWord).sendKeys(loginDO.getPassWord());
		
	}

	public void enterUserName(LoginPageDO loginDO) throws Exception{
		wait.until(ExpectedConditions.elementToBeClickable(userName)).sendKeys(loginDO.getUserName());
		//driver.findElement(userName).sendKeys(loginDO.getUserName());
		
	}
	

	/*
	 * public void clickResetBtn(LoginPageDO loginDO) {
	 * driver.findElement(resetBtn).click();
	 * 
	 * }
	 */
	
}

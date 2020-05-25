package com.trg.testScripts;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.trg.dataObjects.LoginPageDO;
import com.trg.pageObjects.LoginPage;
import com.trg.utils.UtilConstants;

/**
 * @author udkd
 *
 */
public class LoginPageTest {
	 private WebDriver driver ; 
	 private LoginPage loginPageObj;
	 private String parentWindowHandler;
	 private WebDriverWait wait;
	 
		@BeforeTest
		public void configureDriver() {
			System.setProperty("webdriver.chrome.driver", UtilConstants.CHROMEDRIVER_PATH);
			driver = new ChromeDriver();
			driver.get(UtilConstants.BASEURL);
			driver.manage().window().maximize();
			parentWindowHandler = driver.getWindowHandle();
			//System.out.println("parentWindowHandler  "+parentWindowHandler);
			loginPageObj = new LoginPage(driver);
		}

		@Test(dataProvider = "getData")
		public void testLogin(LoginPageDO loginDO) {
			
			try {
				System.out.println("Inside testLogin : loginDO :" + loginDO);
				loginPageObj.populateLogin(loginDO);
				
			}catch (Exception e) {
				System.out.println("Exception in testLogin : "+e.getMessage());
				e.printStackTrace();
				Assert.assertTrue(Boolean.FALSE);
					
			}
		}

		@DataProvider
		public Object[][] getData() throws FileNotFoundException {
			JsonElement jsonData = new JsonParser().parse(new FileReader(UtilConstants.LOGINPAGE_JSON_PATH));
			JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
			
			/* converting json string to Java Data Objects */
			Type type = new TypeToken<List<LoginPageDO>>(){}.getType();
			List<LoginPageDO> testData = new Gson().fromJson(dataSet,type);
			
			Object[][] returnValue = new Object[testData.size()][1];
			int index = 0;
			for (Object[] each : returnValue) {
				each[0] = testData.get(index++);
			}
			return returnValue;
		}
		
		 @AfterMethod
	      public void goBackToHomepage ( ) {
				try {
					System.out.println("Inside goBackToHomepage   ");
					
					if (isAlertDialogPresent(driver)) {
						Alert alert = driver.switchTo().alert();
						System.out.println("alert present : " + alert.getText());
						if (alert.getText().equalsIgnoreCase("User is not valid")) {
							alert.accept();
							Assert.assertTrue(Boolean.TRUE);
						}
					} 
					else if(driver.getTitle().equalsIgnoreCase("GTPL Bank Manager HomePage")) {
					    System.out.println("Successful Login : "+driver.getTitle());
						Assert.assertTrue(Boolean.TRUE);
						driver.navigate().back();
					}
				} catch (Exception e) {
					System.out.println("Exception in goBackToHomepage: " + e.getMessage());
					e.printStackTrace();
					Assert.assertTrue(Boolean.FALSE);

				}
		 }
		 
		 @AfterTest
	      public void terminateBrowser(){
	          driver.close();
	          driver.quit();
	      }
		 
		 private static boolean isAlertPresent(WebDriver driver) {
		        try {
		            driver.getTitle();
		            return false;
		        } catch (UnhandledAlertException e) {
		            // Modal dialog showed
		            return true;
		        }
		    }
		 
		 private  boolean isAlertDialogPresent(WebDriver driver){
			    boolean foundAlert = false;
			     wait = new WebDriverWait(driver,5 /*timeout in seconds*/);
			    try {
			        wait.until(ExpectedConditions.alertIsPresent());
			        foundAlert = true;
			    } catch (TimeoutException eTO) {
			        foundAlert = false;
			    }
			    return foundAlert;
			}
		 
			/*
			 * String subWindowHandler = null; Set<String> handles =
			 * driver.getWindowHandles(); // get all window handles Iterator<String>
			 * iterator = handles.iterator(); while (iterator.hasNext()){ subWindowHandler =
			 * iterator.next();
			 * System.out.println("Inside testLogin : WindowHandlr : "+subWindowHandler); }
			 */
}

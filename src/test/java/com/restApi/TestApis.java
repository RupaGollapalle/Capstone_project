package com.restApi;

import java.time.Duration;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.database.*;
import com.testLib.ListenersReporting;

import io.restassured.response.Response;

import utils.UtilsFunctions;

@Listeners(ListenersReporting.class)
public class TestApis {



	WebDriver driver = new ChromeDriver();
	String URL = "http://localhost:8080/FlyAway/";
	
	
	ApiAndUi obj = new ApiAndUi();
	RestApi api = new RestApi();
	Mysql db = new Mysql();
	
	
	
	@DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][] {
                {"fedup@life", "12345", "12345", "vamsi", "Andhra pradesh", "Anantapur"}
                };
    }
	

	@BeforeClass
	public void beforeClass() {

		try {
			System.out.println("\nbefore class----");
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			driver.get(URL);
			System.out.println(driver.getTitle());

		} catch (ElementNotInteractableException e) {
			UtilsFunctions.explicitlyWait(driver, 5);
		} catch (TimeoutException e2) {
			UtilsFunctions.explicitlyWait(driver, 5);
		}
	}
	
	
	
	

	@Test(priority = 0,dataProvider = "userData")
	public void registerByRestAssuredAPI(String email, String pwd, String confirm_pwd, String name, String address, String city) {
		
		Response res = obj.register(email, pwd, confirm_pwd, name, address, city);
		Assert.assertEquals(200, res.getStatusCode());

	}

	@Test(priority = 1,dataProvider = "userData")
	public void loginCheckUI(String email, String pwd, String confirm_pwd, String name, String address, String city) {
		
		String login_status = obj.login(email, pwd, driver);
		System.out.println(login_status);
		Assert.assertEquals("Login - Successful", login_status);
	}

	@Test(priority = 2,dataProvider = "userData")
	public void dbCheck(String email, String pwd, String confirm_pwd, String name, String address, String city) {
		
		boolean status = db.dbCheck(name, email);
		Assert.assertTrue(status);
	}
	
	@Test(priority = 3)
	public void loginByRestAssuredAPI() {
		
		 Response res = api.testLogin();
		 Assert.assertEquals(200, res.getStatusCode());		 
	}
	@Test(priority = 4)
	public void getFLightsOnGivenDate() {
		
		 Response res = api.getFlightsOnGivenDate();
		 Assert.assertEquals(200, res.getStatusCode());
		
	}
	@Test(priority = 5)
	public void getBookedFlights() {
		
		 Response res = api.getBookedFlights();
		 Assert.assertEquals(200, res.getStatusCode());
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		System.out.println("\nAfter class --> Driver Closed");
	}

}

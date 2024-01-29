package com.testLib;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.libraries.*;


import utils.DriverClass;
import utils.UtilsFunctions;

@Listeners(ListenersReporting.class)
public class NewTestFlyAway {
	
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {

		try {
			System.out.println("\nbefore class----");
			driver = DriverClass.getWebDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			System.out.println(driver.getTitle());

		} catch (ElementNotInteractableException e) {
			UtilsFunctions.explicitlyWait(driver, 5);
		}catch(TimeoutException e2) {
			UtilsFunctions.explicitlyWait(driver, 5);
		}
	}

	
	@Test(priority = 0)
	public void register() {
		Register r = new Register();
		String reg_status = r.register("vamsi@12345", "vamsi@12345", "vamsi@12345", "kv", "Andhra Pradseh", "anantapur");
		//System.out.println(reg_status);
		//Assert.assertEquals("Register = Successful",reg_status);
		
		if(Objects.equals("Register = Successful", reg_status)) {
			System.out.println("New User Registered !!!");
		}else {
			System.out.println("User already exsists !!!");
		}

	}

	@Test(priority = 1)
	public void login() {
		Login l = new Login();
		String login_status = l.login("krish@123", "krish@123");
		System.out.println(login_status);
		Assert.assertEquals("Login - Successful", login_status);
	}

	
	
	@Test(priority = 2)
	public void searchFlight() {
		SearchFlight SF = new SearchFlight();
		String searchFlight_status = SF.searchFlight("Bangalore", "Chennai");
		System.out.println(searchFlight_status);
		Assert.assertEquals("Flights Available",searchFlight_status);
	}
	
	@Test(priority = 3)
	public void bookFlight() {
		BookFlight BF = new BookFlight();
		String booking_status = BF.bookFlight();
		System.out.println(booking_status);
		Assert.assertEquals("Booked Successfully !!! ",booking_status);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		System.out.println("\nAfter class --> Driver Closed");

	}
}

package com.libraries;

import static utils.DriverClass.driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;



public class Login {

	//WebDriver driver ;
	String URL  = "http://localhost:8080/FlyAway/login";
//	public  Login(WebDriver driver){
//		this.driver = driver ;
//
//	}
	By login = By.xpath("//a[starts-with(@href,'login')]");
	By username = By.name("email_id");
	By pwd = By.name("pwd");
	By login_button = By.xpath("//button[text()='Login']");
	
	public String login(String email, String password) {
		String status = "Login - Successful"; 
		
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        
	//	driver.get(URL);
		driver.findElement(login).click();
		driver.findElement(username).sendKeys(email);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(login_button).click();
		try {
			driver.findElement(By.linkText("Dashboard"));
		} catch(NoSuchElementException e){
			status = "Login - Failed";
		}
		return status;
	}
		
}

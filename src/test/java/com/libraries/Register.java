package com.libraries;

import static utils.DriverClass.driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class Register {

//	WebDriver driver ;
	String URL = "http://localhost:8080/FlyAway/signup";

//	public  Register(WebDriver driver){
//		this.driver = driver ;
//
//	}
	By email_path = By.name("email_id");
	By pwd_path = By.name("pwd");
	By confirm_pwd_path = By.name("pwd2");
	By name_path = By.name("name");
	By addr_path = By.name("address");
	By city_path = By.name("city");
	By signup_path = By.xpath("//button[text()='Signup']");
	
	public String register(String email,String pass,String con_pass,String name,String address,String city) {
		String status = "Register = Successful" ; 
		
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        
		driver.get(URL);
		driver.findElement(email_path).sendKeys(email);
		driver.findElement(pwd_path).sendKeys(pass);
		driver.findElement(confirm_pwd_path).sendKeys(con_pass);
		driver.findElement(name_path).sendKeys(name);
		driver.findElement(addr_path).sendKeys(address);
		driver.findElement(city_path).sendKeys(city);
		driver.findElement(signup_path).click();
		
		try {
			driver.findElement(By.linkText("Login to continue checking flights"));
		} catch(NoSuchElementException e){
			status = "Register = Failed";
		}
		return status;
		
	}
	
}



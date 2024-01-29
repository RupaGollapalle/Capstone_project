package com.restApi;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.response.Response;

public class ApiAndUi {

	public Response register(String email, String pwd, String confirm_pwd, String name, String address, String city) {

		RestAssured.baseURI = "http://localhost:8080/FlyAway";

		Response response = RestAssured.given().urlEncodingEnabled(false).formParam("email_id", email)
				.formParam("pwd", pwd).formParam("pwd2", confirm_pwd).formParam("name", name)
				.formParam("address", address).formParam("city", city)
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).when()
				.post("/signupaction");

		if (response.getStatusCode() == 302) {
			String redirectLocation = response.getHeader("Location");
			response = RestAssured.given().urlEncodingEnabled(false).get(redirectLocation);
		}

		return response;
	}
	
	



	By login = By.xpath("//a[starts-with(@href,'login')]");
	By username = By.name("email_id");
	By pwd = By.name("pwd");
	By login_button = By.xpath("//button[text()='Login']");
	
	public String login(String email, String password, WebDriver driver) {
		String status = "Login - Successful"; 
		
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        
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



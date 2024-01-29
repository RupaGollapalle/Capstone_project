package com.libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import static utils.DriverClass.driver;



public class SearchFlight {


//         WebDriver driver;
//        // String URL = "http://localhost:8080/FlyAway/home";
//	public  SearchFlight(WebDriver driver ){
//             this.driver = driver ;
//         }
	
	By home = By.xpath("//a[@href='home']");
	By source_path = By.xpath("//select[@name='source']") ;
	By dest_path = By.xpath("//select[@name='destination']") ;
	By button = By.xpath("//button") ;
	By bookflight_path = By.xpath("//a[starts-with(@href,'bookflight')]") ;
	
    public String searchFlight(String source, String destination ){

        String status = "Flights Available";
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        driver.get(URL);

        driver.findElement(home).click();
        WebElement from = driver.findElement(source_path);
        Select sel_from = new Select(from);
        sel_from.selectByVisibleText(source);
        WebElement to = driver.findElement(dest_path);
        Select sel_to = new Select(to);
        sel_to.selectByVisibleText(destination);

        driver.findElement(button).click();
        
        try{
            driver.findElement(By.xpath("//a[starts-with(@href,'bookflight')]"));
           // driver.findElement(By.xpath("//a[@href='completepurchase']")).click();
        } catch(NoSuchElementException e){
            status = "No Flights Available";
        }

        return status;
    }

}

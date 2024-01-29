package com.libraries;

import static utils.DriverClass.driver;



import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class BookFlight {
//    WebDriver driver;
//    public BookFlight(WebDriver driver){
//        this.driver = driver;
//    }

	By bookflight_path = By.xpath("//a[starts-with(@href,'bookflight')]");
	By completePurchase_path  = By.xpath("//a[starts-with(@href,'completepurchase')]");

    public String bookFlight(){
        String status = "Booked Successfully !!! ";
        try {
        	 driver.findElement(bookflight_path).click();
             driver.findElement(completePurchase_path).click();
        } catch(NoSuchElementException e){
            status = "Booking Failed !!!";
        }
        return status;
    }
}

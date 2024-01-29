package utils;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsFunctions {

		public static void explicitlyWait(WebDriver driver, By locator, int time) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} catch (TimeoutException e) {

				System.out.println("Element not found after waiting for " + time + " seconds");
//	            e.printStackTrace();
//	            System.out.println(e.getMessage());
			}

		}

		public static void explicitlyWait(WebDriver driver, int time) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		}

	}



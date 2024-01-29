package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass {

	public static WebDriver driver;

	public static WebDriver getWebDriver() {

		if (driver == null) {
			driver = startWebDriver();
		}
		return driver;
	}

	private static WebDriver startWebDriver() {
		System.out.println("started web driver");

		driver = new ChromeDriver();

		return driver;
	}
}

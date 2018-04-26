package com.naukri.updateProfile;

import org.testng.annotations.Test;

import com.naukri.pageObjects.nPOM;

import org.testng.annotations.BeforeTest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class nLogin extends nPOM {
	WebDriver driver;
	String URL = "https://www.naukri.com/";

	@Test
	public void login() {

		driver.get(URL);
		selectLogin();
		closeAllBrowsers();
		

		}
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}

}

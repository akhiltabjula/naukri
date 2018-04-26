package com.naukri.updateProfile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class nSample {
public static void main(String[] args) throws Exception {
	System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	// Pass application URL

	driver.get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
	JavascriptExecutor je = (JavascriptExecutor) driver;
	WebElement element = driver.findElement(By.xpath(".//*[@id='mCSB_3_container']/p[3]"));
	je.executeScript("arguments[0].scrollIntoView(true);",element);
	System.out.println(element.getText());

	
	
	
	
	}
}


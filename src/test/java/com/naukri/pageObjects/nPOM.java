package com.naukri.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.naukri.updateProfile.nCloseAllBrowsers;

public class nPOM extends nCloseAllBrowsers {

	WebDriver driver;

	

	public void selectLogin() {
		WebElement login = driver.findElement(By.xpath(".//*[@id='login_Layer']/div"));
		login.click();

	}

	public void credentials(String userName, String passWord) {
		WebElement user = driver.findElement(By.id("eLoginNew"));
		user.sendKeys(userName);

		WebElement pass = driver.findElement(By.id("pLogin"));
		pass.sendKeys(passWord);
	}
}

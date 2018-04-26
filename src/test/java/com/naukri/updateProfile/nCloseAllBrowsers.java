package com.naukri.updateProfile;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class nCloseAllBrowsers {
	WebDriver driver;

	@Test
	public void closeAllBrowsers() {
		/*System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/");*/
		String mainWindow = getMainWindowHandle();
		String windowTitle = getWindowTitle();
		
		Assert.assertTrue(closeAllOtherWindows(mainWindow));
		Assert.assertTrue(windowTitle.contains("Jobs - Recruitment"), "Main window title is not matching");
	}

	public String getMainWindowHandle() {
		return driver.getWindowHandle();
	}

	public String getWindowTitle() {
		String windowTitle = driver.getTitle();
		return windowTitle;
	}

	// close all other windows.
	public boolean closeAllOtherWindows(String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}

		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
}
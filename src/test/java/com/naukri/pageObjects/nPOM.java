package com.naukri.pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.naukri.updateProfile.nCloseAllBrowsers;

public class nPOM {

	WebDriver driver;
	nPOM object2 = new nPOM();
	
	@Test
	public  void closeAllBrowsers() {

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

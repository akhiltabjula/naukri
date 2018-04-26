package com.naukri.updateProfile;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class nTest {
	WebDriver driver;
	WebDriver wd;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void openURL() {
		driver.get("https://www.naukri.com/");
		WebDriverWait launchBrowserWait = new WebDriverWait(driver, 30);
		launchBrowserWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='login_Layer']/div")));
	}

	@Test(priority = 2)
	public void closeAllBrowsers() {

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

	@Test(priority = 3)
	public void selectLogin() throws Exception {
		WebElement login = driver.findElement(By.xpath(".//*[@id='login_Layer']/div"));
		login.click();
		WebDriverWait credentialsWait = new WebDriverWait(driver, 20);
		credentialsWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
			
		WebElement emailID = driver.findElement(By.name("email"));
		emailID.clear();
		emailID.sendKeys("tabjulaakhil@gmail.com");
				
		WebElement pass = driver.findElement(By.name("PASSWORD"));
		pass.clear();
		pass.sendKeys("1Tabjula!");
		Thread.sleep(4000);
		
		/*WebElement loginButton = driver.findElement(By.xpath(".//*[@id='lgnFrmNew']/div[9]/button"));
		loginButton.click();*/
		
			driver.findElement(By.xpath(".//*[@id='lgnFrmNew']/div[9]/button")).click();
			//driver.findElement(By.xpath(".//*[@id='lgnFrm']/div[8]/button")).click();
			
		
		
		Thread.sleep(10000);
		
		WebDriverWait homeProfileWait = new WebDriverWait(driver, 40);
		homeProfileWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='root']/div/span/div/div/div[2]/div[1]/div[2]/div[1]/div/div[1]/a")));
		
		Assert.assertEquals("Home | Mynaukri", driver.getTitle());
	}

	@Test(priority = 4)
	public void selectProfile(){
		WebElement updateProfileButton = driver.findElement(By.xpath(".//*[@id='root']/div/span/div/div/div[2]/div[1]/div[2]/div[1]/div/div[1]/a"));
		updateProfileButton.click();
		Assert.assertEquals("Profile | Mynaukri", driver.getTitle());
	}
	
	@Test(priority = 5)
	public void uploadFile() throws Exception {
		driver.navigate().refresh();
		/*WebElement deleteResume = driver.findElement(By.xpath(".//*[@id='lazyAttachCV']/div/div/div[2]/div[1]/div[2]/div/a"));
		js.executeScript("arguments[0].scrollIntoView(true);" , deleteResume);*/
		js.executeScript("scroll(0,400)");
		Thread.sleep(10000);
	}
	
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}

}

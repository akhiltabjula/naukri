package com.naukri.updateProfile;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class nTestNew {
	WebDriver driver;
	JavascriptExecutor jse = (JavascriptExecutor) driver;

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
		pass.sendKeys("123456789");

		WebDriverWait loginButtonWait = new WebDriverWait(driver, 10);
		loginButtonWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='lgnFrm']/div[8]/button")));

		// For old form
		/*
		 WebElement loginButton = driver.findElement(By.xpath(".//*[@id='lgnFrm']/div[8]/button"));
		 loginButton.click();
		 */
		
		// For new form
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='lgnFrmNew']/div[9]/button"));
		loginButton.click();

		// Wait till naukri home page loads
		WebDriverWait homeProfileWait = new WebDriverWait(driver, 40);
		homeProfileWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(".//*[@id='root']/div/span/div/div/div[2]/div[1]/div[2]/div[1]/div/div[1]/a")));

		// Assert function to verify whether home page is loaded or not
		Assert.assertEquals("Home | Mynaukri", driver.getTitle());
	}

	@Test(priority = 4)
	public void selectProfile() throws InterruptedException {
		// Selecting profile button link to redirect to profile page
		WebElement updateProfileButton = driver
				.findElement(By.xpath(".//*[@id='root']/div/span/div/div/div[2]/div[1]/div[2]/div[1]/div/div[1]/a"));
		updateProfileButton.click();

		// Assert function to verify whether profile page is loaded or not
		Assert.assertEquals("Profile | Mynaukri", driver.getTitle());
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void deleteResume() throws Exception {
		// wait method to load Attach Resume option.
		WebElement attachResumeWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(".//*[@id='root']/div/span/div/div/div/div/div[2]/div[1]/div/div/ul/li[11]")));

		// selecting Attach resume option
		WebElement attachResumeLink = driver
				.findElement(By.xpath(".//*[@id='root']/div/span/div/div/div/div/div[2]/div[1]/div/div/ul/li[11]"));
		attachResumeLink.click();

		WebElement deleteResumeWait = (new WebDriverWait(driver, 15)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(".//*[@id='lazyAttachCV']/div/div/div[2]/div[1]/div[2]/div/a")));

		WebElement deleteResumeButton = driver
				.findElement(By.xpath("//*[@id='lazyAttachCV']/div/div/div[2]/div[1]/div[2]/div/a"));
		deleteResumeButton.click();

		Thread.sleep(4000);

		// delete resume button
		WebElement deleteResumeConfirm = driver.findElement(By.xpath("html/body/div[5]/div[9]/div/div/button"));
		deleteResumeConfirm.click();
		Thread.sleep(2000);

		// Resume deleted message assertion.
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='attachCVMsgBox']/div/div/div/p[2]")).getText(),
				"Attached Resume has been successfully deleted.");

		Thread.sleep(4000);
	}

	@Test(priority = 6)
	public void uploadResume() {
		WebElement uploadResumeWait = (new WebDriverWait(driver, 15))
				.until((ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='attachCV']"))));
		// upload file through send keys
		WebElement uploadFileButton = driver.findElement(By.xpath(".//*[@id='attachCV']"));
		uploadFileButton.sendKeys("C:\\Users\\tabju\\Downloads\\AkhilTabjula_V2.1.docx");
		WebElement uploadSuccessWait = (new WebDriverWait(driver, 15)).until(
				(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='attachCVMsgBox']/div/div/div/p[2]"))));
		// Resume uploaded message assertion
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='attachCVMsgBox']/div/div/div/p[2]")).getText(),
				"Resume has been successfully uploaded.");
	}

	@Test(priority = 7)
	public void logout() {
		// creating actions class for logout option in MyNaukri mouse over menu
		Actions logoutMouseOver = new Actions(driver);

		WebElement logoutMenu = driver.findElement(By.xpath("/html/body/div[1]/div/div/ul[2]/li[2]/a"));
		// building for mouse over action
		logoutMouseOver.moveToElement(logoutMenu).build().perform();

		// selecting logout option
		WebElement logoutButton = driver
				.findElement(By.xpath("/html/body/div[1]/div/div/ul[2]/li[2]/div/ul[2]/li[5]/a"));
		logoutButton.click();
		Assert.assertEquals(driver.getTitle(), "My Naukri");

	}

	@AfterTest()
	public void tearDown() {
		driver.quit();
	}

}

package com.automationpractice;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.apache.commons.*;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAutomation {

	WebDriver driver;
	String url = "http://automationpractice.com/index.php";

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\QATests\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(enabled = false)
	public void title() {
		driver.get(url);
		AssertJUnit.assertEquals("My Store", driver.getTitle());
		System.out.println(driver.getTitle());
	}

	@Test(enabled = false)
	public void textTest() {
		driver.get(url);
		WebElement text = driver.findElement(By.cssSelector("#editorial_block_center > h2"));
		Assert.assertEquals("Practice Selenium", text.getText());
	}

	@Test(enabled=false)
	public void cartClick() {
		driver.get(url);
		WebElement cart = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a"));
		cart.click();
		WebElement text = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));
		boolean flag = text.isDisplayed();
		if (flag) {
			AssertJUnit.assertEquals("Your shopping cart is empty.", text.getText());
		} else {
			System.out.println("Error");
		}

	}
	@Test(enabled=false)
	public void search() {
		driver.get(url);
		
		WebElement textArea = driver.findElement(By.name("search_query"));
		WebElement searchButton = driver.findElement(By.name("submit_search"));
		
		Actions act = new Actions(driver);
		Actions build = act.sendKeys(textArea, "shoes");
		build.perform();
		searchButton.click();
		
		WebElement text = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]"));
		if(text.isDisplayed()) {
			Assert.assertEquals("7 results have been found.", text.getText());
			System.out.println("---WELL DONE---");
		} else {
			System.out.println("Error");
		}
	}
	
	@Test(enabled=false)
	public void signIn() {
		driver.get(url);
		String email = "stevanchez7@gmail.com";
		//click on Sign in nad asserting text
		WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		signInButton.click();
		WebElement text1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1"));
		String auth = text1.getText();
		Assert.assertEquals("authentication" ,auth.toLowerCase());
		//creating an email 
		WebElement accountArea = driver.findElement(By.id("email_create"));
		Actions act = new Actions(driver);
		Actions build = act.sendKeys(accountArea, email);
		build.perform();
		//click on create
		WebElement clickCreate = driver.findElement(By.name("SubmitCreate"));
		clickCreate.click();
		//asserting text
		WebElement text2 = driver.findElement(By.xpath("//*[@id=\"account-creation_form\"]/div[1]/h3"));
		Assert.assertEquals("your personal information", text2.getText().toLowerCase());
		Assert.assertTrue(text2.isDisplayed());
		
	}
	@Test(enabled=false)
	public void contactUs() {
		driver.get(url);
		String email = "stevanchez7@gmail.com";
		String reference = "#123456";
		String message = "POZDRAV!";
		
		//click on the Contact US button
		WebElement clickContactUs = driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a"));
		clickContactUs.click();
		
		//NEXT PAGE
		//Select Subject Heading from the drop-down button
		Select dropDown = new Select(driver.findElement(By.id("id_contact")));
		dropDown.selectByIndex(1);
		
		//write an email
		WebElement emailArea = driver.findElement(By.id("email"));
		Actions act = new Actions(driver);
		Actions build = act.sendKeys(emailArea, email);
		build.perform();
		
		//write the reference
		WebElement orderReference = driver.findElement(By.id("id_order"));
		Actions act2 = new Actions(driver);
		Actions build2 = act2.sendKeys(orderReference, reference);
		build2.perform();
		
		//write message
		WebElement messageArea = driver.findElement(By.id("message"));
		Actions act3 = new Actions(driver);
		Actions build3 = act3.sendKeys(messageArea, message);
		build3.perform();
		
		//SEND
		WebElement sendButton = driver.findElement(By.id("submitMessage"));
		sendButton.click();
		
		//Asserting
		WebElement text = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));
		if(text.isDisplayed()) {
			AssertJUnit.assertEquals("Your message has been successfully sent to our team.", text.getText());
		} else {
			System.out.println("ERROR WHILE CONTACTING");
		}
		
	}
	
	@Test(enabled=false)
	public void newTab() {
		driver.get(url);
		
		// opening new tab
		WebElement newTab = driver.findElement(By.cssSelector("body"));
		newTab.sendKeys(Keys.CONTROL + "t");
		((JavascriptExecutor)driver).executeScript("window.open()");
		//go to google.com
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.google.com/");
		//Send certain keys and click search
		WebElement textArea = driver.findElement(By.name("q"));
		WebElement searchButton = driver.findElement(By.name("btnK"));
		Actions act = new Actions(driver);
		Actions build = act.sendKeys(textArea, "Aleksa");
		build.perform();
		searchButton.click();
		//Assert if page is displayed
		WebElement body = driver.findElement(By.xpath("//*[@id=\"gsr\"]"));
		Assert.assertTrue(body.isDisplayed());
		//back to initial page
		driver.switchTo().window(tabs.get(0));
		driver.get(url);
		//Assert if old page is displayed
		WebElement oldTab = driver.findElement(By.xpath("//*[@id=\"index\"]"));
		Assert.assertTrue(oldTab.isDisplayed());
	
	}
	@Test(enabled=false)
	public void screenshot()  {
		driver.get(url);
		//Taking Screenshot
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(file,new File("C:\\Users\\Aleksa\\Desktop\\QA\\image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void screenshotElement() throws IOException {
		driver.get(url);
		
		// Finding particular logo
		WebElement logo = driver.findElement(By.xpath("//*[@id=\"htmlcontent_top\"]/ul/li[1]/a/img"));
		
		//Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		
		//Get the location of the element 
		Point point = logo.getLocation();
		
		//Get width and height of the element
		int logoWidth = logo.getSize().getWidth();
		int logoHeight = logo.getSize().getHeight();
		
		//Crop the element from the page to get the exact screenshot
		BufferedImage logoScreenshot = fullImg.getSubimage(point.getX(), point.getY(), logoWidth, logoHeight);
 		ImageIO.write(logoScreenshot, "png", screenshot);
 		
 		//Copy the screenshot to the disc
 		File screenshotLocation = new File("C:\\Users\\Aleksa\\Desktop\\QA\\logo.png");
 		FileHandler.copy(screenshot, screenshotLocation);
		
	}
	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}
}

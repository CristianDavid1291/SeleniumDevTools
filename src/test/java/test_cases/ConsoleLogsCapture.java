package test_cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConsoleLogsCapture {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		
		//listeners - OnTestFailure
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Browse Products")).click();
		driver.findElement(By.linkText("Selenium")).click();
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Cart")).click();
		driver.findElement(By.xpath("(//input[@id='exampleInputEmail1'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@id='exampleInputEmail1'])[1]")).sendKeys("2");
		
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
		List<LogEntry> logs = entry.getAll();
		
		for(LogEntry e: logs) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	
}

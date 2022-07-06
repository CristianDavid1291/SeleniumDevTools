package test_cases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;


import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileEmulatorTest {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		Map deviceMetrics = new HashMap()
        {{
            put("width", 600);
            put("height", 1000);
            put("mobile", true);
            put("deviceScaleFactor", 50);
        }};
		
		//send commands to CDP methods(Chrome Dev Protocol)
	 	driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	 	driver.get("https://rahulshettyacademy.com/angularAppdemo/");
	 	driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
	 	Thread.sleep(3000);
	 	driver.findElement(By.linkText("Library")).click();
	}
}

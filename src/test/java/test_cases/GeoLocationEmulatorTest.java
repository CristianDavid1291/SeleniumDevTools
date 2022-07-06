package test_cases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GeoLocationEmulatorTest {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
		Map<String,Object> geoLocationMetric = new HashMap<String,Object>();
		
		geoLocationMetric.put("latitude", 23);
		geoLocationMetric.put("longitude", 120);
		geoLocationMetric.put("accuracy", 1);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", geoLocationMetric);
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
	}
	
}

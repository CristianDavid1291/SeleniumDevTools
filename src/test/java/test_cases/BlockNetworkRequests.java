package test_cases;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.network.Network;

import com.google.common.collect.ImmutableList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BlockNetworkRequests {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
		
		//Enable capturing network traffic
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		//Block specific traffic with regular expression 
		devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.close();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		
		
		
	}
	

}

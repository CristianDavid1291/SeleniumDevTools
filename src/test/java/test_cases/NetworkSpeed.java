package test_cases;



import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.network.Network;
import org.openqa.selenium.devtools.v100.network.model.ConnectionType;
import org.openqa.selenium.devtools.v100.network.model.LoadingFailed;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkSpeed {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		//Enable network
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//waits, latency, connectionType: cellular 2g, 3g, ethernet, wifi
		devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000,Optional.of(ConnectionType.ETHERNET)));
		
		//verifying failed connection
		devTools.addListener(Network.loadingFailed(), LoadingFailed ->
		{
		
			System.out.println(LoadingFailed.getErrorText());
			System.out.println(LoadingFailed.getTimestamp());
			
			
		});
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink='/library']")).click();
		driver.close();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		
	}

}

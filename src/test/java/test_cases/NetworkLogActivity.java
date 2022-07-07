package test_cases;


import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.network.Network;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkLogActivity {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// log file ->

		DevTools devTools = driver.getDevTools();
		devTools.createSession();
	
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));


		devTools.addListener(Network.requestWillBeSent(), request ->
		{
			System.out.println(request.getRequest().getUrl());
		});
		
		// Event will get fired
		devTools.addListener(Network.responseReceived(), response -> {
			System.out.println(response.getResponse().getUrl()+"--Status Code: "+ response.getResponse().getStatus());
			
			
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink='/library']")).click();
		driver.quit();
		
	}

}

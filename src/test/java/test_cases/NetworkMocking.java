package test_cases;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.fetch.Fetch;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Chrome dev tools protocol fetch enable to listen requests
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

		// This method allow us pause all of requests and work with them
		devTools.addListener(Fetch.requestPaused(), request -> {

			//We are looking for specific request 
			if (request.getRequest().getUrl().contains("=shetty"))
			{
				String newRequest = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				
				
				//after modify url's request we need to send it like normal flow
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newRequest),
						Optional.of(request.getRequest().getMethod()), Optional.empty(),
						Optional.empty(), Optional.empty()));
			}
			else
			{
				//we need to send the other request different from we are looking for 
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(),
						Optional.empty(), Optional.empty()));
			}
			

			
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink='/library']")).click();
		
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.cssSelector("p")).getText());
		
		driver.close();

	}

}

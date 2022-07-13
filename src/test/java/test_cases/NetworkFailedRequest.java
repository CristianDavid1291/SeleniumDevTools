package test_cases;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v100.fetch.Fetch;
import org.openqa.selenium.devtools.v100.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v100.network.model.ErrorReason;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkFailedRequest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Defining patterns of request and save them in a list
		Optional<List<RequestPattern>> patterns = Optional
				.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));

		devTools.send(Fetch.enable(patterns, Optional.empty()));

		// Listener to paused when our application receive the request

		devTools.addListener(Fetch.requestPaused(), request ->

		{
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
			devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
					Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
			
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink='/library']")).click();

	}

}

package html_headless_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

public class HeadlessMode_Using_ChromeOptions {


	@Test
	public void headlessScriptTest() {
		
		/* headless in Chrome browser*/
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless=new");
		WebDriver driver =new ChromeDriver(option);
		driver.get("https://www.google.com/");
		System.out.println("===RUN "+driver.getTitle());
		
		
		/*headless in FireFox browser ==
		
		FirefoxOptions foption = new FirefoxOptions();
		foption.addArguments("--headless=new");
		WebDriver driver =new FirefoxDriver(foption);
		driver.get("https://www.google.com/");
		System.out.println("===RUN "+driver.getTitle());*/
		
		
		/*headless in Edge broser
		 * EdgeOptions eoption = new EdgeOptions();
		 * WebDriver driver = new EdgeDriver(options);
		 * options.setCapability("ms:edgeOptions", "{\"headless\":true}");
		 * */
		
		/* Benefits of headless automation
		 * 1. Speed
		 * 2. Continious integration
		 * 3. Scalability
		 * 4. Platoform independency*/
		
		
	}
	
	
}

package StressTesting;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class seleniumGrid<DesireCapabilities> {
	@Test

	public void regular() {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("chrome");
//		caps.setPlatform(null);

		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.8.173:4444"), caps);
		driver.get("");
		ArrayList<String> names = new ArrayList<String>();
		names.add("Pavan");
		names.add("Prakash");
		names.add("Ram");
		names.add("Hanuman");
		names.add("Rashtrapal");
		names.add("Pankaj");
		int count = 0;
		
		for (int i = 0; i < names.size(); i++) {
			String nam = names.get(i);

			if (nam.startsWith("P")) {
				count++;
			}
		}

		System.out.println(count);
	}

}

package StressTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginTask implements Runnable {
	private final String username;
	private final String password;

	public LoginTask(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void run() {
		WebDriver driver = new ChromeDriver();

		try {
			login(driver, username, password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private void login(WebDriver driver, String agentUsername, String agentPassword) throws InterruptedException {
		driver.get("https://qauatoldui.slashrtc.in");

		WebElement usernameInput = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

		usernameInput.sendKeys(agentUsername);
		passwordInput.sendKeys(agentPassword);

		loginButton.click();
		Thread.sleep(500);
		
		 
	}
}

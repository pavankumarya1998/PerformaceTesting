package StressTesting;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class LoadTest {
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		String excelFilePath = "C:\\Users\\pavan\\Downloads\\downloadUploadInvalidUsers (6).xlsx";

		try (FileInputStream fis = new FileInputStream(excelFilePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			int loginCount = 0;

			for (Row row : sheet) {
				if (loginCount >= 20) {
					break;
				}

				Cell usernameCell = row.getCell(0);
				Cell passwordCell = row.getCell(1);

				if (usernameCell != null && passwordCell != null) {
					String agentUsername = usernameCell.getStringCellValue();
					String agentPassword = passwordCell.getStringCellValue();

					login(driver, agentUsername, agentPassword);
					loginCount++;

					Thread.sleep(1000); // Optional: Add a delay between logins
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private static void login(WebDriver driver, String agentUsername, String agentPassword)
			throws InterruptedException {

		driver.get("https://qauatoldui.slashrtc.in/");

		WebElement usernameInput = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

		usernameInput.sendKeys(agentUsername);
		passwordInput.sendKeys(agentPassword);

		loginButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement userConfirmation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='thridTabScreenAgentInfo']")));
		userConfirmation.getText();
		Thread.sleep(500); // Wait for login to process

	}
}

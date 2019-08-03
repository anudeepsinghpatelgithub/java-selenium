package my.selenium.test.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import my.selenium.test.utils.Constants;

public class BaseClass {
	protected WebDriver driver;

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setUpDriver(String browser) {
		try {
			if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
				driver = new FirefoxDriver();
			} else {
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
				driver = new ChromeDriver(options);
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {
			driver.quit();
		}
	}
}

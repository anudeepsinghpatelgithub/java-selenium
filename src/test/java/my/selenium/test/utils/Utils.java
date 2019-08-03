package my.selenium.test.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class Utils {

	public static void waitForPageLoad(WebDriver driver) {
		new WebDriverWait(driver, Constants.PAGE_LOAD_TIMEOUT).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver)
						.executeScript("return document.readyState").equals("complete");
			}
		});
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}

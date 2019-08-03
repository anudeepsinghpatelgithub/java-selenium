package my.selenium.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import my.selenium.test.utils.Constants;
import my.selenium.test.utils.Utils;

public class WHProfileReviewPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public WHProfileReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}

	@FindBy(xpath = "//div[@class='reviews']")
	public WebElement reviewsContent;

	public WHProfileReviewPage clickOnReviewTab(String reviewsUrl) {

		String reviewsTab = "//a[@href='" + reviewsUrl + "']";
		WebElement reviewsTabWeb = driver.findElement(By.xpath(reviewsTab));
		reviewsTabWeb.click();
		Utils.waitForPageLoad(driver);
		wait.until(ExpectedConditions.visibilityOf(reviewsContent));
		return this;
	}

	public void verifyReviewInReviewPage(String content, String reviewsUrl) {
		
		this.clickOnReviewTab(reviewsUrl);
		String reviewContent = "//p[contains(text(),'" + content + "')]";
		// Sometime i have observed that reviews are not displayed so adding retry logic
		// to try 3 times before marking test
		// This is temporary solution, looks like application has some issues.

		int retry = 1;
		boolean reviewFound = false;
		while (retry <= 3) {
			if (driver.findElements(By.xpath(reviewContent)).size() > 0) {
				WebElement reviewContentWeb = driver.findElement(By.xpath(reviewContent));
				Assert.assertEquals(reviewContentWeb.isDisplayed(), true, "Review should be displayed in review page");
				reviewFound=true;
				break;
			} else {
				System.out.println("Attempt:"+retry+", Review is not displayed... hence refreshing the page and waiting for 2 sec and again checking");
				this.driver.navigate().refresh();
				Utils.waitForPageLoad(driver);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.clickOnReviewTab(reviewsUrl);
				retry++;
			}
		}
		if(!reviewFound) {
			Assert.assertEquals(reviewFound, true, "Review not found in review page after 3 attempts");
		}
		
	}
}

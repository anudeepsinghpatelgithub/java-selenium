package my.selenium.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.selenium.test.utils.Constants;
import my.selenium.test.utils.Utils;

public class WHCompanyPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public WHCompanyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}
	
	@FindBy(xpath="(//span[@class='dropdown-placeholder'])[2]")
	public WebElement selectDropDown;
	
	@FindBy(xpath="//textarea[@placeholder='Write your review...']")
	public WebElement reviewTextArea;
	
	@FindBy(xpath="//div[text()='Submit']")
	public WebElement submitButton;
		
	public void clickOnratingStar(int ratingNumber) {
		try {
		String ratings="//review-star[@class='rvs-svg']/descendant::*[name()='svg']["+ratingNumber+"]";
		WebElement ratingsWeb= driver.findElement(By.xpath(ratings));
		wait.until(ExpectedConditions.visibilityOf(ratingsWeb));
		Utils.scrollToElement(driver, ratingsWeb);
		
		Actions builder = new Actions(driver);
		builder.moveToElement(ratingsWeb).perform();
		Thread.sleep(2000);
		builder.moveToElement(ratingsWeb).click().build().perform();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public WHLoginPage writeReview(String category, String content) {
		
		wait.until(ExpectedConditions.visibilityOf(selectDropDown));
		selectDropDown.click();
		
		String categoryXpath="//li[text()='"+category+"']";
		WebElement categoryWeb = driver.findElement(By.xpath(categoryXpath));
		wait.until(ExpectedConditions.visibilityOf(categoryWeb));
		categoryWeb.click();
		
		reviewTextArea.clear();
		reviewTextArea.sendKeys(content);
		
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		return new WHLoginPage(driver);
	}
}

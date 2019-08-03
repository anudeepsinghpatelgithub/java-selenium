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

public class FBHomePage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	public FBHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}

	public String postAreaXpath="//textarea[contains(@title,'Write something here..')]";
	@FindBy(xpath="//textarea[contains(@title,'Write something here..')]")
	public WebElement postArea;
	
	public String postContentXpath="//div[@contenteditable='true' and contains(@aria-label,'Write something here...')]";
	@FindBy(xpath="//div[@contenteditable='true' and contains(@aria-label,'Write something here...')]")
	public WebElement postContent;
	
	@FindBy(xpath="//span[text()='Post']/parent::button")
	public WebElement postButton;
	
	public void postStatus(String status) {
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(postAreaXpath)));
		wait.until(ExpectedConditions.visibilityOf(postArea));
		postArea.click();
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(postContentXpath)));
		wait.until(ExpectedConditions.visibilityOf(postContent));
		postContent.sendKeys(status);
		
		wait.until(ExpectedConditions.elementToBeClickable(postButton));
		postButton.click();
		
		Utils.waitForPageLoad(this.driver);
		String postedMsgXpath="//*[@*='post_message']/descendant::p[contains(text(),'"+status+"')]";
		WebElement postedMsg= this.driver.findElement(By.xpath(postedMsgXpath));
		Assert.assertEquals(postedMsg.isDisplayed(), true, "Posted message should be displayed in facebook wall");
		
	}
}

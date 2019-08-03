package my.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.selenium.test.utils.Constants;
import my.selenium.test.utils.Utils;

public class WHHeaderPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public WHHeaderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}
	
	@FindBy(xpath="//span[text()='Login']")
	public WebElement loginLink;
	
	public WHLoginPage clickOnLoginLink(){
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		loginLink.click();		
		return new WHLoginPage(this.driver);
	}
}

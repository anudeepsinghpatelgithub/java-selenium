package my.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.selenium.test.utils.Constants;
import my.selenium.test.utils.Utils;

public class WHLoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public WHLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}
	
	@FindBy(name="em")
	public WebElement emailEl;
	
	@FindBy(name="pw1")
	public WebElement passwordEl;
	
	@FindBy(xpath="//span[text()='Login']")
	public WebElement loginButton;
	
	@FindBy(xpath="//a[text()='Login']")
	public WebElement loginLink;
	
	public WHLoginPage clickOnLoginTab() {
		wait.until(ExpectedConditions.visibilityOf(loginLink));
		loginLink.click();
		return this;
	}
	
	public void login(String email,String password) {
		
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		
		wait.until(ExpectedConditions.visibilityOf(emailEl));
		emailEl.clear();
		emailEl.sendKeys(email);
		
		wait.until(ExpectedConditions.visibilityOf(passwordEl));
		passwordEl.clear();
		passwordEl.sendKeys(password);
		
		loginButton.click();
		Utils.waitForPageLoad(driver);
	}
	
}

package my.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.selenium.test.utils.Constants;
import my.selenium.test.utils.Utils;

public class FBLoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public FBLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(this.driver, Constants.TIMEOUT);
		Utils.waitForPageLoad(this.driver);
	}

	@FindBy(id = "email")
	public WebElement emailInput;

	@FindBy(id = "pass")
	public WebElement passwordInput;

	@FindBy(xpath = "//input[@value='Log In']")
	public WebElement loginButton;

	public FBHomePage login(String email, String password) {

		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.clear();
		emailInput.sendKeys(email);

		wait.until(ExpectedConditions.visibilityOf(passwordInput));
		passwordInput.clear();
		passwordInput.sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();

		return new FBHomePage(this.driver);
	}
}

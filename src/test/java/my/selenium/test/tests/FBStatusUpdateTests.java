package my.selenium.test.tests;

import java.util.Date;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import my.selenium.test.base.BaseClass;
import my.selenium.test.pages.FBLoginPage;
import my.selenium.test.utils.Constants;

public class FBStatusUpdateTests extends BaseClass {

	String fbEmail;
	String fbPassword;

	@Parameters({ "fbEmail", "fbPassword" })
	@BeforeClass(alwaysRun = true)
	public void setup(String fbEmail, String fbPassword) {
		if (fbEmail.isEmpty() || fbPassword.isEmpty()) {
			System.err.println("Email and password parameters need to be provided in resources/TestNg.xml file");
			driver.quit();
			throw new SkipException("Email and password parameters need to be provided in resources/TestNg.xml file");
		}
		this.fbEmail = fbEmail;
		this.fbPassword = fbPassword;

	}

	@BeforeMethod(alwaysRun = true)
	public void setupTest() {
		driver.get(Constants.FACEBOOK_URL);
	}

	@Test(groups = "postStatus", description = "User should be able to post status message in facebook wall")
	public void postStatusInFbWall() {
		FBLoginPage loginPage = new FBLoginPage(driver);
		String status = "Hello World" + new Date();
		loginPage.login(fbEmail, fbPassword).postStatus(status);
	}

}

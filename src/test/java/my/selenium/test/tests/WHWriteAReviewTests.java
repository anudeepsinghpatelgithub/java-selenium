package my.selenium.test.tests;

import java.util.Date;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import my.selenium.test.base.BaseClass;
import my.selenium.test.pages.WHCompanyPage;
import my.selenium.test.pages.WHProfileReviewPage;
import my.selenium.test.utils.Constants;

public class WHWriteAReviewTests extends BaseClass {

	String whEmail;
	String whPassword;
	String whUserName;

	@Parameters({ "whEmail", "whPassword", "whUserName" })
	@BeforeClass(alwaysRun = true)
	public void setup(String whEmail, String whPassword, String whUserName) {
		if (whEmail.isEmpty() || whPassword.isEmpty() || whUserName.isEmpty()) {
			System.err.println(
					"Email,whUserName and password parameters need to be provided in resources/TestNg.xml file");
			driver.quit();
			throw new SkipException("Email,whUserName and password parameters need to be provided in resources/TestNg.xml file");
		}
		this.whEmail = whEmail;
		this.whPassword = whPassword;
		this.whUserName = whUserName;
	}

	@BeforeMethod(alwaysRun = true)
	public void setupTest() {
		driver.get(Constants.WH_BASE_URL + Constants.WH_COMPANY_URL);
	}

	@Test(groups = "writeReview", description = "User should be able to write a review with ratings and review should be displayed in profile/reviews page")
	public void writeReviewForCompanyAndVerify() {

		WHCompanyPage whCompanyPage = new WHCompanyPage(driver);
		whCompanyPage.clickOnratingStar(5);
		String category = "Health Insurance";
		String content = new Date().getTime()
				+ " On the right part of the page, hover over the stars and click on the fourth star. Your code should actually (a) do the hover and (2) make sure the stars inside get lit up when you hover over them, then (3) click on the fifth star. Simply redirecting the WebDriver to the next page isn’t an option.";
		whCompanyPage.writeReview(category, content).clickOnLoginTab().login(whEmail, whPassword);
		// Go to user review page
		String reviewsUrl = "/profile/" + whUserName + "/reviews/";
		driver.get(Constants.WH_BASE_URL + reviewsUrl);
		// Verify review displayed
		new WHProfileReviewPage(driver).verifyReviewInReviewPage(content, reviewsUrl);
	}
}

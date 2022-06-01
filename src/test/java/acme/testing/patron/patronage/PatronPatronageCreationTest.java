package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class PatronPatronageCreationTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legislation, final String budget,final String startDate, final String finishDate, final String link, final String inventorId) {

		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage");
		super.clickOnButton("Create");
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legislation", legislation);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"inventorId_proxy\"]/option[" + inventorId + "]")).click();

		super.clickOnSubmit("Create");

		super.clickOnMenu("Patron", "Patronage");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, legislation);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legislation", legislation);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
	@Order(20)
	public void negativeTest(final int recordIndex, final String status, final String code, final String legislation, final String budget, final String startDate, final String finishDate, final String link, final String inventorId) {

		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage");
		super.clickOnButton("Create");
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legislation", legislation);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"inventorId_proxy\"]/option[" + inventorId + "]")).click();

		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

}

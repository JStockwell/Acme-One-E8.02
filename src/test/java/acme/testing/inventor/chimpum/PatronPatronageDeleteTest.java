package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class PatronPatronageDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
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
		
		super.clickOnListingRecord(0);

		super.clickOnSubmit("Delete");

		super.signOut();
	}


}

package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronagePublishTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legislation, final String budget,final String startDate, final String finishDate, final String link, final String inventorId) {

		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage");

		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(0);

		super.checkFormExists();

		super.clickOnSubmit("Publish");

		super.clickOnMenu("Patron", "Patronage");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, legislation);
		super.checkColumnHasValue(recordIndex, 3, budget);

		super.clickOnListingRecord(0);

		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legislation", legislation);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

}
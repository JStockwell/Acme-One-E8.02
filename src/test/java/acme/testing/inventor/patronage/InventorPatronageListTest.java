package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/inventor/patronage/list.csv",encoding="utf-8",numLinesToSkip=1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String legislation, final String budget, final String status, final String creationDate, final String startDate, final String finishDate, final String link, final String patronCompany, final String patronStatement, final String patronLink) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Patronage");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, legislation);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, status);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legislation", legislation);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronCompany", patronCompany);
		super.checkInputBoxHasValue("patronStatement", patronStatement);
		super.checkInputBoxHasValue("patronLink", patronLink);

		super.signOut();
	}
	
}

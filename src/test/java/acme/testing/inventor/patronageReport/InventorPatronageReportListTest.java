package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/inventor/patronageReport/list.csv",encoding="utf-8",numLinesToSkip=1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String creationDate, final String memorandum, final String link, final String patronageCode) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Patronage reports");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, creationDate);
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		super.checkColumnHasValue(recordIndex, 2, patronageCode);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("creation", creationDate);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronage", patronageCode);

		super.signOut();
	}
	
}

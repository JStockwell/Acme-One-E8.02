package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/inventor/chimpum/list.csv",encoding="utf-8",numLinesToSkip=1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String creationMoment, final String title, final String description, final String startDate, final String finishDate, final String budget, final String link, final String item, final String inventor) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My chimpums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, budget);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("itemName", item);
		super.checkInputBoxHasValue("inventorName", inventor);

		super.signOut();
	}
	
}

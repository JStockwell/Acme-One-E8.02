package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageCreationTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String status, final String legislation, final String budget, final String creationDate, final String startDate, final String finishDate, final String link, final String inventorName, final String inventorEmail, final String inventorCompany, final String inventorStatement, final String inventorLink) {

		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage");
		super.clickOnSubmit("Create");
		super.checkFormExists();
		
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legislation", legislation);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);

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

//	@ParameterizedTest
//	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(20)
//	public void negativeTest(final int recordIndex, final String name, final String description, final String code, final String technology, final String price, final String link, final String itemType) {
//
//		super.signIn("inventor1", "inventor1");
//
//		super.clickOnMenu("Inventor", "Create item");
//		super.checkFormExists();
//		
//		super.fillInputBoxIn("name", name);
//		super.fillInputBoxIn("description", description);
//		super.fillInputBoxIn("code", code);
//		super.fillInputBoxIn("technology", technology);
//		super.fillInputBoxIn("price", price);
//		super.fillInputBoxIn("link", link);
//		super.fillInputBoxIn("itemType", itemType);
//
//		super.clickOnSubmit("Create");
//
//		super.checkErrorsExist();
//
//		super.signOut();
//	}

}

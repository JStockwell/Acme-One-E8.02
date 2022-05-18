package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemPublishTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String description, final String code, final String technology, final String price, final String link, final String itemType) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "Create item");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", "yyyyyyy");
		super.fillInputBoxIn("description", "fasdfasdf");
		super.fillInputBoxIn("code", "HDJ-001");
		super.fillInputBoxIn("technology", "ea");
		super.fillInputBoxIn("price", "EUR 5");
		super.fillInputBoxIn("link", "");
		super.fillInputBoxIn("itemType", "TOOL");

		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "My items");

		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(2);

		super.checkFormExists();

		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("price", price);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("itemType", itemType);

		super.clickOnSubmit("Publish");

		super.clickOnMenu("Inventor", "My items");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(2, 0, name);
		super.checkColumnHasValue(2, 1, technology);
		super.checkColumnHasValue(2, 2, price);
		super.checkColumnHasValue(2, 3, itemType);

		super.clickOnListingRecord(2);

		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("itemType", itemType);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String name, final String description, final String code, final String technology, final String price, final String link, final String itemType) {
		
		super.signIn("inventor1", "inventor1");
		
		if(recordIndex==0) {
			this.crearItem();
		}
		super.clickOnMenu("Inventor", "My items");

		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);

		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("price", price);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("itemType", itemType);

		super.clickOnSubmit("Publish");

		super.checkErrorsExist();

		super.signOut();
	}
	
	private void crearItem() {
		super.clickOnMenu("Inventor", "Create item");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", "aaaaaaa");
		super.fillInputBoxIn("description", "aaaaaaa");
		super.fillInputBoxIn("code", "PBS-001");
		super.fillInputBoxIn("technology", "aaaaaa");
		super.fillInputBoxIn("price", "EUR 7");
		super.fillInputBoxIn("link", "");
		super.fillInputBoxIn("itemType", "COMPONENT");

		super.clickOnSubmit("Create");
	}

}
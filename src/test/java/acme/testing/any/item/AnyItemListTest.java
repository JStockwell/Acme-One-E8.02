package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/item/list.csv",encoding="utf-8",numLinesToSkip=1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String itemtype, final String link) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Item");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, price);
		super.checkColumnHasValue(recordIndex, 3, itemtype);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("itemType", itemtype);

		super.signOut();
	}

}

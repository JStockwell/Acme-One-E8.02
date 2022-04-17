package acme.testing.any.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyComponentListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/component/list.csv",encoding="utf-8",numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String technology, final String retailPrice) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("List", "Component");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("retailPrice", retailPrice);

		super.signOut();
	}

}

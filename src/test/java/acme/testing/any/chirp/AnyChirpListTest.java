package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/chirp/list.csv",encoding="utf-8",numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String author, final String title, final String body, final String mail) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Chirp");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, mail);

		super.signOut();
	}

}

package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpCreationTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author, final String body, final String mail) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Create chirp");
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("mail", mail);
		super.fillInputBoxIn("confirmation", "true");

		super.clickOnSubmit("Create");

		super.clickOnMenu("Any", "Chirp");

		super.checkListingExists();
		super.sortListing(4, "desc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, mail);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String author, final String body, final String mail) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Create chirp");
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("mail", mail);
		super.fillInputBoxIn("confirmation", "true");

		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

}

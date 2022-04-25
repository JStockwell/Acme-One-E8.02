package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/systemConfiguration/form.csv",encoding="utf-8",numLinesToSkip=1,delimiterString=";")
	@Order(10)
	public void positiveTest(final String defaultCurrency, final String acceptedCurrencies, final String strongSpamTerms,
		final String strongThreshold, final String weakSpamTerms, final String weakThreshold) {
		super.signIn("consumer1", "consumer1");
		
		super.clickOnMenu("Authenticated","System Configuration");
		
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakThreshold", weakThreshold);
		
		super.signOut();
		
	}
}

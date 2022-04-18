package acme.testing;

import acme.framework.helpers.StringHelper;
import acme.framework.testing.AbstractTest;

public abstract class TestHarness extends AbstractTest {

	// Business methods -------------------------------------------------------

	protected void signIn(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);

		super.navigateHome();
		super.clickOnMenu("Sign in");
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("remember", "true");
		super.clickOnSubmit("Sign in");
		super.checkCurrentPath("/master/welcome");
		super.checkLinkExists("Account");
	}

	protected void signOut() {
		super.navigateHome();
		super.clickOnMenu("Sign out");
		super.checkCurrentPath("/master/welcome");
		super.checkNotLinkExists("Account");
	}


}

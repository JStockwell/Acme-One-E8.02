package acme.testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public String computeDeltaMoment(final String deltaDays) {
		Calendar calendar;
		Date moment;
		final DateFormat dateFormat = new SimpleDateFormat("y/MM/dd HH:mm");
		calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1*Integer.parseInt(deltaDays));
		moment=calendar.getTime();
		moment.setHours(1);
    	moment.setMinutes(1);
		return dateFormat.format(moment);
	}


}

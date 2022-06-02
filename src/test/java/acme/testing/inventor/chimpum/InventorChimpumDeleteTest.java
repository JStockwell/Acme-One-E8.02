package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorChimpumDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/delete.csv", encoding = "utf-8", numLinesToSkip = 1,delimiterString=";")
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String startDate, final String finishDate, final String budget, final String link, final String itemId) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My chimpums");
		super.clickOnButton("Create");
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@id=\"itemId_proxy\"]/option[" + itemId + "]")).click();

		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "My chimpums");

		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, budget);
		
		super.clickOnListingRecord(0);

		super.clickOnSubmit("Delete");

		super.signOut();
	}


}

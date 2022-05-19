package acme.testing.any.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Announcement;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class AnyAnnouncementListTest extends TestHarness{
	
	@Autowired
	private AnyAnnouncementTestRepository repository;
	
	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchAnnouncements();
	}
	
	protected void patchAnnouncements() {
	    Collection<Announcement> announcements;
	    Calendar calendar;
		Date moment;
		
		calendar=Calendar.getInstance();

	    announcements = this.repository.findAnnouncementsToPatch();
	    for (final Announcement announcement : announcements) {
	    	calendar.add(Calendar.DAY_OF_YEAR, -1);
	    	moment=calendar.getTime();
	    	moment.setHours(1);
	    	moment.setMinutes(1);
	        announcement.setCreationMoment(moment);
	        this.repository.save(announcement);
	    }
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/announcement/list.csv",encoding="utf-8",numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String deltaDays, final String title, final String body, final String critical, final String link) {
		String moment;

        moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Announcement");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, critical);
		super.checkColumnHasValue(recordIndex, 2, moment);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("creationMoment", moment);

		super.signOut();
	}

}

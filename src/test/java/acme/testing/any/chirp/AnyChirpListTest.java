package acme.testing.any.chirp;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Chirp;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{
	
	@Autowired
	private AnyChirpTestRepository repository;
	
	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchChirps();
	}
	
	protected void patchChirps() {
	    Collection<Chirp> chirps;
	    Calendar calendar;
		Date moment;
		
		calendar=Calendar.getInstance();

	    chirps = this.repository.findChirpsToPatch();
	    for (final Chirp chirp : chirps) {
	    	calendar.add(Calendar.DAY_OF_YEAR, -1);
	    	moment=calendar.getTime();
	    	moment.setHours(1);
	    	moment.setMinutes(1);
	        chirp.setMoment(moment);
	        this.repository.save(chirp);
	    }
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/any/chirp/list.csv",encoding="utf-8",numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author, final String body, final String mail,final String deltaDays) {
		String moment;

        moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "Chirp");
		super.checkListingExists();
		super.sortListing(4, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, mail);
		super.checkColumnHasValue(recordIndex, 4, moment);

		super.signOut();
	}

}

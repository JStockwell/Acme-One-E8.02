package acme.testing.any.announcement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyAnnouncementTestRepository extends AbstractRepository{
	@Query("select a from Announcement a where year(a.creationMoment) < 1901 order by a.title")
	Collection<Announcement> findAnnouncementsToPatch();
}
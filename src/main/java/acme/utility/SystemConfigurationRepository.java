package acme.utility;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SystemConfigurationRepository extends AbstractRepository {

	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
}

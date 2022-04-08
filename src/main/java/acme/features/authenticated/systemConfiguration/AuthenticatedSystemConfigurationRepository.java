package acme.features.authenticated.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;

@Repository
public interface AuthenticatedSystemConfigurationRepository {

	@Query("SELECT a FROM SystemConfiguration")
	SystemConfiguration findSystemConfiguration();

}

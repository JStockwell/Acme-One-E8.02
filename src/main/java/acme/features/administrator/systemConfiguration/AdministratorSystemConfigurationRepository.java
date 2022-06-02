package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository{

	@Query("select sysconfig from SystemConfiguration sysconfig")
	SystemConfiguration getSystemConfiguration();


}

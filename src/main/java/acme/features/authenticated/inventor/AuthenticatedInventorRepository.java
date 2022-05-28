package acme.features.authenticated.inventor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface AuthenticatedInventorRepository extends AbstractRepository {

	@Query("select inv from Inventor inv where inv.userAccount.id = :id")
	Inventor findOneInventorByUserAccountId(int id);

	@Query("select user from UserAccount user where user.id = :id")
	UserAccount findOneUserAccountById(int id);

}

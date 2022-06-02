package acme.features.authenticated.patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface AuthenticatedPatronRepository extends AbstractRepository {

	@Query("select pat from Patron pat where pat.userAccount.id = :id")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select user from UserAccount user where user.id = :id")
	UserAccount findOneUserAccountById(int id);

}

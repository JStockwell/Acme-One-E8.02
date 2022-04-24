package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;



@Repository
public interface AnyUserAccountRepository extends AbstractRepository {

	@Query("select user from UserAccount user where user.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select user from UserAccount user where user.enabled = true")
	Collection<UserAccount> findEnabledUserAccounts();
	
	
	

}
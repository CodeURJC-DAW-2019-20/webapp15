package es.urjc.code.daw.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User findByName(String string);
	
	
	@Query(value = "UPDATE USER SET acc_balance=900 WHERE USER.NAME=?1",nativeQuery = true)
	public void updateMoneyUser(String name);

}

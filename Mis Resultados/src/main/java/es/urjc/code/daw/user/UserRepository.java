package es.urjc.code.daw.user;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User findByName(String string);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET acc_balance=?1 WHERE USER.NAME=?2",nativeQuery = true)
	public void updateMoneyUser(Integer apostado, String name);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE USER SET fav_team=?1 WHERE USER.EMAIL=?2",nativeQuery = true)
	public void updateFavTeam(String fav_team, String email);
	
	@Modifying
	@Query("update User u set u.acc_balance=900 where u.name = : name")
	public void updateMoneyUser2(@Param("name") String name);

}

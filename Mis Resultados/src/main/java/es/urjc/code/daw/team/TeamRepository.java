package es.urjc.code.daw.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	public Optional<Team> findById(Long id);
	public Optional<Team> findByName(String name);	
	//Consulta a la bbdd que te devuelve por liga y por orden la clasificacion en la liga
	public List<Team> findByLeagueOrderByPointsDesc(String league);
	
	/*
	 * 	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET acc_balance=?1 WHERE USER.NAME=?2",nativeQuery = true)
	public void updateMoneyUser(Integer apostado, String name);
	
	 * */
	@Transactional
	@Modifying
	@Query(value = "UPDATE TEAM SET points=?1 WHERE TEAM.NAME=?2",nativeQuery = true)
	public void updatePoint(Integer points,String teamName);
	
}

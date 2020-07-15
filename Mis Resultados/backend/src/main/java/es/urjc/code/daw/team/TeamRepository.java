package es.urjc.code.daw.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	public Optional<Team> findById(Long id);
	public Optional<Team> findByName(String name);	

	public List<Team> findByLeagueOrderByPointsDesc(String league);
	
	public List<Team> findByLeagueOrderByGoalsInFavorDesc(String league);
	
	public List<Team> findByLeagueOrderByGoalsAgainstAsc(String league);
	
	public Optional<Team> findByPosition(Integer position);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE TEAM SET points=?1 WHERE TEAM.NAME=?2",nativeQuery = true)
	public void updatePoint(Integer points,String teamName);

}

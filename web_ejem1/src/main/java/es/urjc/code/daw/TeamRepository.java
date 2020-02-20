package es.urjc.code.daw;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	public Optional<Team> findById(Long id);
	public Optional<Team> findByName(String name);	
	//Consulta a la bbdd que te devuelve por liga y por orden la clasificacion en la liga
	public List<Team> findByLeagueOrderByPosition(String league);

}

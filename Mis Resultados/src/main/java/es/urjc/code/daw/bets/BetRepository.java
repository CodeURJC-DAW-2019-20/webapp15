package es.urjc.code.daw.bets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.code.daw.team.Team;

@Repository
public interface BetRepository extends JpaRepository<Team,Long> {

}

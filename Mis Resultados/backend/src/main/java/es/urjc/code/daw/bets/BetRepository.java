package es.urjc.code.daw.bets;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.code.daw.team.Team;
import es.urjc.code.daw.user.User;

@Repository
public interface BetRepository extends JpaRepository<Bets,Long> {

	Optional<ArrayList<Bets>> findByUser(User u);

}

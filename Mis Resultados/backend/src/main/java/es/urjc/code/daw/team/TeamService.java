package es.urjc.code.daw.team;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.urjc.code.daw.team.Team;
import es.urjc.code.daw.team.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository repository;

	public Optional<Team> findOne(String name) {
		return repository.findByName(name);
	}

	public List<Team> findAll() {
		return repository.findAll();
	}
	
	public Page<Team> listAllPageable(int page, int size) {
		
		Page<Team> allTeams = repository.findAll(PageRequest.of(page, size));
		return allTeams;
		
	}

}

package es.urjc.code.daw.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.daw.bets.BetRepository;
import es.urjc.code.daw.team.Team;
import es.urjc.code.daw.team.TeamRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;
import es.urjc.code.daw.user.UserService;

@RestController
@RequestMapping("/api")
public class SessionRestController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/equipos")
	public Collection<Team> getTeams() {
		return teamRepository.findAll();		
	}
	
	@GetMapping("/equipos/{id}")
	public ResponseEntity<Team> getTeamsById(@PathVariable Long id) {
		Optional<Team> teamAux = teamRepository.findById(id);
		return teamAux.map(team -> new ResponseEntity<>(team, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/equipos/name/{name}")
	public ResponseEntity<Team> getTeamsByName(@PathVariable String name) {
		Optional<Team> teamAux = teamRepository.findByName(name);
		return teamAux.map(team -> new ResponseEntity<>(team, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/equipos/first")
	public Team getFirstPositionTeam() {
		Team teamAux = teamRepository.findByLeagueOrderByPointsDesc("La liga").get(0);
		return teamAux;
	}
	
	@GetMapping("/equipos/goalsF")
	public Team getTeamMoreGoalInFavor() {
		Team teamAux = teamRepository.findByLeagueOrderByGoalsInFavorDesc("La liga").get(0);
		return teamAux;
	}
	
	@GetMapping("/equipos/goalsA")
	public Team getTeamLessGoalAgainst() {
		Team teamAux = teamRepository.findByLeagueOrderByGoalsAgainstAsc("La liga").get(0);
		return teamAux;
	}
	
	@GetMapping("/equipos/position/{p}")
	public ResponseEntity<Team> getTeamByPosition(@PathVariable Integer p) {
		Optional<Team> teamAux = teamRepository.findByPosition(p);
		return teamAux.map(team -> new ResponseEntity<>(team, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/equipos/update/{id}/{p}")
	public ResponseEntity<Team> updateTeamPosition(@PathVariable Long id,@PathVariable Integer p) {
		Optional<Team> teamAux = teamRepository.findById(id);
		
		if(teamAux.isPresent()) {
			Team team;
			team = teamAux.get();
			team.setPosition(p);
			teamRepository.save(team);
			
		}		
		
		Optional<Team> teamAux2 = teamRepository.findById(id);
		
		return teamAux2.map(team -> new ResponseEntity<>(team, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	
	}
	@PutMapping("/equipos/update/{id}/")
	public ResponseEntity<Team> updateTeam(@PathVariable Long id,@RequestBody Team t) {
		Optional<Team> teamAux = teamRepository.findById(t.getId());
		
		if(teamAux.isPresent()) {
			t.setId(id);
			teamRepository.save(t);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@PutMapping("/equipos/updateTeam/{id}/")
	public ResponseEntity<Team> updateTeamEfecttive(@PathVariable Long id,@RequestBody Team t) {
		Optional<Team> teamAux = teamRepository.findById(t.getId());
		
		if(teamAux.isPresent()) {
			Team team = teamAux.get();
			t.setId(id);
			team.setName((t.getName().equals("")||t.getName()==null||t.getName().equals(" "))?team.getName():t.getName());
			team.setLeague((t.getLeague().equals("")||t.getLeague()==null||t.getLeague().equals(" ")?team.getLeague():t.getLeague()));
			
			teamRepository.save(team);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@PutMapping("/equipos/updateTeamWin/{id}/")
	public ResponseEntity<Team> updateMatchWin(@PathVariable Long id) {
		Optional<Team> teamAux = teamRepository.findById(id);
		
		if(teamAux.isPresent()) {
			Team team = teamAux.get();
			
			team.setWinners(team.getWinners()+1);
			team.setPoints(team.getPoints()+3);
			teamRepository.save(team);

			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@PutMapping("/equipos/updateTeamTied/{id}/")
	public ResponseEntity<Team> updateMatchTied(@PathVariable Long id) {
		Optional<Team> teamAux = teamRepository.findById(id);
		
		if(teamAux.isPresent()) {
			Team team = teamAux.get();
			team.setTied(team.getTied()+1);
			team.setPoints(team.getPoints()+1);
			teamRepository.save(team);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}

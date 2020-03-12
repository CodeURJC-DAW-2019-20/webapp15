package es.urjc.code.daw.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.daw.Match;
import es.urjc.code.daw.MatchService;
import es.urjc.code.daw.bets.BetRepository;
import es.urjc.code.daw.team.Team;
import es.urjc.code.daw.team.TeamRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserComponent;
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
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private MatchService matchService;
	
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
	@PostMapping("/equipos/saveTeam/")
	public ResponseEntity<Team> saveTeam(@RequestBody Team t) {
		Optional<Team> teamAux = teamRepository.findByName(t.getName());
		
		if(!teamAux.isPresent()) {
			teamRepository.save(t);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/equipos/deleteTeam/{name}")
	public ResponseEntity<Team> deleteTeam(@PathVariable String name) {
		Optional<Team> teamAux = teamRepository.findByName(name);
		System.out.println(teamAux.get().toString());
		if(teamAux.isPresent()) {
			Team team = teamAux.get();
			teamRepository.delete(team);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/user/")
	public ResponseEntity<User> getUser(){
		
		User user=userComponent.getLoggedUser();

		if(user==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
	
	}
	
	@PutMapping("/user/updateFav/{userName}/{team}")
	public ResponseEntity<User> updateFav(@PathVariable String userName, @PathVariable String team){
		Optional<Team> teamAux = teamRepository.findByName(team);
		User user = userRepository.findByName(userName);
				
		if(teamAux.isPresent()&&user!=null) {
			
			user.setFav_team(team);
			userRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);

		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/user/updateFav/{team}")
	public ResponseEntity<User> updateFavLoggerUser(@PathVariable String team){
		Optional<Team> teamAux = teamRepository.findByName(team);
		
		User user = userComponent.getLoggedUser();

		if(teamAux.isPresent()&&userComponent.isLoggedUser()) {
			
			user.setFav_team(team);
			userRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);

		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/user/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User userAux = userRepository.findByName(user.getName());		
		if(userAux==null) {
			userRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}		
	}
	
	
	@GetMapping("/table/{league}")
	public ResponseEntity<List<Team>> showTable(@PathVariable String league){
		List<Team> table = teamRepository.findByLeagueOrderByPointsDesc(league);

		if(!table.isEmpty()) {

			return new ResponseEntity<>(table,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping("/equipos/nextmatches")
	public ResponseEntity<List<Match>> nextMatches(){
		List<Match> matches = matchService.controlNextMatches();
		if(!matches.isEmpty()) {
			return new ResponseEntity<>(matches,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
}

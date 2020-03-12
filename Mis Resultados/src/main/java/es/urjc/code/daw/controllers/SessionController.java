package es.urjc.code.daw.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.bets.*;
import es.urjc.code.daw.Match;
import es.urjc.code.daw.MatchService;
import es.urjc.code.daw.team.*;
import es.urjc.code.daw.user.*;

@Controller
public class SessionController {
	// Repositories
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private BetRepository betRepository;
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/home")
	public String root(Model model, HttpServletRequest request) {

		init(model, request);
		return "home";
	}

	@RequestMapping(value = "/")
	public String home(Model model, HttpServletRequest request) {

		init(model, request);

		List<Team> allTeams = (List<Team>) teamRepository.findAll();

		for (Team t : allTeams) {
			System.out.println(t.toString());
		}

		return "home";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpServletRequest request) {

		init(model, request);

		return "login";
	}

	@RequestMapping(value = "/registro", method = { RequestMethod.GET, RequestMethod.POST })
	public String registro(Model model, HttpServletRequest request) {

		init(model, request);

		return "registro";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(Model model, HttpServletRequest request, @RequestParam String name,
			@RequestParam String surname, @RequestParam String email, @RequestParam String password) {

		init(model, request);
		User NewUser = new User(name, surname, email, "", password, "ROLE_USER");
		NewUser.setAcc_balance(10);
		userService.save(NewUser);

		return "home";
	}



	@GetMapping("/equipos")
	public String equipos(Model model, HttpServletRequest request,
			@RequestParam(name = "teampage", required = false, defaultValue = "0") Integer teampage) {

		init(model, request);
		Page<Team> allTeams = teamRepository.findAll(PageRequest.of(0, 10 * (teampage + 1)));

		model.addAttribute("allTeams", allTeams);
		model.addAttribute("nextTeamsPage", teampage + 1);
		Boolean showmoreteams = teampage < allTeams.getTotalPages();
		model.addAttribute("showmoreteams", showmoreteams);

		return "equipos";
	}

	@GetMapping("/equipo/{name}")
	public String equipo(Model model, HttpServletRequest request, @PathVariable String name) {

		Optional<Team> teamAux = teamRepository.findByName(name);
		Team team;
		if (teamAux.isPresent()) {
			team = teamAux.get();
		} else {
			return "error";
		}
		String direction = team.getDirection();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User u = userRepository.findByEmail(email);

		String fav_team = u.getFav_team();

		if (fav_team.equals(name)) {
			model.addAttribute("press", "press");
		}

		model.addAttribute("teamName", name);

		model.addAttribute("teamStats", team);
		model.addAttribute("teamDirection", direction);
		init(model, request);

		return "equipo";
	}

	@PostMapping(value = "/equipo/{name}/setFav")
	public String setFav(Model model, HttpServletRequest request, @PathVariable String name,
			@RequestParam(name = "fav_team") String fav_team) {
		Optional<Team> teamAux = teamRepository.findByName(name);
		Team team;
		if (teamAux.isPresent()) {
			team = teamAux.get();
		} else {
			return "error";
		}
		String direction = team.getDirection();
		model.addAttribute("teamName", name);

		model.addAttribute("teamStats", team);
		model.addAttribute("teamDirection", direction);
		model.addAttribute("press", true);
		init(model, request);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName(); // get logged in username
		userRepository.updateFavTeam(fav_team, email);

		return "equipo";
	}

	@GetMapping("/clasificacion")
	public String table(Model model, HttpServletRequest request) {

		List<Team> clasificacion = teamRepository.findByLeagueOrderByPointsDesc("La liga");

		model.addAttribute("equipoPosicion", clasificacion);
		init(model, request);

		return "clasificacion";
	}

	@RequestMapping("/partidos")
	public String nextmatches(Model model, HttpServletRequest request) {
		List<Match> matches;

		matches = matchService.controlNextMatches();
		model.addAttribute("match", matches);
		init(model, request);

		return "partidos";

	}

	@PostMapping(value = "/partidos/addMatch")
	public String addMatch(Model model, HttpServletRequest request, @RequestParam(name = "local") String local,
			@RequestParam(name = "visit") String visit) {
		init(model, request);

		Optional<Team> teamAux = teamRepository.findByName(local);
		Team team;
		if (teamAux.isPresent()) {
			team = teamAux.get();
		} else {
			return "error";
		}
		team.addMatchByAdmin(visit);
		teamRepository.save(team);

		Optional<Team> teamAux2 = teamRepository.findByName(visit);

		Team team2;
		if (teamAux2.isPresent()) {
			team2 = teamAux2.get();
		} else {
			return "error";
		}
		team2.addMatchByAdmin(local);
		teamRepository.save(team2);

		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);

		return "partidos";
	}

	@GetMapping("/apostar")
	public String apostar(Model model, HttpServletRequest request) {
		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);

		if (matchService.getBetMatches().isEmpty()) {
			model.addAttribute("codigoHtmlInicio", false);
		} else {
			model.addAttribute("listMatch", matchService.getBetMatches());

			model.addAttribute("codigoHtmlInicio", true);

			String totalBet = calculateBetCombinated(matchService.getBetMatches());

			model.addAttribute("totalBet", totalBet);
		}

		init(model, request);
		return "apostar";
	}

	@GetMapping("/apostar/{id}/{id2}/{id3}/{id4}")
	public String apostar(Model model, HttpServletRequest request, @PathVariable String id, @PathVariable String id2,
			@PathVariable String id3, @PathVariable String id4) {
		init(model, request);

		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);
		model.addAttribute("codigoHtmlInicio", true);

		
		String totalBet = matchService.apostar(id, id2, id3, id4);

		model.addAttribute("totalBet", totalBet);
		model.addAttribute("listMatch", matchService.getBetMatches());

		return "apostar";
	}

	private String calculateBetCombinated(ArrayList<Match> betMatches2) {
		float total = 0;
		float totalAux = 0;
		for (Match b : betMatches2) {
			totalAux = Float.parseFloat(b.getBetSelected());
			if (total == 0) {
				total = totalAux;
			} else {
				total = total * totalAux;
			}
		}
		return total + "";
	}

	@PostMapping(value = "/apostar/calcular")
	public String calculateBet(Model model, HttpServletRequest request, @RequestParam(name = "precio") String precio) {

		init(model, request);
		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);
		if (matchService.getBetMatches().isEmpty()) {
			model.addAttribute("codigoHtmlInicio", false);
		} else {
			model.addAttribute("listMatch", matchService.getBetMatches());

			model.addAttribute("codigoHtmlInicio", true);

			String totalBet = calculateBetCombinated(matchService.getBetMatches());

			float totalBetAux = Float.parseFloat(totalBet);

			totalBetAux = totalBetAux * Float.parseFloat(precio);

			model.addAttribute("totalBet", totalBetAux);
			model.addAttribute("precio", precio);
		}
		return "apostar";
	}

	@GetMapping("apostar/deleteBet")
	public String deleteBets(Model model, HttpServletRequest request) {
		init(model, request);

		matchService.getBetMatches().clear();

		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);
		if (matchService.getBetMatches().isEmpty()) {
			model.addAttribute("codigoHtmlInicio", false);
		} else {
			model.addAttribute("listMatch", matchService.getBetMatches());

			model.addAttribute("codigoHtmlInicio", true);

		}
		return "apostar";

	}

	@PostMapping(value = "/apostar/apostado/{totalBet}")
	public String doBet(Model model, HttpServletRequest request, @PathVariable String totalBet) {
		init(model, request);
		List<Match> matches;

		matches = matchService.controlNextMatches();

		model.addAttribute("match", matches);

		model.addAttribute("codigoHtmlInicio", false);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName(); // get logged in username
		String name = "LOGIN";

		if (userRepository.findByEmail(email) != null) {
			name = userRepository.findByEmail(email).getName();
		}

		// Simulacion de los partidos
		User u = userRepository.findByName(name);

		Optional<ArrayList<Bets>> betList;

		betList = betRepository.findByUser(u);

		System.out.println(betList);

		boolean result = generateRandomResult();

		Integer auxMoney = u.getAcc_balance();
		Integer totalBetAux = Math.round(Float.parseFloat(totalBet));

		if (result) {
			model.addAttribute("ganado", true);
			model.addAttribute("perdido", false);
			auxMoney = auxMoney + totalBetAux;
			userRepository.updateMoneyUser(auxMoney, name);
		} else {
			model.addAttribute("ganado", false);
			model.addAttribute("perdido", true);
			auxMoney = auxMoney - totalBetAux;
			userRepository.updateMoneyUser(auxMoney, name);
		}

		Bets b = new Bets(u);

		ArrayList<String> auxMatches = new ArrayList<String>();

		for (Match m : matchService.getBetMatches()) {
			if (result) {
				auxMatches.add(
						m.localTeam.getName() + " vs " + m.visitantTeam.getName() + " Ganado: " + totalBetAux + "€");
				System.out.println(m.toString());
			} else {
				auxMatches.add(
						m.localTeam.getName() + " vs " + m.visitantTeam.getName() + " Perdido: " + totalBetAux + "€");
				System.out.println(m.toString());
			}
		}

		b.setMatches(auxMatches);

		betRepository.save(b);

		for (Match bAux : matchService.getBetMatches()) {
			Optional<Team> teamAux = teamRepository.findByName(bAux.getLocalTeam().getName());

			Team team;
			if (teamAux.isPresent()) {
				team = teamAux.get();
			} else {
				return "error";
			}
			team.removeMatch(bAux.getVisitantTeam().getName());
			teamRepository.save(team);

			Optional<Team> teamAux2 = teamRepository.findByName(bAux.getVisitantTeam().getName());

			Team team2;
			if (teamAux2.isPresent()) {
				team2 = teamAux2.get();
			} else {
				return "error";
			}
			team2.removeMatch(bAux.getLocalTeam().getName());
			teamRepository.save(team2);

		}

		for (Match bAux : matchService.getBetMatches()) {
			bAux.getLocalTeam().getMatches().clear();
			bAux.getVisitantTeam().getMatches().clear();
			System.out.println("Match " + bAux.toString());
		}

		matchService.getBetMatches().clear();

		return "apostar";
	}

	private boolean generateRandomResult() {
		boolean aux = true;

		Random aleatorio = new Random();

		int auxInt;

		for (Match m : matchService.getBetMatches()) {
			auxInt = aleatorio.nextInt(3);
			if (auxInt == 0) {
				if (m.betLocal == null) {
					aux = false;
				} else {
					Optional<Team> teamAux = teamRepository.findByName(m.getLocalTeam().getName());

					Team team;
					if (teamAux.isPresent()) {
						team = teamAux.get();
					} else {
						team = new Team();
					}

					teamRepository.updatePoint(team.getPoints() + 3, team.getName());
				}
			} else if (auxInt == 1) {
				if (m.betTied == null) {
					aux = false;
				} else {
					Optional<Team> teamAux = teamRepository.findByName(m.getLocalTeam().getName());

					Team team;
					if (teamAux.isPresent()) {
						team = teamAux.get();
					} else {
						team = new Team();
					}

					teamRepository.updatePoint(team.getPoints() + 1, team.getName());

					Optional<Team> teamAux2 = teamRepository.findByName(m.getVisitantTeam().getName());

					Team team2;
					if (teamAux2.isPresent()) {
						team2 = teamAux2.get();
					} else {
						team2 = new Team();
					}

					teamRepository.updatePoint(team2.getPoints() + 1, team2.getName());
				}

			} else {
				if (m.betVisit == null) {
					aux = false;
				} else {
					Optional<Team> teamAux2 = teamRepository.findByName(m.getVisitantTeam().getName());

					Team team2;
					if (teamAux2.isPresent()) {
						team2 = teamAux2.get();
					} else {
						team2 = new Team();
					}

					teamRepository.updatePoint(team2.getPoints() + 3, team2.getName());
				}
			}
		}

		return aux;
	}

	// Método que inicializa la bbdd de toda la página
	// Ir añadiendo campos
	public void init(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName(); // get logged in username
		String name = "LOGIN";

		if (userRepository.findByEmail(email) != null) {
			name = userRepository.findByEmail(email).getName();
		}

		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("user", (request.isUserInRole("USER") || request.isUserInRole("ADMIN")));
		model.addAttribute("username", name);

	}

	@GetMapping("/user")
	public String historico(Model model, HttpServletRequest request) {
		init(model, request);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName(); // get logged in username
		String name = "LOGIN";

		if (userRepository.findByEmail(email) != null) {
			name = userRepository.findByEmail(email).getName();
		}
		User u2 = userRepository.findByName(name);

		Optional<ArrayList<Bets>> betsAux = betRepository.findByUser(u2);

		ArrayList<Bets> bets;

		System.out.println(u2.toString());

		if (betsAux.isPresent()) {
			bets = betsAux.get();
		} else {
			return "error";
		}

		model.addAttribute("userAux", u2);
		model.addAttribute("betsAux", bets);

		return "user";
	}

}

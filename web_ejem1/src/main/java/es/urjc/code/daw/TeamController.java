package es.urjc.code.daw;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class TeamController {
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	BetRepository betRepository;

	private ArrayList<Match> betMatches = new ArrayList<Match>();
	
	@GetMapping("/")
	public String home(Model model) {
		List<Team> allTeams = teamRepository.findAll();

		model.addAttribute("allTeams", allTeams);

		return "home";
	}

	@GetMapping("/equipos")
	public String equipos(Model model) {
		List<Team> allTeams = teamRepository.findAll();

		model.addAttribute("allTeams", allTeams);

		return "equipos";
	}

	@GetMapping("/equipo/{name}")
	public String equipo(Model model, @PathVariable String name) {

		Optional<Team> teamAux = teamRepository.findByName(name);
		Team team;
		if (teamAux.isPresent()) {
			team = teamAux.get();
		} else {
			return "error";
		}
		model.addAttribute("teamName", name);

		model.addAttribute("teamStats", team);

		return "equipo";
	}

	@GetMapping("/clasificacion")
	public String table(Model model) {

		List<Team> clasificacion = teamRepository.findByLeagueOrderByPosition("La liga");

		model.addAttribute("equipoPosicion", clasificacion);

		return "clasificacion";
	}
	
	@GetMapping("/apostar")
	public String apostar(Model model) {
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		if(betMatches.isEmpty()) {
			model.addAttribute("codigoHtmlInicio",false);
		}else {
			model.addAttribute("listMatch",betMatches);

			model.addAttribute("codigoHtmlInicio",true);

		}
		return "apostar"; 
	}
	@GetMapping("/apostar/{id}/{id2}/{id3}/{id4}")
	public String apostar(Model model,@PathVariable String id, @PathVariable String id2, @PathVariable String id3,
			@PathVariable String id4) {
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		model.addAttribute("codigoHtmlInicio",true);
		
		Optional<Team> teamAux = teamRepository.findByName(id);

		Team local;

		if (teamAux.isPresent()) {
			local = teamAux.get();
		} else {
			local = new Team();
		}
		teamAux = teamRepository.findByName(id2);

		Team visit;

		if (teamAux.isPresent()) {
			visit = teamAux.get();
		} else {
			visit = new Team();
		}
		Match m1 = new Match(local,visit);
		
		switch(id4) {
			case "betLocal":
				m1.setBetLocal(id3);
				m1.setBetSelected(id3);
				break;
			case "betTied":
				m1.setBetTied(id3);
				m1.setBetSelected(id3);
				break;
			default:
				m1.setBetVisit(id3);
				m1.setBetSelected(id3);

		}
		boolean repeat=false;
		boolean changeBet=false;
		for(Match b: betMatches) {
			if(b.localTeam.getName().equals(id)) {
				if(b.betSelected.equals(m1.betSelected)) {
					repeat = true;
				}else {
					repeat = false;
					changeBet = true;
					b.setBetSelected(m1.getBetSelected());
				}
			}
		}
		if(!repeat&&!changeBet) {
			betMatches.add(m1);
		}
		
		String totalBet = calculateBetCombinated(betMatches);
		
		model.addAttribute("totalBet",totalBet);
		model.addAttribute("listMatch",betMatches);
		

		return "apostar"; 
	}

	private String calculateBetCombinated(ArrayList<Match> betMatches2) {
		float total = 0;
		float totalAux = 0;
		for(Match b: betMatches2) {
			totalAux = Float.parseFloat(b.getBetSelected());
			if(total==0) {
				total = totalAux;
			}else {
				total = total * totalAux;
			}
		}
		return total+"";
	}
	
	@GetMapping("apostar/deleteBet")
	public String deleteBets(Model model) {
		
		betMatches.clear();
		
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		if(betMatches.isEmpty()) {
			model.addAttribute("codigoHtmlInicio",false);
		}else {
			model.addAttribute("listMatch",betMatches);

			model.addAttribute("codigoHtmlInicio",true);

		}
		return "apostar"; 

	}

	@GetMapping("/partidos")
	public String nextmatches(Model model) {
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);

		return "partidos";

	}

	public List<Match> controlNextMatches() {
		List<Team> allTeams = teamRepository.findAll();
		List<Match> matches = new ArrayList<Match>();

		for (Team t : allTeams) {
			String visitName = t.getMatches().get(0);

			Optional<Team> teamAux = teamRepository.findByName(visitName);

			Team visit;

			if (teamAux.isPresent()) {
				visit = teamAux.get();
			} else {
				visit = new Team();
			}

			Team local = t;

			String horario = generateRandomDate();

			// Generar fecha actual + numero aleatorio
			Match m = new Match(local, visit, horario);

			boolean search = false;
			for (Match mAux : matches) {
				if (mAux.getLocalTeam().getName().equals(visit.getName())) {
					search = true;

					break;
				}
			}
			if (!search) {
				m.setBetLocal("1.53");
				m.setBetTied("2.53");
				m.setBetVisit("3.43");
				matches.add(m);
			}
		}
		return matches;
	}

	public String generateRandomDate() {

		Calendar c = new GregorianCalendar();

		Integer diaAux = c.get(Calendar.DATE);
		Integer mesAux = c.get(Calendar.MONTH) + 1;
		Integer horaAux = c.get(Calendar.HOUR_OF_DAY);

		Random aleatorio;
		aleatorio = new Random();

		diaAux = aleatorio.nextInt(5) + diaAux;
		horaAux = aleatorio.nextInt(2) + horaAux;

		String dia = Integer.toString(diaAux);
		String mes = Integer.toString(mesAux);
		String hora = Integer.toString(horaAux);

		String horario = dia + "-" + mes + "/" + hora + ":" + "00";

		return horario;
	}
}

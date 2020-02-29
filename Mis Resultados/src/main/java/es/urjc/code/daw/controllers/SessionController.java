package es.urjc.code.daw.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.urjc.code.daw.bets.*;
import es.urjc.code.daw.Match;
import es.urjc.code.daw.team.*;
import es.urjc.code.daw.user.*;


@Controller
public class SessionController {
    //Repositories
    @Autowired
    private  UserRepository userRepository;
    @Autowired 
    private TeamRepository teamRepository;
    @Autowired
	private BetRepository betRepository;
    
    private ArrayList<Match> betMatches = new ArrayList<Match>();

    //Services

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

        List<Team> allTeams = teamRepository.findAll();

        for(Team t: allTeams) {
        	System.out.println(t.toString());
        }
    	
        return "home";
    }
   
    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
    public String login(Model model, HttpServletRequest request) {
        
    	
    	init(model, request);

        return "login";
    }
    
    @RequestMapping(value = "/registro", method = {RequestMethod.GET,RequestMethod.POST})
    public String registro(Model model, HttpServletRequest request) {
        
    	
    	init(model, request);

        return "registro";
    }
    
    @RequestMapping(value = "/addUser")
    public String addUser(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String password) {
        
    	
    	init(model, request);
        User NewUser = new User(name, surname, email, "", password, "ROLE_USER");
        NewUser.setAcc_balance(0);
        userService.save(NewUser);

        return "home"; 
    }
    
    @GetMapping("/equipos")
	public String equipos(Model model, HttpServletRequest request) {
        List<Team> allTeams = teamRepository.findAll();
        
        model.addAttribute("allTeams",allTeams);
        init(model, request);
		
        return "equipos";
	}
	
	@GetMapping("/equipo/{name}")
	public String equipo(Model model, HttpServletRequest request,@PathVariable String name) {
		
		Optional<Team> teamAux= teamRepository.findByName(name);
		Team team ;
		if(teamAux.isPresent()) {
			team = teamAux.get();
		}else {
			return "error";
		}
		String direction = team.getDirection();
		model.addAttribute("teamName",name);
		
		model.addAttribute("teamStats",team);
		model.addAttribute("teamDirection",direction);
		init(model, request);
		
		return "equipo";
	}
	
	@PostMapping(value="/equipo/{name}/setFav")
    public String setFav(Model model, HttpServletRequest request,@PathVariable String name, @RequestParam(name="fav_team") String fav_team) {
		Optional<Team> teamAux= teamRepository.findByName(name);
		Team team ;
		if(teamAux.isPresent()) {
			team = teamAux.get();
		}else {
			return "error";
		}
		String direction = team.getDirection();
		model.addAttribute("teamName",name);
		
		model.addAttribute("teamStats",team);
		model.addAttribute("teamDirection",direction);
		init(model, request);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        userRepository.updateFavTeam(fav_team, email);
		
        
        return "equipo";
    }
	
	@GetMapping("/clasificacion")
	public String table(Model model, HttpServletRequest request) {
		
		List<Team> clasificacion = teamRepository.findByLeagueOrderByPosition("La liga");
		
		model.addAttribute("equipoPosicion",clasificacion);
		init(model, request);
		
		return "clasificacion";
	}
	
	@GetMapping("/partidos")
	public String nextmatches(Model model, HttpServletRequest request) {
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		init(model, request);

		return "partidos";

	}
	
	@PostMapping(value="/partidos/addMatch")
    public String addMatch(Model model, HttpServletRequest request, @RequestParam(name="local") String local,@RequestParam(name="visit") String visit) {
    	init(model, request);
    	
    	Optional<Team> teamAux= teamRepository.findByName(local);
		Team team ;
		if(teamAux.isPresent()) {
			team = teamAux.get();
		}else {
			return "error";
		}
        team.addMatchByAdmin(visit);
        teamRepository.save(team);

        Optional<Team> teamAux2 = teamRepository.findByName(visit);
        
		Team team2;
		if(teamAux2.isPresent()) {
			team2 = teamAux2.get();
		}else {
			return "error";
		}
        team2.addMatchByAdmin(local);
        teamRepository.save(team2);
        
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);

        return "partidos";
    }
	public String generateRandomDate() {
		
		Calendar c = new GregorianCalendar();

    	Integer diaAux =c.get(Calendar.DATE);
    	Integer mesAux = c.get(Calendar.MONTH)+1;
    	Integer horaAux = c.get(Calendar.HOUR_OF_DAY);
    	
        Random aleatorio;
        aleatorio = new Random();
    	
        diaAux = aleatorio.nextInt(5)+diaAux;
        horaAux  = aleatorio.nextInt(2)+horaAux;
    	
        
        String dia = Integer.toString(diaAux);
    	String mes = Integer.toString(mesAux);
    	String hora =Integer.toString(horaAux);
    	String año =Integer.toString(c.get(Calendar.YEAR));
    	
    	
    	String horario = dia +"/"+ mes + "/" + año + " "+ hora + ":" + "00";

    	return horario;
	}
	
	
	@GetMapping("/apostar")
	public String apostar(Model model, HttpServletRequest request) {
		List<Match> matches;
		
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		
		
		System.out.println(betMatches);
		
		System.out.println(betMatches.size());
		
		if(betMatches.isEmpty()) {
			model.addAttribute("codigoHtmlInicio",false);
		}else {
			model.addAttribute("listMatch",betMatches);

			model.addAttribute("codigoHtmlInicio",true);
			
			String totalBet = calculateBetCombinated(betMatches);
			
			model.addAttribute("totalBet",totalBet);
		}
		
		init(model,request);
		return "apostar"; 
	}
	@GetMapping("/apostar/{id}/{id2}/{id3}/{id4}")
	public String apostar(Model model, HttpServletRequest request,@PathVariable String id, @PathVariable String id2, @PathVariable String id3,
			@PathVariable String id4) {
		init(model,request);
		
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
	
	
	@PostMapping(value="/apostar/calcular")
	public String calculateBet(Model model, HttpServletRequest request,@RequestParam(name="precio") String precio) {
		
		init(model,request);
		List<Match> matches;
				
		matches =  controlNextMatches();
		
		model.addAttribute("match", matches);
		if(betMatches.isEmpty()) {
			model.addAttribute("codigoHtmlInicio",false);
		}else {
			model.addAttribute("listMatch",betMatches);

			model.addAttribute("codigoHtmlInicio",true);
			
			String totalBet = calculateBetCombinated(betMatches);
			
			float totalBetAux = Float.parseFloat(totalBet);

			totalBetAux = totalBetAux * Float.parseFloat(precio);
			
			model.addAttribute("totalBet",totalBetAux);
			model.addAttribute("precio",precio);
		}
		return "apostar"; 
	}
	@GetMapping("apostar/deleteBet")
	public String deleteBets(Model model, HttpServletRequest request) {
		init(model,request);
		
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
	
	@PostMapping(value="/apostar/apostado/{totalBet}")
	public String doBet(Model model, HttpServletRequest request, @PathVariable String totalBet) {  
		init(model,request);
		List<Match> matches;
		
		matches =  controlNextMatches();
		/*
		 * ControlNextMatches -> Borrar del repositorio los partidos apostados(betMatches)
		 * Borrar betMatches
		 * */
		model.addAttribute("match", matches);
				
		model.addAttribute("codigoHtmlInicio",false);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        String name = "LOGIN";

        if (userRepository.findByEmail(email) != null) {
            name = userRepository.findByEmail(email).getName();
        }
		
		//Simulacion  de los partidos
		User u = userRepository.findByName(name);

		Bets b = new Bets(u);
		
		ArrayList<String> auxMatches = new ArrayList<String>();
		
		for(Match m: betMatches) {
			auxMatches.add(m.localTeam.getName()+"vs"+m.visitantTeam.getName());
		}
		
		b.setMatches(auxMatches);
		
		betRepository.save(b); 
		
		boolean result = generateRandomResult();
		
		Integer auxMoney = u.getAcc_balance();
		Integer totalBetAux =Math.round(Float.parseFloat(totalBet));
		
		
		
		if(result) {
			model.addAttribute("ganado",true);
			model.addAttribute("perdido",false);
			auxMoney = auxMoney+totalBetAux;
			userRepository.updateMoneyUser(auxMoney,name);
		}else {
			model.addAttribute("ganado",false);
			model.addAttribute("perdido",true);
			auxMoney = auxMoney-totalBetAux;
			userRepository.updateMoneyUser(auxMoney,name);
		}
				
		for(Match bAux: betMatches) {    
			Optional<Team> teamAux = teamRepository.findByName(bAux.getLocalTeam().getName());
	        
			Team team;
			if(teamAux.isPresent()) {
				team = teamAux.get();
			}else {
				return "error";
			}
	        team.removeMatch(bAux.getVisitantTeam().getName());
	        teamRepository.save(team);
	        
			Optional<Team> teamAux2 = teamRepository.findByName(bAux.getVisitantTeam().getName());
	        
			Team team2;
			if(teamAux2.isPresent()) {
				team2 = teamAux2.get();
			}else {
				return "error";
			}
	        team2.removeMatch(bAux.getLocalTeam().getName());
	        teamRepository.save(team2);
	        
		}
        
        for(Match bAux: betMatches) {
			bAux.getLocalTeam().getMatches().clear();
			bAux.getVisitantTeam().getMatches().clear();
			System.out.println("Match "+bAux.toString());
		}
		
		betMatches.clear();
		
		
		
		
		return "apostar"; 
	}
	private boolean generateRandomResult() {
		boolean aux = true;
		
		Random aleatorio = new Random();
		
		int auxInt;

		for(Match m: betMatches) {
			auxInt = aleatorio.nextInt(3);
			if(auxInt==0) {
				if (m.betLocal==null) {
					aux = false;
					break;
				}
			}else if(auxInt==1) {
				if (m.betTied==null) {
					aux = false;
					break;
				}
				
			}else {
				if (m.betVisit==null) {
					aux = false;
					break;
				}
			}
		}
		
		return aux;
	}
	
	public List<Match> controlNextMatches() {
		List<Team> allTeams = teamRepository.findAll();
		List<Match> matches = new ArrayList<Match>();

		for (Team t : allTeams) {
			String visitName = t.getMatches().get(0);
			System.out.println(visitName);
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
			
			ArrayList<String> betAvanced = calculateBetAvanced(m);
					
			boolean search = false;
			for (Match mAux : matches) {
				if (mAux.getLocalTeam().getName().equals(visit.getName())) {
					search = true;

					break;
				}
			}
			if (!search) {
				if(betAvanced.get(0).length()>5){
					betAvanced.set(0,betAvanced.get(0).substring(0,4));
				}else {
					betAvanced.set(0,betAvanced.get(0));
				}
				if(betAvanced.get(1).length()>5){
					betAvanced.set(1,betAvanced.get(1).substring(0,4));
				}else {
					betAvanced.set(1,betAvanced.get(1));
				}
				if(betAvanced.get(2).length()>5){
					betAvanced.set(2,betAvanced.get(2).substring(0,4));
				}else {
					betAvanced.set(2,betAvanced.get(2));
				}
				m.setBetLocal(betAvanced.get(0));
				m.setBetVisit(betAvanced.get(1));
				m.setBetTied(betAvanced.get(2));
				matches.add(m);
			}
		}
		return matches;
	}
	public ArrayList<String> calculateBetAvanced(Match m1){
		int pointsLocal=1;
		int pointsVisit=1;
		
		float betLocal;
		float betVisit;
		float betTied;
		
		Optional<Team> teamAux = teamRepository.findByName(m1.getLocalTeam().getName());
		Optional<Team> teamAux2 = teamRepository.findByName(m1.getVisitantTeam().getName());
		
		Team local;
		Team visit;
		
		if (teamAux.isPresent()) {
			local = teamAux.get();
		} else {
			local = new Team();
		}
		if (teamAux2.isPresent()) {
			visit = teamAux2.get();
		} else {
			visit = new Team();
		}
		/*
		 * En caso de empate siempre gana el local
		 * Victoria = 10 puntos
		 * Menos perdidas = 7 puntos
		 * Más empatadas = 3 puntos 
		 * Puntos = 7 puntos
		 * Goles a favor = 6 puntos
		 * Goles en contra el que menos = 5 puntos
		 * Posicion = 9 puntos
		 * Local = 3 puntos
		 * Total 50 puntos*/
		if (local.getWinners()>=visit.getWinners()) pointsLocal=pointsLocal+10;
		else pointsVisit=pointsVisit+10;
		if (local.getLossers()<visit.getLossers()) pointsLocal=pointsLocal+6;
		else pointsVisit=pointsVisit+7;
		if (local.getTied()>=visit.getTied()) pointsLocal=pointsLocal+3;
		else pointsVisit=pointsVisit+3;
		if (local.getPoints()>=visit.getPoints()) pointsLocal=pointsLocal+7;
		else pointsVisit=pointsVisit+7;
		if (local.getGoalsInFavor()>=visit.getGoalsInFavor()) pointsLocal=pointsLocal+6;
		else pointsVisit=pointsVisit+6;
		if (local.getGoalsAgainst()<visit.getGoalsAgainst()) pointsLocal=pointsLocal+5;
		else pointsVisit=pointsVisit+5;
		if (local.getPosition()<visit.getPosition()) pointsLocal=pointsLocal+9;
		else pointsVisit=pointsVisit+10;
		
		/*Condicion de local*/
		pointsLocal=pointsLocal+3;

		betLocal = 3 - (float)((float) pointsLocal/50)*3;
		betVisit = 3 - (float)((float) pointsVisit/50)*3;
		betTied  = (betLocal+betVisit)/2;

		if(pointsLocal-pointsVisit>=10) {
			if(pointsLocal>=pointsVisit*2) {
				betVisit = betVisit + 3;
			}else {
				betVisit = betVisit + 1;
			}
		}
		if(pointsVisit-pointsLocal>=10) {
			if(pointsLocal>=pointsVisit*2) {
				betLocal = betLocal + 3;
			}else {
				betLocal = betLocal + 1;
			}
		}
		
		if(Math.abs(pointsLocal-pointsVisit)<10) {
			betLocal = betLocal + 1;
			betVisit = betVisit + 1;
		}
		
		if(pointsLocal>30) {
			betLocal = betLocal +1;
			betTied = (betLocal+betVisit)/2;
		}
		if(pointsVisit>30) {
			betVisit = betVisit + 1;
			betTied = (betLocal+betVisit)/2;
		}

		
		ArrayList<String> bets = new ArrayList<String>();
		
		bets.add(betLocal+"");
		bets.add(betVisit+"");
		bets.add(betTied+"");
		
		return bets;
	}
    //Método que inicializa la bbdd de toda la página
    //Ir añadiendo campos
    public  void init(Model model, HttpServletRequest request){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        String name = "LOGIN";

        if (userRepository.findByEmail(email) != null) {
            name = userRepository.findByEmail(email).getName();
        }

        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("user", (request.isUserInRole("USER") ||
                request.isUserInRole("ADMIN")));
        model.addAttribute("username", name);

    }
	@GetMapping("/user")
	public String historico(Model model, HttpServletRequest request) {
		init(model,request);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
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
		
		model.addAttribute("userAux",u2);
		model.addAttribute("betsAux",bets);
		
		return "user";
	}
	
}
    
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
        User NewUser = new User(name, surname, email, password, "ROLE_USER");
        userService.save(NewUser);

        return "home";
    }
    
    @GetMapping("/equipos")
	public String equipos(Model model) {
        List<Team> allTeams = teamRepository.findAll();
        
        model.addAttribute("allTeams",allTeams);
		
        return "equipos";
	}
	
	@GetMapping("/equipo/{name}")
	public String equipo(Model model,@PathVariable String name) {
		
		Optional<Team> teamAux= teamRepository.findByName(name);
		Team team ;
		if(teamAux.isPresent()) {
			team = teamAux.get();
		}else {
			return "error";
		}
		model.addAttribute("teamName",name);
		
		model.addAttribute("teamStats",team);
		
		return "equipo";
	}
	
	@GetMapping("/clasificacion")
	public String table(Model model) {
		
		List<Team> clasificacion = teamRepository.findByLeagueOrderByPosition("La liga");
		
		model.addAttribute("equipoPosicion",clasificacion);
		
		return "clasificacion";
	}
	
	@GetMapping("/partidos")
	public String nextmatches(Model model) {
		
        List<Team> allTeams = teamRepository.findAll();
        List<Match> matches = new ArrayList<Match>();
        for(Team t : allTeams) {
        	String visit = t.getMatches().get(0);

        	String local = t.getName();
        	
        	String horario = generateRandomDate();
        	
        	//Generar fecha actual + numero aleatorio
        	Match m = new Match(local,visit,horario);
        	
        	System.out.println(m.toString());
        	
        	matches.add(m);
        }
        model.addAttribute("match",matches);
        
        
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
    	
    	String horario = dia +"-"+ mes + "/" + hora + ":" + "00";

    	return horario;
	}
	
	
	@GetMapping("/apostar")
	public String apostar(Model model) {
		return "apostar";
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

}
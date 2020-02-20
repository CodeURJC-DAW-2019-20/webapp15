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
	
    @PostConstruct
    private void initDatabase() {
    	ArrayList<String> nextMatches = new ArrayList<String>();
    	ArrayList<String> nextMatches2 = new ArrayList<String>();
    	ArrayList<String> nextMatches3 = new ArrayList<String>();
    	ArrayList<String> nextMatches4 = new ArrayList<String>();

    	nextMatches.add("Osasuna");nextMatches.add("Getafe");nextMatches.add("Leganes");
    	nextMatches2.add("Espanyol");nextMatches2.add("Valencia");nextMatches2.add("Granada");
    	nextMatches3.add("Valladolid");nextMatches3.add("Rayo Vallecano");nextMatches3.add("Girona");
    	nextMatches4.add("Betis");nextMatches4.add("Sevilla");nextMatches4.add("Mallorca");

		Team t1 = new Team("Real Madrid",3,5,6,23,30,18,1,"La liga","Concha Espina", nextMatches);
		Team t2 = new Team("Barcelona",4,2,3,21,28,10,2,"La liga","Canaletas",nextMatches2);
		Team t3 = new Team("Atletico de Madrid",2,2,5,13,10,20,4,"La liga","Metropolitano",nextMatches3);
		Team t4 = new Team("Real Sociedad",3,2,3,20,22,8,3,"La liga","Alava",nextMatches4);
		//Team t5 = new Team("Manchester City",3,2,3,20,22,8,3,"Premier league","Manchester",new ArrayList<>());
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		teamRepository.save(t4);
		//teamRepository.save(t5);
    }
	
	@GetMapping("/equipos")
	public String equipos(Model model) {
        List<Team> allTeams = teamRepository.findAll();
        
        model.addAttribute("allTeams",allTeams);
		
        return "equipos";
	}
	@GetMapping("/")
	public String index (Model model) {
		
		return "home";
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

}

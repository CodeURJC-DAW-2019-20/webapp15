package es.urjc.code.daw;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class TeamController {
	
	@Autowired
	TeamRepository teamRepository;
	
    @PostConstruct
    private void initDatabase() {
    	ArrayList<String> nextMatches = new ArrayList<String>();
    	ArrayList<String> nextMatches2 = new ArrayList<String>();
    	
    	nextMatches.add("Osasuna");nextMatches.add("Getafe");nextMatches.add("Leganes");
    	nextMatches2.add("Espanyol");nextMatches2.add("Valencia");nextMatches2.add("Granada");

		Team t1 = new Team("Real Madrid",3,5,6,23,30,18,1,"La liga","Concha Espina", nextMatches);
		Team t2 = new Team("Barcelona",4,2,3,21,28,10,2,"La liga","Canaletas",nextMatches2);

		teamRepository.save(t1);
		teamRepository.save(t2);

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

}

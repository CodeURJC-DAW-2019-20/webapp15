package es.urjc.code.daw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

@Component
public class DataBaseLoader {

	@Autowired
	private TeamRepository teamRepository;

	@PostConstruct
	public void init() {

		ArrayList<String> nextMatches = new ArrayList<String>();
		ArrayList<String> nextMatches2 = new ArrayList<String>();
		ArrayList<String> nextMatches3 = new ArrayList<String>();
		ArrayList<String> nextMatches4 = new ArrayList<String>();
		ArrayList<String> nextMatches5 = new ArrayList<String>();
		ArrayList<String> nextMatches6 = new ArrayList<String>();
		ArrayList<String> nextMatches7 = new ArrayList<String>();
		ArrayList<String> nextMatches8 = new ArrayList<String>();
		
		
		nextMatches.add("Osasuna");
		nextMatches.add("Getafe");
		nextMatches.add("Leganes");
		nextMatches2.add("Espanyol");
		nextMatches2.add("Valencia");
		nextMatches2.add("Granada");
		nextMatches3.add("Valladolid");
		nextMatches3.add("Rayo Vallecano");
		nextMatches3.add("Girona");
		nextMatches4.add("Betis");
		nextMatches4.add("Sevilla");
		nextMatches4.add("Mallorca");
		nextMatches5.add("Real Madrid");
		nextMatches6.add("Barcelona");
		nextMatches7.add("Atletico de Madrid");
		nextMatches8.add("Real Sociedad");
		
		Team t1 = new Team("Real Madrid",14,2,8,53,46,17,1, "La liga", "Concha Espina", nextMatches,
				"Plantilla_RealMadrid", "escudo_RealMadrid");
		Team t2 = new Team("Barcelona",16,4,4,52,62,29,2, "La liga", "Canaletas", nextMatches2,
				"Plantilla_Barcelona", "escudo_Barcelona");
		Team t3 = new Team("Atletico de Madrid",10,4,10,40,25,17,4, "La liga", "Metropolitano", nextMatches3,
				"Plantilla_AtleticodeMadrid", "escudo_AtleticodeMadrid");
		Team t4 = new Team("Real Sociedad",12,8,4,40,42,31,3, "La liga", "Alava", nextMatches4,
				"Plantilla_RealSociedad", "escudo_RealSociedad");
		Team t5 = new Team("Osasuna", 9, 5, 10, 37, 28, 18, 5, "La liga", "Concha Espina",nextMatches5,
				"Plantilla_RealMadrid", "escudo_RealMadrid");
		Team t6 = new Team("Espanyol", 8, 6, 10, 34, 30, 10, 6, "La liga", "Canaletas", nextMatches6,
				"Plantilla_Barcelona", "escudo_Barcelona");
		Team t7 = new Team("Valladolid", 11, 13, 0, 33, 45, 30, 7, "La liga", "Metropolitano", nextMatches7,
				"Plantilla_AtleticodeMadrid", "escudo_AtleticodeMadrid");
		Team t8 = new Team("Betis", 7, 7, 10, 31, 30, 20, 8, "La liga", "Alava", nextMatches8,
				"Plantilla_RealSociedad", "escudo_RealSociedad");
		// Team t5 = new Team("Manchester City",3,2,3,20,22,8,3,"Premier
		// league","Manchester",new ArrayList<>());

		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		teamRepository.save(t4);
		teamRepository.save(t5);
		teamRepository.save(t6);
		teamRepository.save(t7);
		teamRepository.save(t8);
		// teamRepository.save(t5);

	}
}
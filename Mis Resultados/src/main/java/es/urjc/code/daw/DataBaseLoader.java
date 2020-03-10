package es.urjc.code.daw;

import es.urjc.code.daw.bets.BetRepository;
import es.urjc.code.daw.bets.Bets;
import es.urjc.code.daw.team.*;
import es.urjc.code.daw.user.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

@Component
public class DataBaseLoader {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private BetRepository betRepository;
	
	
	@PostConstruct
	public void init() {
		// USER
		User user1 = new User("administrador", "", "administrador@gmail.com", "", "1234", "ROLE_ADMIN");
		User user2 = new User("Alvaro", "Orbaneja", "alvaro@gmail.com", "", "1234", "ROLE_USER");
		user1.setAcc_balance(1000);
		user2.setAcc_balance(1000);
		user1.setFav_team("Real Madrid");
		user2.setFav_team("Real Madrid");
		userRepository.save(user1);
		userRepository.save(user2);
		
		Bets bet1 = new Bets(user2);
		ArrayList<String> betMatches = new ArrayList<String>();
		betMatches.add("Real Madrid vs Osasuna Ganado: 500€");
		bet1.setMatches(betMatches);
		betRepository.save(bet1);
		
		
		ArrayList<String> nextMatches = new ArrayList<String>();
		ArrayList<String> nextMatches2 = new ArrayList<String>();
		ArrayList<String> nextMatches3 = new ArrayList<String>();
		ArrayList<String> nextMatches4 = new ArrayList<String>();
		ArrayList<String> nextMatches5 = new ArrayList<String>();
		ArrayList<String> nextMatches6 = new ArrayList<String>();
		ArrayList<String> nextMatches7 = new ArrayList<String>();
		ArrayList<String> nextMatches8 = new ArrayList<String>();

		nextMatches.add("Osasuna");
		nextMatches.add("Barcelona");
		nextMatches.add("Atletico de Madrid");
		nextMatches2.add("Espanyol");
		nextMatches2.add("Real Madrid");
		nextMatches2.add("Betis");
		nextMatches3.add("Valladolid");
		nextMatches3.add("Espanyol");
		nextMatches3.add("Real Madrid");
		nextMatches4.add("Betis");
		nextMatches4.add("Osasuna");
		nextMatches4.add("Valladolid");
		nextMatches5.add("Real Madrid");
		nextMatches5.add("Real Sociedad");
		nextMatches5.add("Espanyol");
		nextMatches6.add("Barcelona");
		nextMatches6.add("Atletico de Madrid");
		nextMatches6.add("Osasuna");
		nextMatches7.add("Atletico de Madrid");
		nextMatches7.add("Betis");
		nextMatches7.add("Real Sociedad");
		nextMatches8.add("Real Sociedad");
		nextMatches8.add("Valladolid");
		nextMatches8.add("Barcelona");

		Team t1 = new Team("Real Madrid", 14, 2, 8, 53, 46, 17, 1, "La liga", "Santiago Bernabeu", nextMatches,
				"Plantilla_RealMadrid", "escudo_RealMadrid");
		Team t2 = new Team("Barcelona", 16, 4, 4, 52, 62, 29, 2, "La liga", "Camp Nou", nextMatches2,
				"Plantilla_Barcelona", "escudo_Barcelona");
		Team t3 = new Team("Atletico de Madrid", 10, 4, 10, 40, 25, 17, 4, "La liga", "Wanda Metropolitano", nextMatches3,
				"Plantilla_AtleticodeMadrid", "escudo_AtleticodeMadrid");
		Team t4 = new Team("Real Sociedad", 12, 8, 4, 40, 42, 31, 3, "La liga", "Reale Arena", nextMatches4,
				"Plantilla_RealSociedad", "escudo_RealSociedad");
		Team t5 = new Team("Osasuna", 9, 5, 10, 37, 28, 18, 5, "La liga", "Estadio El Sadar", nextMatches5,
				"Plantilla_Osasuna", "escudo_Osasuna");
		Team t6 = new Team("Espanyol", 8, 6, 10, 34, 30, 10, 6, "La liga", "RCDE Stadium", nextMatches6,
				"plantilla_Espanyol", "escudo_Espanyol");
		Team t7 = new Team("Valladolid", 11, 13, 0, 33, 45, 30, 7, "La liga", "Estadio José Zorrilla", nextMatches7,
				"Plantilla_Valladolid", "escudo_Valladolid");
		Team t8 = new Team("Betis", 7, 7, 10, 31, 30, 20, 8, "La liga", "Alava", nextMatches8, "plantilla_Betis",
				"escudo_Betis");
		Team t9 = new Team("Getafe", 7, 7, 10, 30,30, 20, 9, "La liga", "Coliseum Alfonso Perez", nextMatches8, "Plantilla_RealSociedad",
				"escudo_getafe");
		Team t10 = new Team("Villareal", 7, 7, 10, 29, 30, 20, 10, "La liga", "Coliseum Alfonso Perez", nextMatches8, "Plantilla_RealSociedad",
				"escudo_villareal");
		Team t11 = new Team("Mallorca", 7, 7, 10, 28, 30, 20, 11, "La liga", "Coliseum Alfonso Perez", nextMatches8, "Plantilla_RealSociedad",
				"escudo_mallorca");
		Team t12 = new Team("Sevilla", 7, 7, 10, 27, 30, 20, 12, "La liga", "Coliseum Alfonso Perez", nextMatches8, "Plantilla_RealSociedad",
				"escudo_sevilla");

		

		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		teamRepository.save(t4);
		teamRepository.save(t5);
		teamRepository.save(t6);
		teamRepository.save(t7);
		teamRepository.save(t8);
		teamRepository.save(t9);
		teamRepository.save(t10);
		teamRepository.save(t11);
		teamRepository.save(t12);
		// teamRepository.save(t5);

	}
}
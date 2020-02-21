package es.urjc.code.daw;

import es.urjc.code.daw.team.*;
import es.urjc.code.daw.user.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import javax.annotation.PostConstruct;


@Component
public class DataBaseLoader {
	
    @Autowired private UserRepository userRepository;
    @Autowired private TeamRepository teamRepository;


    @PostConstruct
    public void init(){
    	//USER
        User user1 =  new User("administrador", "", "administrador@gmail.com", "1234" , "ROLE_ADMIN");
        User user2 =  new User("Álvaro", "Orbaneja", "alvaro@gmail.com", "1234" , "ROLE_USER");
        	userRepository.save(user1);
	        userRepository.save(user2);
	        
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
}
package webapp15;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import team.Team;
import team.TeamRepository;


@Controller
public class DataBaseUsage implements CommandLineRunner{
	@Autowired
	private TeamRepository teamRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Team t1 = new Team("Real Madrid",3,5,6,23,30,18,1,"La liga","Concha Espina", new ArrayList<String>());
		Team t2 = new Team("Barcelona",4,2,3,21,28,10,2,"La liga","Canaletas",new ArrayList<String>());
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		
	}

}

/*package webapp15;

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
*/

package webapp15;

import webapp15.user.User;
import webapp15.user.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataBaseUsage {
	
    @Autowired private UserRepository userRepository;


    @PostConstruct
    public void init(){
    	//USER
        User user1 =  new User("administrador", "", "administrador@gmail.com", "1234" , "ROLE_ADMIN");
        User user2 =  new User("Álvaro", "Orbaneja", "alvaro@gmail.com", "1234" , "ROLE_USER");
        if(userRepository.findByEmail("administrador@gmail.com")==null)
        	user1=userRepository.save(user1);
        user1=userRepository.findByEmail("administrador@gmail.com");
        if(userRepository.findByEmail("alvaro@gmail.com")==null)
	        user2=userRepository.save(user2);
        user2=userRepository.findByEmail("alvaro@gmail.com");
 
   
    }
}
package webapp15.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import webapp15.user.*;


@Controller
public class SessionController {
    //Repositories
    @Autowired
    private  UserRepository userRepository;

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
   
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Model model, HttpServletRequest request) {
        
    	
    	init(model, request);

        return "login";
    }
    
    @RequestMapping(value = "/addUser")
    public String addUser(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String password) {
        
    	
    	init(model, request);
        User NewUser = new User(name, surname, email, password, "ROLE_USER");
        userService.save(NewUser);

        return "registro";
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
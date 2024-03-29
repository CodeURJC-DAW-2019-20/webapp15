package es.urjc.code.daw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
						.allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers","x-xsrf-token")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.allowCredentials(true).maxAge(3600);
			}
		};
	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/new").setViewName("redirect:/new/");
			registry.addViewController("/new/").setViewName("forward:/new/index.html");
			
    }
}

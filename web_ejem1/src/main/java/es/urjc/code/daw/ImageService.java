
package es.urjc.code.daw;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Service
@Configuration
public class ImageService implements WebMvcConfigurer {

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"),"src/main/resources/static/images/");
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("clients/**")
				.addResourceLocations("file:" + FILES_FOLDER.toAbsolutePath().toString() + "/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	private Path createFilePath(String id, Path folder) {
		return folder.resolve(id + ".jpg");
	}

	public ResponseEntity<Object> createResponseFromImage(String folderName, String id) throws MalformedURLException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Resource file = new UrlResource(createFilePath(id, folder).toUri());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg").body(file);
	}

}

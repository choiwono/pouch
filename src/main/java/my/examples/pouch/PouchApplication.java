package my.examples.pouch;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
public class PouchApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication springApplication =
				new SpringApplication(PouchApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}


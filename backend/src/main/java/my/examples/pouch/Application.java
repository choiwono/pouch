package my.examples.pouch;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		WebServlet webServlet = new WebServlet();
		ServletRegistrationBean registration = new ServletRegistrationBean(webServlet);
		registration.addUrlMappings("/h2/*");
		return registration;
	}
}

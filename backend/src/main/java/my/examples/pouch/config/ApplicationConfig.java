package my.examples.pouch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "my.examples.pouch.domain","my.examples.pouch.service" })

@Import(MailConfig.class)
public class ApplicationConfig {
}

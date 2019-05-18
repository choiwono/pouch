package my.examples.pouch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")

// 없어도 설정을 읽을 것 같다
@Import(MailConfig.class)
public class ApplicationConfig {
}

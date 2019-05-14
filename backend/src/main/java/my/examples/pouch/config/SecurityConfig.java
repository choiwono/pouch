package my.examples.pouch.config;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.handler.AuthFailerHandler;
import my.examples.pouch.handler.AuthSuccessHandler;
import my.examples.pouch.handler.HttpLogoutSuccessHandler;
import my.examples.pouch.handler.RestAuthenticationEntryPoint;
import my.examples.pouch.security.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailerHandler authFailerHandler;
    private final HttpLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);*/
        http.headers().frameOptions().disable();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.httpBasic()
                //.and()
                //.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/h2/*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/accounts/auth").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/accounts/join").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/accounts/findpswd").permitAll()
                .antMatchers(HttpMethod.POST, "/api/accounts/emailcheck").permitAll()
                .antMatchers(HttpMethod.GET, "/api/categories/**").hasRole("USER")
                .antMatchers("/api/accounts/**").hasRole("USER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
                .loginProcessingUrl("/api/login") // 로그인 화면
                .usernameParameter("loginId")
                .passwordParameter("loginPassword")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailerHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .csrf().ignoringAntMatchers("/**");
    }
}

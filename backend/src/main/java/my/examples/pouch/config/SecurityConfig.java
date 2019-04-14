package my.examples.pouch.config;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.config.handler.AuthFailerHandler;
import my.examples.pouch.config.handler.AuthSuccessHandler;
import my.examples.pouch.config.handler.HttpLogoutSuccessHandler;
import my.examples.pouch.config.handler.RestAuthenticationEntryPoint;
import my.examples.pouch.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

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

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
            //.antMatchers(HttpMethod.GET,"/api/**").permitAll()
            .antMatchers(HttpMethod.POST,"/api/login").permitAll()
            .antMatchers("/api/user/**").hasRole("USER")
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
            .loginProcessingUrl("/api/login") // 로그인 화면
            //.loginPage("/account/login") // 사용자가 입력한 id, password가 전달되는 url경로(필터가처리)
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

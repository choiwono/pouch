package my.examples.pouch.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        //    PathRequest.toStaticResources().atCommonLocations()
//    CSS(new String[]{"/css/**"}),
//    JAVA_SCRIPT(new String[]{"/js/**"}),
//    IMAGES(new String[]{"/images/**"}),
//    WEB_JARS(new String[]{"/webjars/**"}),
//    FAVICON(new String[]{"/**/favicon.ico"});
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"))
                .requestMatchers(new AntPathRequestMatcher("/templates/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .logout() // logout설정
            .logoutRequestMatcher(new AntPathRequestMatcher("/account/logout"))
            .logoutSuccessUrl("/main")
            .permitAll().and()
            .authorizeRequests() // 인가에 대한 설정
            .antMatchers("/").hasAnyRole("USER","ADMIN")
            .antMatchers("/main").hasAnyRole("USER","ADMIN")
            .antMatchers("/link/**").hasAnyRole("USER","ADMIN")
            .antMatchers("/account/join").permitAll()
            .antMatchers("/account/welcome").permitAll()
            .antMatchers("/account/login").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/crawling/**").permitAll()
            .antMatchers("/admin/**").hasAnyRole("USER","ADMIN")
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
            .loginProcessingUrl("/account/login") // 로그인 화면
            .loginPage("/account/login") // 사용자가 입력한 id, password가 전달되는 url경로(필터가처리)
            .usernameParameter("loginId")
            .passwordParameter("loginPassword")
            .defaultSuccessUrl("/main", true)
            .failureUrl("/account/login?fail=true")
            .and().csrf().ignoringAntMatchers("/**");
    }
}

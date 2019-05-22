package my.examples.pouch.config;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.handler.AuthFailerHandler;
import my.examples.pouch.handler.AuthSuccessHandler;
import my.examples.pouch.handler.HttpLogoutSuccessHandler;
import my.examples.pouch.handler.RestAuthenticationEntryPoint;
import my.examples.pouch.security.CustomUserDetailService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        jsr250Enabled = true)
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
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/templates/**"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.httpBasic()
            .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
                .authorizeRequests()
                // 권한이 없어도 허용 (로그인, 회원가입, 회원가입시 이메일 중복 체크, 비밀번호 찾기)
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login", "/api/accounts/join", "/api/accounts/emailcheck").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/accounts/findpswd").permitAll()
                // user 권한이면 허용(token 발급, 카테고리 접근, 계정 관련 페이지)
                .antMatchers(HttpMethod.POST,"/api/accounts/auth").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/categories/**").hasRole("USER")
                .antMatchers("/api/accounts/**").hasRole("USER")
                // admin 권한이면 허용
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                // 설정한 요청 이외의 요청은 인증된 사용자만이 요청 가능
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
                .deleteCookies("JSESSIONID")
            .and()
                .csrf().ignoringAntMatchers("/**");
    }
}

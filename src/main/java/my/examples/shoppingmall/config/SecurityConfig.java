package my.examples.shoppingmall.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/main")
            .permitAll().and()
            .authorizeRequests() // 인가에 대한 설정
            .antMatchers("/api/cart/**").permitAll()
            .antMatchers("/api/cart/change").permitAll()
            .antMatchers("/api/order").permitAll()
            .antMatchers("/api/order/change").permitAll()
            .antMatchers("/api/account/**").permitAll()
            .antMatchers("/order/buy").permitAll()
            .antMatchers("/order/writeform").permitAll()
            .antMatchers("/order/directorder").permitAll()
            .antMatchers("/order/complete").permitAll()
            .antMatchers("/order/record").permitAll()
            .antMatchers("/cart/**").permitAll()
            .antMatchers("/users/join").permitAll()
            .antMatchers("/users/welcome").permitAll()
            .antMatchers("/users/login").permitAll()
            .antMatchers("/users/wishlist").hasAnyRole("USER")
            .antMatchers("/product/**").permitAll()
            .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
            //.antMatchers("/item/write").hasAnyRole("ADMIN")
            .antMatchers("/main").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
            .loginProcessingUrl("/users/login") // 로그인 화면
            .loginPage("/users/login") // 사용자가 입력한 id, password가 전달되는 url경로(필터가처리)
            .usernameParameter("loginId")
            .passwordParameter("loginPassword")
            .failureUrl("/users/login?fail=true").and().csrf().ignoringAntMatchers("/**");
    }
}

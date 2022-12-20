package ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    CustomUserDetailService customUserDetailService;
   /* @Bean
    public PasswordEncoder encoderPassword() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); // Spring Security - Post - 403 forbidden 방지
        http
            .authorizeRequests()
                // admin 페이지는 ROLE_ADMIN 일때만 match
                .antMatchers("/admin**/**").hasAuthority("ROLE_ADMIN")
                // 나머지 request는 허용 // .authenticated() 로 주면 로그인 했을시 허용  // authorization : 권한처리
                .anyRequest().permitAll()
                .and()

        // 로그인페이지 설정
            .formLogin()
                .loginPage("/login")
                .permitAll() // 로그인 페이지 접근 허용
                //.loginProcessingUrl("/loginProcess")
                .usernameParameter("email")
                .passwordParameter("pw")
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/allboard")
                .failureHandler(authenticationFailureHandler)
//                .failureUrl("/loginFail")
                .and()

            // 로그아웃
            .logout()
                .logoutSuccessUrl("/login")
                .and()

            // oauth2 설정
            .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/allboard")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }
}
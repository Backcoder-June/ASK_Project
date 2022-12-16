package ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;



  /*  @Autowired
    LoginIdPwValidator loginIdPwValidator;
*/
    @Bean
    public PasswordEncoder encoderPassword() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); // Spring Security - Post - 403 forbidden 방지
        http.authorizeRequests()
                // admin 페이지는 ROLE_ADMIN 일때만 match
                .antMatchers("/admin**/**").hasAuthority("ROLE_ADMIN")
                // 나머지 request는 허용
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
            //.loginProcessingUrl("/loginProcess")
                .usernameParameter("email")
                .passwordParameter("pw")
//        	.permitAll()
                .defaultSuccessUrl("/test")
//        	.failureHandler(AuthenticationFailureHandler)
                .failureUrl("/logout")
                .and()
                .logout()
                .logoutSuccessUrl("/login?error=1")
                // oauth2 설정
                .and()
                .oauth2Login().loginPage("/login")
                .defaultSuccessUrl("/test")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

  /*  @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginIdPwValidator);
    }*/




}
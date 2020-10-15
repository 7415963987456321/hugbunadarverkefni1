package is.hi.hbv501g.team20.taeknilaesi.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            userDetailsService(userDetailsService)
             .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.csrf().disable() //h2
        .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()

        .and()
        .formLogin().
            loginPage("/login").permitAll().
            loginProcessingUrl("/doLogin")

        .and()
            // .logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/")
         .logout()
                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                  .logoutSuccessUrl("/")
                  .invalidateHttpSession(true)        // set invalidation state when logout
                  .deleteCookies("JSESSIONID")
                  .and()
              .exceptionHandling()
                  .accessDeniedPage("/403");
        http.headers().frameOptions().disable(); //h2
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

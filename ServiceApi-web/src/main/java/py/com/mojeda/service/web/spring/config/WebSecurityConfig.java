package py.com.mojeda.service.web.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import py.com.mojeda.service.web.jwt.JWTAuthenticationEntryPoint;
import py.com.mojeda.service.web.jwt.JWTAuthenticationFilter;
import py.com.mojeda.service.web.jwt.JWTLoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"py.com.mojeda.service.web.**"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public UserSession userSession() {
        return new UserSession();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // Disable CSRF protection since tokens are immune to it
                .csrf().disable()
                // If the user is not authenticated, returns 401
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // This is a stateless application, disable sessions
                .sessionManagement()
                //                .maximumSessions(1)
                //                .maxSessionsPreventsLogin(true).and()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Security policy
                .authorizeRequests()
                // Allow anonymous access to "/" path
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/empresas").hasAuthority("ROLE_ENTERPRISE.ADD")
                // Allow anonymous access to "/login" (only POST requests)
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/updateauth/token").permitAll()
                // Any other request must be authenticated
                .anyRequest().authenticated().and()
                // Custom filter for logging in users at "/login"
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // Custom filter for authenticating users using tokens
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // Disable resource caching
                .headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(userSession());

        //auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

}

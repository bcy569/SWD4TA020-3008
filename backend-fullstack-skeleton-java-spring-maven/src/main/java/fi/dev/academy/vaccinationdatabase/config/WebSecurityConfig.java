package fi.dev.academy.vaccinationdatabase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// At the moment there is no need to customize endpoint security rules with credentials (Username-Password) because
// development of FullStack skeleton is heading for micro-architecture where a consumer can be anyone (like React SPA
// as a FrontEnd Client) with a proper API Key. Goal of this experiment will be FullStack implementation in a Cloud
// where all tiers are isolated in Docker containers, that is, orchestrated by Kubernetes. So in this face of
// development it is enough to use Spring Security default login mechanism for still unknown consumers to utilize
// http://localhost:8080/swagger-ui/
@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/login/**", "src/app/App.css",
            /*
                                    "/swagger-ui/**",
                                    "/swagger-ui/#/**",
                                    "/swagger-resources/**",
                                    "/swagger-resources/configuration/**",
                                    "/swagger-resources/configuration/ui/**",
                                    "/swagger-resources/configuration/security/**",
                                    "/v2/api-docs/**",
            */

//                        "/actuator/**").permitAll()

//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();

            /*
                            .and()
                            .formLogin()
                            .loginPage("/login") // This is not Spring Security default login-endpoint (here we need to create our own implement)
                            .defaultSuccessUrl("/user/profile/", true)
                            .permitAll()
                            .and()
                            .logout()
                            .permitAll();
            */
 //   }

}
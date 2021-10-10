package fi.dev.academy.vaccinationdatabase.config;

import fi.dev.academy.vaccinationdatabase.web_controller_rest.UserDetailServiceImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpSession;


// At the moment there is no need to customize endpoint security rules with credentials (Username-Password) because
// development of FullStack skeleton is heading for micro-architecture where a consumer can be anyone (like React SPA
// as a FrontEnd Client) with a proper API Key. Goal of this experiment will be FullStack implementation in a Cloud
// where all tiers are isolated in Docker containers, that is, orchestrated by Kubernetes. So in this face of
// development it is enough to use Spring Security default login mechanism for still unknown consumers to utilize
// http://localhost:8080/swagger-ui/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Makes possible to use @PreAuthorize("hasRole('ROLE_ADMIN')")
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // UserDetailsService loads user details. And behind the scenes:
    // AuthenticationManager --> GrantedAuthority --> AccessDecisionManager checks password.
    // Usually frameworks take user input for password, encrypts it and compare encrypted passwords hash
    // to hash which is stored into DataBase
    @Autowired
    private UserDetailServiceImplemented personDetailsService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }



    // http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html
    @Autowired
    private HttpSession session;
/*    session.setAttribute("foobBookBar", new Book() );
    Book s = session.getAttribute("book");*/



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/register", "/saveuser", "/login", "src/app/App.css", "/css/bootstrap.min.css", "/list/css/bootstrap.min.css",
                                    "/swagger-ui/**",
                                    //"/swagger-ui/#/**",
                                    "/swagger-resources/**",
                                    //"/v2/api-docs/**",
                                    "/actuator/**").permitAll()

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                            .and()
                            .formLogin()
                            //.loginPage("/login") // This is not Spring Security default login-endpoint (here we need to create our own implement)
                            .defaultSuccessUrl("/list", true)
                            //.defaultSuccessUrl("/user/profile/", true)
                            .permitAll()
                            .and()
                                .logout()
//                                .clearAuthentication(true)
//                                .deleteCookies()
//                                .invalidateHttpSession(true)
//                                .logoutSuccessUrl("/register")
//                                .logoutUrl("/register")
                                .permitAll();

    }


}

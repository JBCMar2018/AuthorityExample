package me.afua.securitydemo.security;

import me.afua.securitydemo.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    String[] administrators = {"/h2/**"};
    String [] everyone = {"/"};
    String [] authenticated = {"/loggedin"};
    String [] onlyadmin = {"/admin"};
    String [] onlyteacher = {"/teacher"};
    String []  teachersandadmin ={"/adminteacher"};


    @Autowired
    AppUserRepository userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(everyone).permitAll()

                //Logged in STUDENTS, TEACHERS AND ADMINS can see this (other logged in users can't)
                .antMatchers(authenticated).hasAnyAuthority("ADMIN","STUDENT","TEACHER")


                //Only administrators can see this

                .antMatchers(onlyadmin).hasAuthority("ADMIN")
                //Only teachers can see this

                .antMatchers(onlyteacher).hasAuthority("TEACHER")

                //Teachers and administrators can see this
                .antMatchers(teachersandadmin).hasAnyAuthority("TEACHER","ADMIN")

                .anyRequest()
                .authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout");


        //For H2, not live production
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("defaultuser").password(encoder.encode("defaultpassword")).authorities("TEACHER")
                .and()
                .withUser("administrator").password(encoder.encode("administratorpassword")).authorities("ADMIN")
                .and()
                .withUser("student").password(encoder.encode("studentpassword")).authorities("STUDENT")
                .and()
                .passwordEncoder(encoder);

        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(encoder);
    }
}

package me.afua.securitydemo.security;

import me.afua.securitydemo.models.AppRole;
import me.afua.securitydemo.models.AppUser;
import me.afua.securitydemo.repositories.AppUserRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

@Transactional
@EnableWebSecurity
public class SSUDS implements UserDetailsService {

    AppUserRepository users;
    public SSUDS(AppUserRepository userRepo) {
        this.users = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser loggingIn = users.findAppUserByUsername(s);

        if(loggingIn==null)
         throw new UsernameNotFoundException("Your login attempt was not successful, try again.");

        return new User(loggingIn.getUsername(),loggingIn.getPassword(),grantedAuthorities(loggingIn));
    }

    private Collection<? extends GrantedAuthority> grantedAuthorities(AppUser user) {
        HashSet userAuthorities = new HashSet<>();
        for(AppRole eachRole:user.getRoles())
        {
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getName()));
        }
        return userAuthorities;
    }
}

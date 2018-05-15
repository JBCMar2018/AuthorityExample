package me.afua.securitydemo.services;

import me.afua.securitydemo.models.AppRole;
import me.afua.securitydemo.models.AppUser;
import me.afua.securitydemo.repositories.AppUserRepository;
import me.afua.securitydemo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private AppUserRepository users;

    @Autowired
    private RoleService roles;

    //Add a user
    public AppUser addUser(String username, String password, String[] roleList)
    {
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        for(String eachRole:roleList)
        {
            newUser.addRole(this.roles.find(eachRole));
        }
        users.save(newUser);
        return newUser;
    }

    //Add a user with an individual role
    public AppUser addUser(String username, String password, String role)
    {
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.addRole(roles.find(role));
        users.save(newUser);
        return newUser;
    }

    public AppRole addAppRole(String rolename)
    {
        return this.roles.addRole(rolename);

    }

}

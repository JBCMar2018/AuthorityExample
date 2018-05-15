package me.afua.securitydemo.services;

import me.afua.securitydemo.models.AppRole;
import me.afua.securitydemo.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    AppRoleRepository roles;

    //Find role by name
    public AppRole find(String rolename)
    {
        return roles.findAppRoleByName(rolename);
    }

    public AppRole addRole(String rolename)
    {
        AppRole newRole = new AppRole();
        newRole.setName(rolename);
        roles.save(newRole);
        return newRole;
    }
}

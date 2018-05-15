package me.afua.securitydemo.repositories;

import me.afua.securitydemo.models.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findAppRoleByName(String name);
}

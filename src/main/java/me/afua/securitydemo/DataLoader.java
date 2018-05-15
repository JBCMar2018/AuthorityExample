package me.afua.securitydemo;

import me.afua.securitydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserService users;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting up the application. This will change to a log message. ");

        //Create Roles
        users.addAppRole("ADMIN");
        users.addAppRole("USER");
        users.addAppRole("MANAGER");

        //Create users with single administrative rights
        users.addUser("auser","apassword","ADMIN");
        users.addUser("anotheruser","nopassword","USER");

        //Add multiple roles to a user (comma-separated)
        String[]rolesToAdd={"ADMIN","MANAGER"};

        users.addUser("multipleroleuser","password",rolesToAdd);

    }
}

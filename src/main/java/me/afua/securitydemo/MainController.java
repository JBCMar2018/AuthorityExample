package me.afua.securitydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String showIndex()
    {
        return "index";
    }

    @RequestMapping("/admin")
    public String showAdminPage()
    {
        return "index";
    }

    @RequestMapping("/teacher")
    public String showTeacherPage()
    {
        return "index";
    }


    @RequestMapping("/adminteacher")
    public String showAdminTeacherPage()
    {
        return "index";
    }



    @RequestMapping("/loggedin")
    public String showLoggedInUserPage()
    {
        return "index";
    }




}



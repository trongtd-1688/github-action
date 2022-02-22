package com.sun.usermanagement.home;

import com.sun.usermanagement.common.Exception.UserManagementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public String getTitle() {
        return "Home page";
    }
    
}

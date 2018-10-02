package com.nyawqabob.bezPyatiMinutTwitter.controller;

import com.nyawqabob.bezPyatiMinutTwitter.entity.User;
import com.nyawqabob.bezPyatiMinutTwitter.exception.ServiceException;
import com.nyawqabob.bezPyatiMinutTwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RegistrationController {
    
    @Autowired
    UserService userService = new UserService();
    
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        try {
            userService.addUser(user);
        } catch (ServiceException ex) {
            model.put("message", ex.getMessage());
            return "registration";
        }
        
        return "redirect:/login";
    }
    
    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        try {
            userService.activate(code);
            model.addAttribute("message", "Activated!");
        } catch (ServiceException ex) {
            model.addAttribute("message", ex.getMessage());
        }
        return "login";
    }
    
}

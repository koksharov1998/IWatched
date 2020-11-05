package com.example.IWatched.web;

import com.example.IWatched.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(String username, String action, Model model) {
        if (action.equals("delete")){
            userService.deleteUser(username);
        }
        return "redirect:/admin";
    }
}

package com.example.project.controllers;

import com.example.project.models.PersonAccount;
import com.example.project.security.PersonAccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonAccountDetails personDetails = (PersonAccountDetails) authentication.getPrincipal();
        PersonAccount personAccount = personDetails.getPersonAccount();

        if (personAccount.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("admin", personAccount.getPerson());
            return "admin/admin";
        } else
            model.addAttribute("user", personAccount.getPerson());
        return "main/main";
    }
}
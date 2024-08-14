package com.example.project.controllers;

import com.example.project.models.Person;
import com.example.project.services.PeopleService;
import com.example.project.util.PersonAccountValidator;
import com.example.project.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final PersonAccountValidator personAccountValidator;

    @Autowired
    public AuthController(PeopleService peopleService, PersonValidator personValidator,
                          PersonAccountValidator personAccountValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.personAccountValidator = personAccountValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("person") Person person) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        personAccountValidator.validate(person.getPersonAccount(), bindingResult);
        if (bindingResult.hasErrors())
            return "auth/register";
        peopleService.save(person);
        return "redirect:/auth/login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "auth/access-denied";
    }
}

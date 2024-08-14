package com.example.project.util;

import com.example.project.models.PersonAccount;
import com.example.project.services.PersonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonAccountValidator implements Validator {
    private final PersonAccountService personAccountService;

    @Autowired
    public PersonAccountValidator(PersonAccountService personAccountService) {
        this.personAccountService = personAccountService;
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonAccount personAccount = (PersonAccount) o;
        if (personAccountService.getPersonAccountByUsername(personAccount.getUsername()).isPresent())
            errors.rejectValue("personAccount.username", "", "Пользователь с таким username уже существует");
        if (personAccountService.getPersonAccountByEmail(personAccount.getEmail()).isPresent())
            errors.rejectValue("personAccount.email", "", "Пользователь с таким email уже существует");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonAccount.class.equals(aClass);
    }
}

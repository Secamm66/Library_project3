package com.example.project.util;

import com.example.project.models.Person;
import com.example.project.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;
@Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.getPersonByName(person.getName()).isPresent() &&
                person.getPersonId() != peopleService.getPersonByName(person.getName()).get().getPersonId())
            errors.rejectValue("name", "", "Пользователь с таким именем уже существует");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }
}

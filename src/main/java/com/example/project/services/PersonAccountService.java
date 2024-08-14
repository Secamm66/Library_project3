package com.example.project.services;

import com.example.project.models.PersonAccount;
import com.example.project.repositories.PeopleRepository;
import com.example.project.repositories.PersonAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonAccountService {

    private final PersonAccountRepository personAccountRepository;

    @Autowired
    public PersonAccountService(PersonAccountRepository personAccountRepository) {
        this.personAccountRepository = personAccountRepository;
    }

    public Optional<PersonAccount> getPersonAccountByUsername(String username) {
        return personAccountRepository.findByUsername(username);
    }

    public Optional<PersonAccount> getPersonAccountByEmail(String email) {
        return personAccountRepository.findByEmail(email);
    }
}
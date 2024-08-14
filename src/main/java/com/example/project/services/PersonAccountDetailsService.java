package com.example.project.services;

import com.example.project.models.PersonAccount;
import com.example.project.repositories.PersonAccountRepository;
import com.example.project.security.PersonAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonAccountDetailsService implements UserDetailsService {
    private final PersonAccountRepository personAccountRepository;

    @Autowired
    public PersonAccountDetailsService(PersonAccountRepository personAccountRepository) {
        this.personAccountRepository = personAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<PersonAccount> personAccount = personAccountRepository.findByUsername(s);
        if (personAccount.isEmpty())
            throw new UsernameNotFoundException("Пользователя с таким именем не существует");
        return new PersonAccountDetails(personAccount.get());
    }
}

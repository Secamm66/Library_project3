package com.example.project.services;

import com.example.project.models.Book;
import com.example.project.models.Person;
import com.example.project.models.PersonAccount;
import com.example.project.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        if (person.getPersonAccount() != null) {
            PersonAccount personAccount = person.getPersonAccount();
            personAccount.setPerson(person);
            personAccount.setRole("ROLE_USER");
            personAccount.setPasswordEncoded(passwordEncoder.encode(person.getPersonAccount().getPassword()));
        }
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPersonId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Hibernate.initialize(findById(id).getBooks());
        markAsOverdueIfOver10Days(id);
        return findById(id).getBooks();
    }

    public Optional<Person> getPersonByName(String name) {
        return peopleRepository.findByName(name);
    }

    public void markAsOverdueIfOver10Days(int id) {
        long tenDaysInMilliseconds = 864000000L;
        Date currentDate = new Date();
        for (Book book : findById(id).getBooks()) {
            if (currentDate.getTime() - book.getIssuedAt().getTime() > tenDaysInMilliseconds)
                book.setOverdue(true);
        }
    }
}

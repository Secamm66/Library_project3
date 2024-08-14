package com.example.project.repositories;

import com.example.project.models.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonAccountRepository extends JpaRepository<PersonAccount, Integer> {

    Optional<PersonAccount> findByUsername(String username);
    Optional<PersonAccount> findByEmail(String email);
}

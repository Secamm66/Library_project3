package com.example.project.repositories;

import com.example.project.models.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<PersonAccount, Integer> {

}

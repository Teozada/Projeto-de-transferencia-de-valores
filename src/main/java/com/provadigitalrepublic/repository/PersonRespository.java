package com.provadigitalrepublic.repository;

import com.provadigitalrepublic.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRespository extends JpaRepository<Person, Long> {
    public  Optional<Person> findByCpf(String cpf);
}
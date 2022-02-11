package com.provadigitalrepublic.controller;


import com.provadigitalrepublic.dto.PersonDto;
import com.provadigitalrepublic.exceptions.UserException;
import com.provadigitalrepublic.exceptions.ValueException;
import com.provadigitalrepublic.model.Person;
import com.provadigitalrepublic.service.PersonService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createdPerson (@RequestBody PersonDto.Create personDto) throws Exception {
        Person person = new Person();
        person.setCpf(personDto.cpf);
        person.setName(personDto.name);
        personService.createPerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity transfer(@RequestBody PersonDto.Transfer personDto) throws Exception {
        personService.transfer(personDto.senderCpf, personDto.receiverCpf, personDto.value);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deposit(@RequestBody PersonDto.Deposit personDto) throws Exception {
        personService.deposit(personDto.receiverCpf, personDto.value);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

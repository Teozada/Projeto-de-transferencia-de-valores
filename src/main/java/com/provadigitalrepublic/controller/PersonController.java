package com.provadigitalrepublic.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.provadigitalrepublic.dto.PersonDto;
import com.provadigitalrepublic.model.Person;
import com.provadigitalrepublic.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PersonController {

    PersonService personService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createdPerson (@RequestBody PersonDto.Create personDto) throws Exception {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
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

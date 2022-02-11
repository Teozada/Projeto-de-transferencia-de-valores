package com.provadigitalrepublic.service;

import com.provadigitalrepublic.exceptions.InvalidValue;
import com.provadigitalrepublic.exceptions.UserErrosException;
import com.provadigitalrepublic.exceptions.UserException;
import com.provadigitalrepublic.exceptions.ValueException;
import com.provadigitalrepublic.model.Person;
import com.provadigitalrepublic.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRespository personRepository;

    // criando usuario
    public Person createPerson (Person person) throws Exception {

        final Optional<Person> existPerson = this.personRepository.findByCpf(person.getCpf());

        if (existPerson.isPresent()) {
            throw new UserException();
        }
        person.setBalance(0.0);
        return personRepository.save(person);
    }

    // Transferindo valor
    public void transfer(String senderCpf, String receiverCpf, Double value) throws Exception {

        final Optional<Person> senderPerson = this.personRepository.findByCpf(senderCpf);
        final Optional<Person> receiverPerson = this.personRepository.findByCpf(receiverCpf);

        if (senderPerson.isEmpty() || receiverPerson.isEmpty()) {
            throw new UserErrosException();
        }
        if (value <= 0.0) {
            throw new InvalidValue();
        }
        if (senderPerson.get().getBalance() < value) {
            throw new ValueException();
        }
        final Person sender = senderPerson.get();
        final Person receiver = receiverPerson.get();
        sender.setBalance(sender.getBalance() - value);
        receiver.setBalance(receiver.getBalance() + value);

        this.personRepository.save(sender);
        this.personRepository.save(receiver);
    }


    // Depositando valor
    public void deposit(String cpf, Double value) throws Exception {
        final Optional<Person> person = this.personRepository.findByCpf(cpf);

        if (person.isEmpty()) {
            throw new UserErrosException();
        }
        if (value > 2000) {
            throw new InvalidValue();
        }
        final Person depositPerson = person.get();
        depositPerson.setBalance(depositPerson.getBalance() + value);

        this.personRepository.save(depositPerson);
    }

}

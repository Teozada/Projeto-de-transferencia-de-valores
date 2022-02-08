package com.provadigitalrepublic.service;

import com.provadigitalrepublic.model.Person;
import com.provadigitalrepublic.repository.PersonRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRespository personRepository;

    public Person createPerson (Person person) throws Exception {

        final Optional<Person> existPerson = this.personRepository.findByCpf(person.getCpf());

        if (existPerson.isPresent()) {
            throw new Exception("Teste1");
        }
        person.setBalance(0.0);
        return personRepository.save(person);
    }

    public Person getByCpf(String cpf) throws Exception {

        final Optional<Person> existPerson = this.personRepository.findByCpf(cpf);

        if (existPerson.isEmpty()) {
            throw new Exception("Teste1");
        }
        return existPerson.get();
    }

    public List<Person> listAllPerson() {
        return personRepository.findAll();
    }

    public void transfer(String senderCpf, String receiverCpf, Double value) throws Exception {

        final Optional<Person> senderPerson = this.personRepository.findByCpf(senderCpf);
        final Optional<Person> receiverPerson = this.personRepository.findByCpf(receiverCpf);

        if (senderPerson.isEmpty() || receiverPerson.isEmpty()) {
            throw new Exception("Teste1");
        }
        if (value <= 0.0) {
            throw new Exception("Teste2");
        }
        if (senderPerson.get().getBalance() < value) {
            throw new Exception("Teste3");
        }
        final Person sender = senderPerson.get();
        final Person receiver = receiverPerson.get();
        sender.setBalance(sender.getBalance() - value);
        receiver.setBalance(receiver.getBalance() + value);

        this.personRepository.save(sender);
        this.personRepository.save(receiver);
    }

    public void deposit(String cpf, Double value) throws Exception {
        final Optional<Person> person = this.personRepository.findByCpf(cpf);

        if (person.isEmpty()) {
            throw new Exception("Teste4");
        }
        if (value > 2000) {
            throw new Exception("Teste5");
        }
        final Person depositPerson = person.get();
        depositPerson.setBalance(depositPerson.getBalance() + value);

        this.personRepository.save(depositPerson);
    }

}

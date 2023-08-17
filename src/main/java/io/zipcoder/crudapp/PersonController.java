package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository people;

    @PostMapping(value ="/people")
    ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(people.save(p), HttpStatus.CREATED);
    }

    @GetMapping(value = "/people/{id}")
    ResponseEntity <Person> getPerson(@PathVariable Long id){
        Person p = (Person) people.findAll(id);
        if(p == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping(value ="/people")
    ResponseEntity <List<Person>> getPersonList(){
        List<Person> PersonList = new ArrayList<>();
        people.findAll().forEach(PersonList::add);
        return new ResponseEntity<>(PersonList, HttpStatus.OK);
    }

    @PutMapping(value ="/people/{id}")
    ResponseEntity <Person> updatePerson(@PathVariable Person p){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value ="/people/{id}")
    void DeletePerson(@PathVariable Long id){
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

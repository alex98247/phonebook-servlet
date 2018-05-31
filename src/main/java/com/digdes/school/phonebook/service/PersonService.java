package com.digdes.school.phonebook.service;

import com.digdes.school.phonebook.model.Person;

import java.io.InputStream;
import java.util.List;

public interface PersonService {


    void convert(String filename, InputStream is);

    void add(Person person);


    Person get(Long id);

    List<Person> list();


}

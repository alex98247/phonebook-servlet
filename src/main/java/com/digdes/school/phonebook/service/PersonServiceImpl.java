package com.digdes.school.phonebook.service;

import com.digdes.school.phonebook.helper.Config;
import com.digdes.school.phonebook.model.Person;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {

    private PersonServiceImpl() {
    }

    private static PersonService INSTANCE = null;

    public static PersonService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersonServiceImpl();
        }
        return INSTANCE;
    }


    public void convert(String filename, InputStream is) {
        DBFReader reader = new DBFReader(is, Charset.forName("windows-1251"));
        try (Writer fileWriter = Files.newBufferedWriter(Paths.get(getStorePath()), Charset.forName("UTF-8"))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(fileWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Person> personList = new ArrayList<>();
            DBFRow dbfRow;
            while ((dbfRow = reader.nextRow()) != null) {
                personList.add(dbfRowToPerson(dbfRow));
            }
            beanToCsv.write(personList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Person person) {
        try (FileWriter fileWriter = new FileWriter(getStorePath(), true)) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(fileWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person get(Long id) {
        try( Reader reader = Files.newBufferedReader(Paths.get(getStorePath()))) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Person> persons = csvToBean.parse();
            Optional<Person> person = persons.stream().filter(p -> Objects.equals(p.getId(), id)).collect(Collectors.reducing((a, b) -> null));
            return person.orElse(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> list() {
        try( Reader reader = Files.newBufferedReader(Paths.get(getStorePath()))) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String getStorePath() {
        return Config.getConfig("store.path");

    }

    private Person dbfRowToPerson(DBFRow row) {
        Person p = new Person();
        p.setId(row.getLong(Person.Cols.ID));
        p.setFirstName(row.getString(Person.Cols.FIRST_NAME));
        p.setLastName(row.getString(Person.Cols.LAST_NAME));
        p.setMiddleName(row.getString(Person.Cols.MIDDLE_NAME));
        p.setDateBirth(row.getDate(Person.Cols.DATE_BIRTH));
        p.setPhoneNumber(row.getString(Person.Cols.PHONE_NUMBER));
        return p;
    }
}

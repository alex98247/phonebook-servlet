package com.digdes.school.phonebook.servlet;

import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Person person = PersonServiceImpl.getInstance().get(Long.parseLong(id));
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String personJson = gson.toJson(person);
        PrintWriter out = resp.getWriter();
        out.print(personJson);
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String middlename = req.getParameter("middlename");
        String dateBirth = req.getParameter("dateBirth");
        String phoneNumber = req.getParameter("phoneNumber");

        Person person = new Person();
        person.setId(Long.parseLong(id));
        person.setLastName(lastname);
        person.setFirstName(firstname);
        person.setMiddleName(middlename);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            person.setDateBirth(sdf.parse(dateBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        person.setPhoneNumber(phoneNumber);
        PersonServiceImpl.getInstance().add(person);
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String personJson = gson.toJson(person);
        PrintWriter out = resp.getWriter();
        out.print(personJson);
        out.flush();

    }
}

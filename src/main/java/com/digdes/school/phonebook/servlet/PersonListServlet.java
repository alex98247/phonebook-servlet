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
import java.util.List;

@WebServlet("personList")
public class PersonListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> list = PersonServiceImpl.getInstance().list();
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String personJson = gson.toJson(list);
        PrintWriter out = resp.getWriter();
        out.print(personJson);
        out.flush();
    }
}

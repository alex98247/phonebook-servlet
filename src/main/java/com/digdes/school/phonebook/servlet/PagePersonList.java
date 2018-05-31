package com.digdes.school.phonebook.servlet;

import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PagePersonList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> list = PersonServiceImpl.getInstance().list();
        req.setAttribute("personList", list);
        req.getRequestDispatcher("/WEB-INF/jsp/person_list_jstl.jsp").forward(req, resp);
    }
}

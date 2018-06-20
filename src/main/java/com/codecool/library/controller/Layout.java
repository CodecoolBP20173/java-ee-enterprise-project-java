package com.codecool.library.controller;

import com.codecool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Layout extends HttpServlet {

    private final HttpServletRequest req;
    private BookRepository bookRepository;

    @Autowired
    public Layout(HttpServletRequest req, BookRepository bookRepository) {
        this.req = req;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/layout", method = RequestMethod.GET)
    protected String doGet(Model model) {

        HttpSession session = req.getSession();

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("books", bookRepository.findAll());

        return "layout";
    }
}

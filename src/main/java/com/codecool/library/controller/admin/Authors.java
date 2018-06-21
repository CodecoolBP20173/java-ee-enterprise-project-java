package com.codecool.library.controller.admin;

import com.codecool.library.model.Author;
import com.codecool.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Authors extends HttpServlet {
    private final HttpServletRequest req;
    private final AuthorRepository bookRepositor;

    @Autowired
    public Authors(HttpServletRequest req, AuthorRepository authorRepository) {
        this.req = req;
        this.bookRepositor = authorRepository;
    }

    @RequestMapping(value = "/admin/authors", method = RequestMethod.GET)
    protected String doGet(Model model) {
        Iterable<Author> authors = bookRepositor.findAll(Sort.by("lastName", "firstName"));

        model.addAttribute("authors", authors);

        return "/components/admin/authors :: authors";
    }

}

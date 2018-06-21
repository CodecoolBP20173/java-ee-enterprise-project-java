package com.codecool.library.controller.admin;

import com.codecool.library.model.Publisher;
import com.codecool.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Publishers extends HttpServlet {
    private final HttpServletRequest req;
    private final PublisherRepository bookRepositor;

    @Autowired
    public Publishers(HttpServletRequest req, PublisherRepository publisherRepository) {
        this.req = req;
        this.bookRepositor = publisherRepository;
    }

    @RequestMapping(value = "/admin/publishers", method = RequestMethod.GET)
    protected String doGet(Model model) {
        Iterable<Publisher> publishers = bookRepositor.findAll(Sort.by("name"));

        model.addAttribute("publishers", publishers);

        return "/components/admin/publishers :: publishers";
    }

}

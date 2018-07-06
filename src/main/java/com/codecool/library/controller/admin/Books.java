package com.codecool.library.controller.admin;

import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import com.codecool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Books extends HttpServlet {
    private final HttpServletRequest req;
    private final BookRepository bookRepositor;

    @Autowired
    public Books(HttpServletRequest req, BookRepository bookRepository) {
        this.req = req;
        this.bookRepositor = bookRepository;
    }

    @RequestMapping(value = "/admin/books", method = RequestMethod.GET)
    protected String doGet(Model model) {
        Iterable<Book> books = bookRepositor.findAll(Sort.by("title"));

        Language[] languages = Language.values();
        model.addAttribute("languages", languages);
        model.addAttribute("books", books);

        return "components/admin/books :: books";
    }

}

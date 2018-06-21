package com.codecool.library.controller.admin;

import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Admin extends HttpServlet {
    private final HttpServletRequest req;

    @Autowired
    public Admin(HttpServletRequest req) {
        this.req = req;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    protected String doGet(Model model) {
        HttpSession session = req.getSession();

        model.addAttribute("categories", new String[]{"Book", "Author", "Publisher"});
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("languages", Language.values());

        return "admin";
    }
}

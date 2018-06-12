package com.codecool.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class Index extends HttpServlet {

    @Autowired
    private HttpServletRequest req;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String doGet(Model model) {

        HttpSession session = req.getSession();

        model.addAttribute("username", session.getAttribute("username"));

        return "index";
    }

}

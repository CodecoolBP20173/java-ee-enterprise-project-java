package com.codecool.library.controller.admin;

import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Admin {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    protected String adminPage(Model model) {
          model.addAttribute("username", "admin");
          model.addAttribute("languages", Language.values());

          return "admin";
    }
}

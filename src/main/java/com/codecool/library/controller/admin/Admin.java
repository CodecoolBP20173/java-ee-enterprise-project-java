package com.codecool.library.controller.admin;

import com.codecool.library.model.Language;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Admin {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    protected String adminPage(Model model) {
          model.addAttribute("username", "admin");
          model.addAttribute("languages", Language.values());

          return "admin";
    }
}

package com.codecool.library.controller.admin;

import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class Admin extends HttpServlet {
    private final HttpServletRequest req;

    @Autowired
    public Admin(HttpServletRequest req) {
        this.req = req;
    }

    @GetMapping(value = "/my-account")
    protected String myAccount() {
        return "components/admin/my-account :: my-account";
    }

    @GetMapping
    protected String doGet(Model model) {
        HttpSession session = req.getSession();
        CsrfToken csrfToken = (CsrfToken) req.getAttribute("_csrf");

        model.addAttribute("categories", new String[]{"Book", "Author", "Publisher"});
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("languages", Language.values());
        model.addAttribute("csrfName", csrfToken.getParameterName());
        model.addAttribute("csrfToken", csrfToken.getToken());
        model.addAttribute("csrfHeader", csrfToken.getHeaderName());

        return "admin";
    }
}

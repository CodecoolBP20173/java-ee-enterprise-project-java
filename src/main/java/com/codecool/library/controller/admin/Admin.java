package com.codecool.library.controller.admin;

import com.codecool.library.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

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

    @GetMapping(value = "/register-new-admin")
    protected String regiserNewAdmin() {
        return "components/admin/register-new-admin :: register-new-admin";
    }

    @GetMapping
    protected String doGet(Model model) {
        CsrfToken csrfToken = (CsrfToken) req.getAttribute("_csrf");
        Authentication authnetication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("categories", new String[]{"Book", "Author", "Publisher"});
        model.addAttribute("username", authnetication.getName());
        model.addAttribute("isSupervisor", authnetication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(com.codecool.library.model.Admin.Authorities.SUPERVISOR.toString())));
        model.addAttribute("languages", Language.values());
        model.addAttribute("csrfName", csrfToken.getParameterName());
        model.addAttribute("csrfToken", csrfToken.getToken());
        model.addAttribute("csrfHeader", csrfToken.getHeaderName());

        return "admin";
    }
}

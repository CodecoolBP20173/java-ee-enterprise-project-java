package com.codecool.library.controller.admin;

import com.codecool.library.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin"})
public class Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();

        // TODO This is pretty ugly, but it'll do for now
        session.setAttribute("username", "admin");
        if ("admin".equals(session.getAttribute("username"))) {
            context.setVariable("username", "admin");
            engine.process("admin.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/index");
        }
    }
}

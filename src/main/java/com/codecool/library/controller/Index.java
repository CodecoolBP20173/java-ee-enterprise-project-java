package com.codecool.library.controller;

import com.codecool.library.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("username", getUsername(req.getSession()));

        engine.process("index.html", context, resp.getWriter());
    }

    public static String getUsername(HttpSession session) {
        Object username = session.getAttribute("username");
        if (username != null) {
            return (String) username;
        }
        return null;
    }
}

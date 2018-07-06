package com.codecool.library.controller;

import com.codecool.library.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;

@Controller
public class Index extends HttpServlet {

    private PlaceRepository placeRepository;


    @Autowired
    public Index(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected String doGet(Model model) {

        model.addAttribute("places", placeRepository.findAll());
        return "layout";
    }
}

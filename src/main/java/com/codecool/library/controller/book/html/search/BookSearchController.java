package com.codecool.library.controller.book.html.search;

import com.codecool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import java.util.Optional;

@Controller
public class BookSearchController extends HttpServlet {


    private BookRepository bookRepository;

    @Autowired
    public BookSearchController(BookRepository bookRepository){

        this.bookRepository = bookRepository;
    }



    @GetMapping("/ui/books/search")
    public String search(Model model,@Param("q") String q, @Param("size") Integer size, @Param("page") Integer page) {

        Sort sort = Sort.by("title").ascending();

        int pageIndex = Optional.ofNullable(page).orElse(0);
        int pageSize = Optional.ofNullable(size).orElse(20);

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, sort);

        Page pageData = q == null ? bookRepository.findAll(pageRequest) : bookRepository.findAllByTitleContainingIgnoreCase(q,pageRequest);

        model.addAttribute("books", pageData.getContent());
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageNumber", pageIndex + 1);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageTotalCount", pageData.getTotalPages());
        return  "/components/search-page/book-list.html :: book-list";
    }



}

package tfip.ssf.openlibrary.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfip.ssf.openlibrary.model.book;
import tfip.ssf.openlibrary.service.bookService;

@Controller
@RequestMapping(path = "/book" , produces = MediaType.TEXT_HTML_VALUE )
public class searchController {

    @Autowired
    private bookService bookSvc;

    private final Logger logger = Logger.getLogger(searchController.class.getName());


    @GetMapping
    public String getBook(@RequestParam(required = true)String title, Model model){
        List<book> bookList = bookSvc.search(title);
        model.addAttribute("bookList", bookList);
        model.addAttribute("title",title);
        return "books";
    }
}

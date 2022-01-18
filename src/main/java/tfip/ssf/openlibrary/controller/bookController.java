package tfip.ssf.openlibrary.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import tfip.ssf.openlibrary.Repo.bookRepository;
import tfip.ssf.openlibrary.model.book;
import tfip.ssf.openlibrary.model.cached;
import tfip.ssf.openlibrary.service.bookService;

@Controller
@RequestMapping(path = "/book",produces = MediaType.TEXT_HTML_VALUE)

public class bookController {

    @Autowired
    private bookService bookSvc;

    @Autowired
    private bookRepository bookRepo;

    private final Logger logger = Logger.getLogger(searchController.class.getName());

    
    @GetMapping("/{path}")
    public String displayBook(Model model, @PathVariable(value="path") String path){
        book bookresult = new book();
        cached cached = new cached();

        if(bookRepo.getData(path).isPresent()){
            bookresult = bookSvc.getBookData(path);
            System.out.println(bookresult.getDescription());
            model.addAttribute("bookresult", bookresult);
        }else{
        bookresult = bookSvc.getBook(path);
        String value = bookSvc.convertToString(bookresult);

        bookRepo.saveRepo(path, value);
        if (bookRepo.saveRepo(path,value) == true){
            cached.setStatus("Yes");
            System.out.println(cached.getStatus());
            ;
        }else{
            cached.setStatus("No");;
        }

        model.addAttribute("bookresult", bookresult);
        model.addAttribute("cached",cached);
        }
        
        return "results";
        }

        
    }
    



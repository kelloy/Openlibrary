package tfip.ssf.openlibrary.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/book",produces = MediaType.TEXT_HTML_VALUE);
public class bookController {
    
    @GetMapping("/{key}")
    public String displayBook(){
    }
    


}

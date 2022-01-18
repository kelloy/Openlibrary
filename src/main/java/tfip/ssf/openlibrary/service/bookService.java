package tfip.ssf.openlibrary.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import tfip.ssf.openlibrary.Constants;
import tfip.ssf.openlibrary.model.book;

@Service
public class bookService {
    
    private final Logger logger = Logger.getLogger(bookService.class.getName());

    public List<book> search(String title){
        String url = UriComponentsBuilder.fromUriString(Constants.URL_OPENLIBRARY).queryParam("q", title.trim().replace(" ", "+")).queryParam("limit",Constants.SEARCH_LIMIT).toUriString();
        RequestEntity req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req,String.class);
        String Body = resp.getBody();
        logger.info(Body);
        List<book> booklist = new ArrayList();

        try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject result = reader.readObject();
            JsonArray readings = result.getJsonArray("docs");
            for(jakarta.json.JsonValue s: readings){
                book b = new book();
                JsonObject o = s.asJsonObject();
                b.setPath(o.getString("key"));
                b.setTitle(o.getString("title"));
                b.setKey(o.getString("key"));
                booklist.add(b);
            }

        }catch (IOException e){

        }
        
        return booklist;
        
    }
    
}

package tfip.ssf.openlibrary.Repo;

import java.time.Duration;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class bookRepository {
    @Autowired
    @Qualifier("BEAN_LIBRARY_CACHE")

    RedisTemplate<String,String> template;

    private static final Logger logger = Logger.getLogger(bookRepository.class.getName());

        public boolean saveRepo(String title, String value){
            Boolean result = true;
            title = NormaliseString(title);
            template.opsForValue().set(title,value,Duration.ofMinutes(10));
            return result;
        }

        public Optional<String> getData(String title){
            title = NormaliseString(title.toString());
            String result = template.opsForValue().get(title);
            return Optional.ofNullable(result);
        }   

        private String NormaliseString(String title) {
            title = title.trim().toUpperCase();
            return title;
            
        }

    }
    


package tfip.ssf.openlibrary.model;

import tfip.ssf.openlibrary.Constants;

public class book {
    private String title;
    private String key;
    private String path;
    private String description;
    private String excerpt;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public book(String title, String key, String path) {
        this.title = title;
        this.key = key;
        this.path = path;
    }


    public book() {
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        String b = path.replace("works", "book");
        this.path = b;
    }

    public void setExcerpt(String excerpt){
        this.excerpt = excerpt;
    }

    public String getExcerpt(){
        return this.excerpt;
    }
}

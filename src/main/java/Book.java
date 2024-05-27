import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer id;
    private String title;
//    private List<Chapter> chapters;
DataEngine dataEngine=new CsvDataEngine();
    public Book() {}

    public Book(Integer id, String title,Boolean save) {
        this.id = id;
        this.title = title;
//        this.chapters = Collections.emptyList();
        if(save){
            dataEngine.saveNewBook(id,title);
        }
    }

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
//        this.chapters = List.of(chapters);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

//    public List<Chapter> getChapters() {
//        return chapters;
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

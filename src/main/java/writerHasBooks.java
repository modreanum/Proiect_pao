import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class writerHasBooks {

    private Integer writerId;
    private Integer bookId;
    DataEngine dataEngine=new CsvDataEngine();

    public writerHasBooks() {}

    public writerHasBooks(Integer writerId, Integer bookId,Boolean save) {
        this.writerId = writerId;
        this.bookId = bookId;
        if(save){
            dataEngine.saveNewLink(writerId,bookId);
        }}
        public Integer getWriterId(){
            return writerId;
        }

        public Integer getBookId(){
            return bookId;
        }


    public static List<Integer> writerBookList(Integer idUser) {
        List<Integer> books = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader("src/main/java/writerHasBooks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Integer writerId = Integer.parseInt(values[0]);
                if (writerId.equals(idUser)) {

                    books.add(Integer.parseInt(values[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}

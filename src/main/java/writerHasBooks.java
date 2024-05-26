import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class writerHasBooks {
    private Number idUser;
    private Number idBook;

    public writerHasBooks() {}

    public writerHasBooks(Number idUser, Number idBook) {
        this.idUser = idUser;
        this.idBook = idBook;
        try {
            FileWriter myWriter = new FileWriter("src/main/java/links.txt", true);
            myWriter.write(idUser.toString());
            myWriter.write(',');
            myWriter.write(idBook.toString());
            myWriter.write('\n');
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

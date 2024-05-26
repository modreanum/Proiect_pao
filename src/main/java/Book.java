import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private List<Chapter> chapters;

    public Book() {}

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
        this.chapters = Collections.emptyList();
        try {
            FileWriter myWriter = new FileWriter("src/main/java/books.txt", true);
            myWriter.write(id.toString());
            myWriter.write(',');
            myWriter.write(title);
            myWriter.write(',');
            myWriter.write("");
            myWriter.write('\n');
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Book(Integer id, String title, Chapter[] chapters) {
        this.id = id;
        this.title = title;
        this.chapters = List.of(chapters);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public static int lastId() {
        int lastId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    lastId = Integer.parseInt(values[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static List<Book> getBookList() {
        Integer id1, id2;
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/links.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                book.setId(Integer.parseInt(values[0]));
                book.setTitle(values[1]);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Book getBookById(Integer id) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/books.txt"))) {
            String line;
            Book book = new Book();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                book = new Book(id, values[1]);
                                        System.out.println(values[1]);
                                        System.out.println(book.getTitle());
break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBookListWriter(Writer writer) {
        List<Integer> writerBookIds = writerHasBooks.writerBookList(writer.getId());
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/links.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Integer bookId = Integer.parseInt(values[0]);
                if (writerBookIds.contains(bookId)) {
                    Book book = new Book();
                    book.setId(bookId);
                    book.setTitle(values[1]);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataEngine implements DataEngine {
    private final String folder;
    public static final String DEFAULT_FOLDER = "src/main/resources/CSV/";

    public CsvDataEngine(String folder) {
        this.folder = folder;
    }

    public CsvDataEngine() {
        this.folder = DEFAULT_FOLDER;
    }

    @Override
    public List<User> getWriters() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.WRITER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    Integer id = Integer.parseInt(values[0]);
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    users.add(new Writer(id, fileUsername, filePassword, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getReaders() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.READER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    Integer id = Integer.parseInt(values[0]);
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    users.add(new Reader(id, fileUsername, filePassword, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.BOOK.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    Integer id = Integer.parseInt(values[0]);
                    String title = values[1];
                    books.add(new Book(id, title, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public User getWriter(String username, String password) {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.WRITER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        Writer writer =
                                new Writer(
                                        Integer.parseInt(values[0]),
                                        values[1],
                                        values[2],
                                        false);
                        System.out.println(writer.id);
                        return writer;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getWriterByName(String username) {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.WRITER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    if (fileUsername.equals(username)) {
                        Writer writer =
                                new Writer(
                                        Integer.parseInt(values[0]),
                                        values[1],
                                        values[2],
                                        false);
                        System.out.println(writer.id);
                        return writer;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public User getReader(String username, String password) {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.READER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        Reader reader =
                                new Reader(
                                        Integer.parseInt(values[0]), values[1], values[2], false);
                        return reader;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer lastUserId(Boolean isWriter) {
        int lastId = 0;
        List<User> users;
        if (isWriter) {

            users = this.getWriters();
        } else {
            users = this.getReaders();
        }
        if (users != null && !users.isEmpty()) {
            return users.get(users.size() - 1).getId();
        } else {
            return 0;
        }

    }

    public void saveNewUser(Integer id, String username, String password, Boolean isWriter) {
        List<User> users;
        String file;
        if (isWriter) {

            file = FilePaths.WRITER.getPath();
        } else {
            file = FilePaths.READER.getPath();
        }
        if (isWriter) {
            try (FileWriter writer = new FileWriter(this.folder + FilePaths.WRITER.getPath(), true)) {
                writer.append(id.toString())
                        .append(',')
                        .append(username)
                        .append(',')
                        .append(password)
                        .append(',')
                        .append('0')
                        .append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter writer = new FileWriter(this.folder + file, true)) {
                writer.append(id.toString())
                        .append(',')
                        .append(username)
                        .append(',')
                        .append(password)
                        .append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveNewBook(Integer id, String title) {


        try (FileWriter writer = new FileWriter(this.folder + FilePaths.BOOK.getPath(), true)) {
            writer.write(id.toString());
            writer.write(',');
            writer.write(title);
            writer.write('\n');
            //writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewChapter(Integer bookId, Integer chapterId, String title, String content) {

        String fileName = this.folder + "chapter_" + bookId + "_" + chapterId + ".txt";

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write(content);

        } catch (IOException ignored) {

        }
        try (FileWriter writer = new FileWriter(this.folder + FilePaths.CHAPTER.getPath(), true)) {
            writer.write(bookId.toString());
            writer.write(',');
            writer.write(chapterId.toString());
            writer.write(',');
            writer.write(title);
            writer.write('\n');

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Chapter> getChapters(Integer idBook) {

        List<Chapter> chapters = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.CHAPTER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3 && Integer.parseInt(values[0]) > -1) {
                    Integer id = Integer.parseInt(values[0]);
                    Integer id2 = Integer.parseInt(values[1]);
                    String title = values[2];
                    chapters.add(new Chapter(id, id2, title, "", false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chapters;

    }

    public Integer lastChapterId(Integer bookId) {
        int lastId = 0;
        List<Chapter> chapters;

        chapters = this.getChapters(bookId);

        if (chapters != null && !chapters.isEmpty()) {
            return chapters.get(chapters.size() - 1).getChapterId();
        } else {
            return 0;
        }
    }

    public Integer lastBookId() {
        int lastId = 0;
        List<Book> books;

        books = this.getBooks();

        if (books != null && !books.isEmpty()) {
            return books.get(books.size() - 1).getId();
        } else {
            return 0;
        }
    }


    public void saveNewLink(Integer userId, Integer bookId) {

        try (FileWriter writer = new FileWriter(this.folder + FilePaths.WRITER_HAS_BOOKS.getPath(), true)) {
            writer.write(userId.toString());
            writer.write('-');
            writer.write(bookId.toString());
            writer.write('\n');
            //writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean verifyBookOwnership(Integer writerId, Integer bookId) {
        List<writerHasBooks> links;


        links = this.getLinks();

        ObjectSearch<writerHasBooks> objectSearch = new ObjectSearch<>();
        List<writerHasBooks> matched =
                objectSearch.search(
                        links,
                        link ->
                                link.getWriterId().equals(writerId)
                                        && link.getBookId().equals(bookId));

        if (!matched.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<writerHasBooks> getLinks() {

        List<writerHasBooks> links = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.WRITER_HAS_BOOKS.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("-");
                if (values.length >= 2) {
                    Integer id = Integer.parseInt(values[0]);
                    Integer id2 = Integer.parseInt(values[1]);
                    links.add(new writerHasBooks(id, id2, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;

    }


    public Chapter getChapter(Integer bookId, Integer chapterId) {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(this.folder + FilePaths.CHAPTER.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    Integer bookId1 = Integer.parseInt(values[0]);
                    Integer chapterId1 = Integer.parseInt(values[1]);
                    String title = values[2];
                    if (bookId.equals(bookId1) && chapterId.equals(chapterId1)) {
                        String bf2= this.folder + "chapter_" + bookId + "_" + chapterId + ".txt";
                        String content = getChapterText(bf2);
                        Chapter chapter = new Chapter(bookId, chapterId, title, content, false);
                        return chapter;
                    }

                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return null;

    }

    public String getChapterText(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        }
        return contentBuilder.toString();
    }
}




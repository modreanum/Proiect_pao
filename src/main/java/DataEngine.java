import java.io.IOException;
import java.util.List;

public interface DataEngine {
    public List<User> getWriters();

    public List<User> getReaders();

    public User getWriter(String username, String password);

    public User getReader(String username, String password);

    public Integer lastUserId(Boolean isWriter);

    public void saveNewUser(Integer id, String username, String password, Boolean isWriter);
    public void saveNewChapter(Integer bookId, Integer chapterId,String title, String content);
    public void saveNewBook(Integer id, String title);
    public List<Chapter> getChapters(Integer idBook);
    public Integer lastChapterId(Integer bookId);
    public Integer lastBookId();
    public List<Book> getBooks();
    public void saveNewLink(Integer userId, Integer bookId);
    public Boolean verifyBookOwnership(Integer userId, Integer bookId);
    public List<writerHasBooks> getLinks();
public Chapter getChapter(Integer bookId,Integer chapterId);
    public String getChapterText(String filePath) throws IOException;
    public User getWriterByName(String username);


}

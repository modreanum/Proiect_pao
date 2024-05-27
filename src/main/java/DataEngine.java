import java.io.IOException;
import java.util.List;

public interface DataEngine {
     List<User> getWriters();

   List<User> getReaders();

User getWriter(String username, String password);

     User getReader(String username, String password);

     Integer lastUserId(Boolean isWriter);

     void saveNewUser(Integer id, String username, String password, Boolean isWriter);

     void saveNewChapter(
            Integer bookId, Integer chapterId, String title, List<String> content);

     void saveNewBook(Integer id, String title);

List<Chapter> getChapters(Integer idBook);

 Integer lastChapterId(Integer bookId);

  Integer lastBookId();

List<Book> getBooks();

   void saveNewLink(Integer userId, Integer bookId);

   Boolean verifyBookOwnership(Integer userId, Integer bookId);

 List<WriterHasBooks> getLinks();

 Chapter getChapter(Integer bookId, Integer chapterId);

  List<String> getChapterText(String filePath) throws IOException;
}

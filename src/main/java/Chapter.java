import java.util.List;

public class Chapter {
    private Integer bookId;
    private Integer chapterId;
    private String title;
    private List<String> content;

    public Chapter() {}

    public Chapter(Integer bookId, Integer chapterId, String title, List<String> content) {
        this.bookId = bookId;
        this.chapterId = chapterId;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public List<String> getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}

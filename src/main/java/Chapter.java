public class Chapter {
    private Integer bookId;
    private Integer chapterId;
    private String title;
    private String content;
    DataEngine dataEngine=new CsvDataEngine();
    public Chapter() {}

    public Chapter(Integer bookId, Integer chapterId, String title, String content,Boolean save) {
        this.bookId=bookId;
        this.chapterId=chapterId;
        this.title = title;
        this.content = content;
        if(save){
            dataEngine.saveNewChapter(bookId,chapterId,title,content);
        }
    }

    public String getTitle() {
        return title;
    }
    public Integer getChapterId(){
        return chapterId;
    }
    public Integer getBookId(){
        return bookId;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

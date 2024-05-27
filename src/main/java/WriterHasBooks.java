public class WriterHasBooks {

    private Integer writerId;
    private Integer bookId;

    public WriterHasBooks() {}

    public WriterHasBooks(Integer writerId, Integer bookId) {
        this.writerId = writerId;
        this.bookId = bookId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public Integer getBookId() {
        return bookId;
    }
}

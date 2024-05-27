public enum FilePaths {
    WRITER("writers.txt"),
    READER("readers.txt"),
    BOOK("books.txt"),
    CHAPTER("chapters.txt"),
    WRITER_HAS_BOOKS("writerHasBooks.txt");


    private final String path;

    FilePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

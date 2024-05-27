public class Reader extends User {

    public Reader(Integer id, String value, String value1) {
        super(id, value, value1);
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean isWriter() {
        return false;
    }
}

public class Writer extends User {

    Writer(Integer id, String username, String password) {

        super(id, username, password);
    }

    @Override
    public boolean isWriter() {
        return true;
    }
}

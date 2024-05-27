import java.util.List;

public class LoginManager implements LoginManagerInterface {
    private final DataEngine dataEngine;

    public LoginManager(DataEngine dataEngine) {
        this.dataEngine = dataEngine;
    }

    @Override
    public Integer authenticate(String username, String password) {

        Integer writerLogin = this.logIn(username, password, true);
        if (writerLogin != null) return 1;
        Integer readerLogin = this.logIn(username, password, false);
        if (readerLogin != null) return -1;
        return 0;
    }
@Override
    public Integer logIn(String username, String password, Boolean isWriter) {
        List<User> users;
        if (isWriter) {

            users = this.dataEngine.getWriters();
        } else {
            users = this.dataEngine.getReaders();
        }
        ObjectSearch<User> objectSearch = new ObjectSearch<>();
        List<User> foundUsers =
                objectSearch.search(
                        users,
                        user ->
                                user.getUsername().equals(username)
                                        && user.getPassword().equals(password));

        if (!foundUsers.isEmpty()) {
            return foundUsers.get(0).getId();
        } else {
            return null;
        }
    }
}

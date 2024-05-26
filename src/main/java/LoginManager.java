public class LoginManager implements LoginManagerInterface{
    private static final LoginManager instance=new LoginManager();

    public static LoginManager getInstance() {
        return instance;
    }

    @Override
    public Integer authenticate(String username, String password) {

        Integer writerLogin = Writer.logIn(username, password);
        if (writerLogin != null) return 1;
        Integer readerLogin = Reader.logIn(username, password);
        if (readerLogin != null) return -1;
        return 0;
    }
}

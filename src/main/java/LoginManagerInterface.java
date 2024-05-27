public interface LoginManagerInterface {

    Integer authenticate(String username, String password);
    Integer logIn(String username, String password, Boolean isWriter);
}

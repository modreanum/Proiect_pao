import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class User {
    protected Integer id;
    protected String username;
    protected String password;

    public User() {}

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getUserId(String username, String Password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return Integer.parseInt(values[0]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

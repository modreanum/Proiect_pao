import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

        try {
            FileWriter myWriter = new FileWriter("src/main/java/users.txt", true);
            myWriter.write(id.toString());
            myWriter.write(',');
            myWriter.write(username);
            myWriter.write(',');
            myWriter.write(password);
            myWriter.write('\n');
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static int lastUserId() {
        int lastId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    lastId = Integer.parseInt(values[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static Integer logIn(String username, String password) {
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

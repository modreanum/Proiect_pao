import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader extends User {

    public Reader(int i, String value, String value1) {}

    public Reader() {}

    public Integer getId() {
        return id;
    }

    public static Integer logIn(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/readers.txt"))) {
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

    public static Reader GetReader(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/writers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        Reader reader =
                                new Reader(Integer.parseInt(values[0]), values[1], values[2]);
                        return reader;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

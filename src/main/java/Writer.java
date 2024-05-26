import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Writer extends User {
    private Integer numberOfWorksWritten;

    Writer() {}

    Writer(Integer id, String username, String password, Integer numberOfWorksWritten) {
        this.numberOfWorksWritten = numberOfWorksWritten;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getNumberOfWorksWritten() {
        return this.numberOfWorksWritten;
    }

    public static Integer logIn(String username, String password) {
        List<Writer> writers = loadUsersFromFile("src/main/java/writers.txt");

        ObjectSearch<Writer> objectSearch = new ObjectSearch<>();
        List<Writer> matchedUsers = objectSearch.search(writers, user -> user.getUsername().equals(username) && user.getPassword().equals(password));

        if (!matchedUsers.isEmpty()) {
            return matchedUsers.get(0).getId();
        }else{

        return null;}

    }
    private static List<Writer> loadUsersFromFile(String filePath) {
        List<Writer> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    Integer id = Integer.parseInt(values[0]);
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    Integer fileNr=Integer.parseInt(values[3]);
                    users.add(new Writer(id, fileUsername, filePassword,fileNr));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static Writer GetWriter(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/writers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    String fileUsername = values[1];
                    String filePassword = values[2];
                    String fileWorks = values[3];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        Writer writer =
                                new Writer(
                                        Integer.parseInt(values[0]),
                                        values[1],
                                        values[2],
                                        Integer.parseInt(values[3]));
                        return writer;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

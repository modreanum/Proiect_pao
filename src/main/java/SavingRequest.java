import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavingRequest implements SavingRequestInterface{
    @Override
    public void SaveRequest(
            Integer operation_type, List<String> parameters, List<String> result) {
        if (operation_type != null) {
            try (Connection connection =
                    DriverManager.getConnection(
                            ("jdbc:mysql://localhost:3306/laborator"), "student", "student")) {
                try (Statement statement = connection.createStatement()) {
                    String createRequestIndex =
                            """
                              CREATE TABLE IF NOT EXISTS requestIndex (
                                  id INT PRIMARY KEY auto_increment,
                                  operation_type VARCHAR(20) NOT NULL,
                                  parameters VARCHAR(255),
                                  result VARCHAR(255)
                              );
                            """;
                    statement.execute(createRequestIndex);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
                try (PreparedStatement insertIntoTable =
                        connection.prepareStatement(
                                "INSERT INTO requestIndex(operation_type, parameters,"
                                        + "result)\n"
                                        + "  VALUES (?, ?, ?);")) {
                    insertIntoTable.setString(1, operation_type.toString());
                    insertIntoTable.setString(2, parameters.toString());
                    insertIntoTable.setString(3, result.toString());
                    insertIntoTable.execute();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
@Override
    public List<String> getRequests() {
        List<String> requests = new ArrayList<>();
        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/laborator", "student", "student")) {
            String query = "SELECT * FROM requestIndex";
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String operationType = resultSet.getString("operation_type");
                    String parameters = resultSet.getString("parameters");
                    String result = resultSet.getString("result");
                    String request =
                            String.format(
                                    "ID: %d, Case Type: %s, Parameters: %s, Result: %s",
                                    id, operationType, parameters, result);
                    requests.add(request);
                }
            } catch (Exception ignored) {

            }
        } catch (Exception ignored) {

        }
        return requests;
    }
}

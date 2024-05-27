import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class SavingRequest {
    public static void SaveRequest(
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
                try (PreparedStatement insertStatement =
                        connection.prepareStatement(
                                "INSERT INTO requestIndex(operation_type, parameters,"
                                        + "result)\n"
                                        + "  VALUES (?, ?, ?);")) {
                    insertStatement.setString(1, operation_type.toString());
                    insertStatement.setString(2, parameters.toString());
                    insertStatement.setString(3, result.toString());
                    insertStatement.execute();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

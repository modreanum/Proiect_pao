import java.util.List;

public interface SavingRequestInterface {
    void SaveRequest(Integer operation_type, List<String> parameters, List<String> result);

   List<String> getRequests();
}

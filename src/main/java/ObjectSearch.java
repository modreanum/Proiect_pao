import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ObjectSearch<T> {
    public List<T> search(List<T> list, Predicate<T> obj) {
        return list.stream().filter(obj).collect(Collectors.toList());
    }
}

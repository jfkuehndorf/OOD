import java.util.Set;

public interface Observer {
    void update(Set<String> currentIngredients);
}

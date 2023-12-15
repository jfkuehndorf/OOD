import java.util.Set;

public interface RecipeAlgorithm {
    void suggestRecipes(Set<String> ingredients);
    boolean hasAllIngredientsForRecipe(String recipeName, Set<String> ingredients);
}

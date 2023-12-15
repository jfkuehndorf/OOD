import java.util.Set;

public class RecipeSuggestion implements Observer {
    private RecipeAlgorithm recipeAlgorithm;
    private Set<String> currentIngredients;

    public RecipeSuggestion(RecipeAlgorithm recipeAlgorithm) {
        this.recipeAlgorithm = recipeAlgorithm;
    }

    public void update(Set<String> currentIngredients) {
        this.currentIngredients = currentIngredients;
        if (recipeAlgorithm != null) {
            recipeAlgorithm.suggestRecipes(currentIngredients);
        } else {
            System.out.println("No recipe algorithm set.");
        }
    }
}

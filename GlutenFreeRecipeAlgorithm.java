import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class GlutenFreeRecipeAlgorithm implements RecipeAlgorithm {
    private Set<String> glutenFreeRecipes;

    public GlutenFreeRecipeAlgorithm() {
        glutenFreeRecipes = new HashSet<>();
        glutenFreeRecipes.add("Beef Stir-Fry");
        glutenFreeRecipes.add("Lemon Garlic Chicken");
        glutenFreeRecipes.add("Apple Spinach Salad");
        glutenFreeRecipes.add("Carrot and Celery Soup");
        glutenFreeRecipes.add("Mushroom and Rice Pilaf");
    }

    public void suggestRecipes(Set<String> ingredients) {
        System.out.println("Suggested gluten-free recipes based on your ingredients:");
        for (String recipe : glutenFreeRecipes) {
            if (recipeCanBeMadeWithIngredients(recipe, ingredients)) {
                System.out.println(recipe);
            }
        }
    }

    public Set<String> getAllRecipes() {
        return new HashSet<>(glutenFreeRecipes);
    }

    private boolean recipeCanBeMadeWithIngredients(String recipe, Set<String> ingredients) {
        Set<String> recipeIngredients = getIngredientsForRecipe(recipe);
        return !Collections.disjoint(recipeIngredients, ingredients);
    }

    private Set<String> getIngredientsForRecipe(String recipe) {
        switch (recipe) {
            case "Beef Stir-Fry":
                return new HashSet<>(Arrays.asList("beef", "onion", "garlic", "zucchini"));
            case "Lemon Garlic Chicken":
                return new HashSet<>(Arrays.asList("chicken", "lemon", "garlic"));
            case "Apple Spinach Salad":
                return new HashSet<>(Arrays.asList("apple", "spinach", "lemon", "onion"));
            case "Carrot and Celery Soup":
                return new HashSet<>(Arrays.asList("carrot", "celery", "garlic"));
            case "Mushroom and Rice Pilaf":
                return new HashSet<>(Arrays.asList("mushroom", "rice", "onion"));
            default:
                return Collections.emptySet();
        }
    }
    public boolean hasAllIngredientsForRecipe(String recipeName, Set<String> ingredients) {
        Set<String> recipeIngredients = getIngredientsForRecipe(recipeName);
        return ingredients.containsAll(recipeIngredients);
    }
}

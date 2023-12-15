import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class VegetarianRecipeAlgorithm implements RecipeAlgorithm {
    private Set<String> vegetarianRecipes;

    public VegetarianRecipeAlgorithm() {
        vegetarianRecipes = new HashSet<>();
        vegetarianRecipes.add("Tomato Basil Pasta");
        vegetarianRecipes.add("Vegetable Stir Fry");
        vegetarianRecipes.add("Cheese and Spinach Stuffed Mushrooms");
        vegetarianRecipes.add("Eggplant Parmesan");
        vegetarianRecipes.add("Cucumber Salad");
    }

    public void suggestRecipes(Set<String> ingredients) {
        System.out.println("Suggested vegetarian recipes based on your ingredients:");
        System.out.println("Current ingredients in recipe algorithm: " + ingredients);  // Debug print
        System.out.println("Suggested recipes based on your ingredients:");
        for (String recipe : vegetarianRecipes) {
            if (recipeCanBeMadeWithIngredients(recipe, ingredients)) {
                System.out.println(recipe);
            }
        }
    }

    public Set<String> getAllRecipes() {
        return new HashSet<>(vegetarianRecipes);
    }

    private boolean recipeCanBeMadeWithIngredients(String recipe, Set<String> ingredients) {
        Set<String> recipeIngredients = getIngredientsForRecipe(recipe);
        return !Collections.disjoint(recipeIngredients, ingredients);
    }

    private Set<String> getIngredientsForRecipe(String recipe) {
    switch (recipe) {
        case "Tomato Basil Pasta":
            return new HashSet<>(Arrays.asList("tomatoes", "basil", "pasta"));
        case "Vegetable Stir Fry":
            return new HashSet<>(Arrays.asList("zucchini", "onion", "garlic", "mushroom"));
        case "Cheese and Spinach Stuffed Mushrooms":
            return new HashSet<>(Arrays.asList("cheese", "spinach", "mushroom"));
        case "Eggplant Parmesan":
            return new HashSet<>(Arrays.asList("eggplant", "cheese", "tomato"));
        case "Cucumber Salad":
            return new HashSet<>(Arrays.asList("cucumber", "tomato", "onion"));
        default:
            return Collections.emptySet();
    }
    }
    public boolean hasAllIngredientsForRecipe(String recipeName, Set<String> ingredients) {
        Set<String> recipeIngredients = getIngredientsForRecipe(recipeName);
        return ingredients.containsAll(recipeIngredients);
    }
}

import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class StandardRecipeAlgorithm implements RecipeAlgorithm {
    private Set<String> standardRecipes = new HashSet<>();

    public StandardRecipeAlgorithm() {
        standardRecipes.addAll(new VegetarianRecipeAlgorithm().getAllRecipes());
        standardRecipes.addAll(new GlutenFreeRecipeAlgorithm().getAllRecipes());
    }

    public void suggestRecipes(Set<String> ingredients) {
        System.out.println("Suggested standard recipes based on your ingredients:");
        standardRecipes.stream()
            .filter(recipe -> recipeCanBeMadeWithIngredients(recipe, ingredients))
            .forEach(System.out::println);
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


import java.util.ArrayList;
import java.util.List;



public class CompositeIngredient implements Ingredient {
    private List<Ingredient> ingredients = new ArrayList<>();

    public void add(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void remove(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void display() {
        for (Ingredient ingredient : ingredients) {
            ingredient.display();
        }
    }
}
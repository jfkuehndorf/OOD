import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Inventory implements Subject {
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyUpdate() {
        Set<String> currentIngredients = ingredients.stream()
            .filter(ing -> ing instanceof IndividualIngredient)
            .map(ing -> ((IndividualIngredient)ing).getName())
            .collect(Collectors.toSet());

        for (Observer o : observers) {
            if (o instanceof RecipeSuggestion) {
                ((RecipeSuggestion)o).update(currentIngredients);
            }
        }
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        notifyUpdate();
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        notifyUpdate();
    }

    public boolean isFull() {
        return ingredients.size() >= 5;
    }

    public void displayIngredients() {
        System.out.println("Here are your ingredients: ");
        for (Ingredient ingredient : ingredients) {
            ingredient.display();
        }
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }
    
    public void clearIngredients() {
        ingredients.clear();
        notifyUpdate();
    } 

}

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StarMarketSimulator {
    private static final int MAX_INGREDIENTS = 5;
    private static final List<String> availableIngredients = Arrays.asList(
        "Tomatoes", "Cheese", "Basil", "Chicken", "Beef", 
        "Lettuce", "Onion", "Garlic", "Eggplant", "Zucchini", 
        "Lemon", "Apple", "Carrot", "Celery", "Cucumber", 
        "Mushroom", "Rice", "Pasta", "Potato", "Spinach");

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔═╗┌┬┐┌─┐┬─┐  ╔╦╗┌─┐┬─┐┬┌─┌─┐┌┬┐  ╔═╗┬┌┬┐┬ ┬┬  ┌─┐┌┬┐┌─┐┬─┐╚═╗ │ ├─┤├┬┘  ║║║├─┤├┬┘├┴┐├┤  │   ╚═╗│││││ ││  ├─┤ │ │ │├┬┘╚═╝ ┴ ┴ ┴┴└─  ╩ ╩┴ ┴┴└─┴ ┴└─┘ ┴   ╚═╝┴┴ ┴└─┘┴─┘┴ ┴ ┴ └─┘┴└─");
        System.out.println("Welcome to Star Market Simulator!");
        System.out.println("Story: You are a measley college student who is on dinner duty for the day. Your objective: Collect ingredients from the list of ingredients and try to figure out which ingredient combinations create certain dishes. You must cook tonight!");
        System.out.println("What is your food preference? (Vegetarian, Gluten-Free, Standard)");
        String preference = scanner.nextLine();

        RecipeAlgorithm recipeAlgorithm = RecipeAlgorithmFactory.getRecipeAlgorithm(preference);
        Inventory inventory = new Inventory();
        RecipeSuggestion recipeSuggestion = new RecipeSuggestion(recipeAlgorithm);
        inventory.attach(recipeSuggestion);

        System.out.println("Available ingredients: " + availableIngredients);
        String command = "";
        while (!command.equalsIgnoreCase("done")) {
            System.out.println("Enter command (add/remove [ingredient], cook [recipe (uppercase)]):");
            command = scanner.nextLine();
            try {
                processCommand(command, inventory, recipeAlgorithm, scanner);
                triggerRandomEvent(inventory);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
    // private static void handleCompleteRecipe(String recipeName, Inventory inventory) {
    //     System.out.println("Completed recipe: " + recipeName);
    //     inventory.clearIngredients();
    //     System.out.println("You can start shopping for another recipe.");
    // }

    private static void processCommand(String command, Inventory inventory, RecipeAlgorithm recipeAlgorithm, Scanner scanner) throws InvalidCommandException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            throw new InvalidCommandException("Incomplete command. Please specify an action and its argument (use lowercase).");
        }
    
        String action = parts[0].toLowerCase();
        String argument = parts[1];
    
        if ("add".equals(action)) {
            handleAddIngredient(argument, inventory, recipeAlgorithm);
            inventory.displayIngredients();

        } else if ("remove".equals(action)) {
            handleRemoveIngredient(argument, inventory);
            inventory.displayIngredients();

         } 
        else if ("cook".equals(action)) {
            String recipeName = argument;
            if (recipeAlgorithm.hasAllIngredientsForRecipe(recipeName, getCurrentIngredients(inventory))) {
                handleCompleteRecipe(recipeName, inventory, recipeAlgorithm, scanner);
                System.out.println("You cooked " + recipeName + "!");

                System. exit(0);
            } else {
                System.out.println("Missing ingredients for " + recipeName);
            }
        }
        else {
            throw new InvalidCommandException("Unknown command: " + action);
        }
    }
    
    private static void handleAddIngredient(String ingredientName, Inventory inventory, RecipeAlgorithm recipeAlgorithm) {
        Ingredient ingredient = new IndividualIngredient(ingredientName);
        if (!inventory.isFull()) {
            inventory.addIngredient(ingredient);
            System.out.println("Added " + ingredientName);
    
            Set<String> currentIngredients = getCurrentIngredients(inventory);
            if (recipeAlgorithm != null) {
                recipeAlgorithm.suggestRecipes(currentIngredients);
            } else {
                System.out.println("Recipe algorithm is not set.");
            }
        } else {
            System.out.println("Your bag is full! Can't add more ingredients.");
        }
    }
    
    
    private static void handleRemoveIngredient(String ingredientName, Inventory inventory) {
        inventory.removeIngredient(new IndividualIngredient(ingredientName));
        System.out.println("Removed " + ingredientName);
    }
    
    private static void handleCompleteRecipe(String recipeName, Inventory inventory, RecipeAlgorithm recipeAlgorithm, Scanner scanner) {
        System.out.println("Completed recipe: " + recipeName);
        inventory.clearIngredients(); 
        System.out.println("Would you like to try and figure out a different recipe? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            System.out.println("Start shopping for a new recipe! Seems like you weren't a fan of the old one, but dinner time is slowly approaching...");
        } else {
            System.out.println("Exiting shopping simulation. You cooked so well your roommates thought it was takeout. Good job!");
            System.exit(0); 
        }
        
    }
    
    private static Set<String> getCurrentIngredients(Inventory inventory) {
        if (inventory != null && inventory.getIngredients() != null) {
            return inventory.getIngredients().stream()
                .filter(ing -> ing instanceof IndividualIngredient)
                .map(ing -> ((IndividualIngredient)ing).getName())
                .collect(Collectors.toSet());
        }
        return new HashSet<>(); 
    }

    private static void triggerRandomEvent(Inventory inventory) {
        if (random.nextFloat() < 0.1) { 
            List<Ingredient> ingredients = inventory.getIngredients();
            if (!ingredients.isEmpty()) {
                int index = random.nextInt(ingredients.size());
                Ingredient stolenIngredient = ingredients.get(index);
                if (stolenIngredient instanceof IndividualIngredient) {
                    System.out.println("Another customer in a hurry steals your " + ((IndividualIngredient) stolenIngredient).getName() + " and runs away. What a bum!");
                    inventory.removeIngredient(stolenIngredient);
                }
            }
        }
    }
}

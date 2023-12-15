public class RecipeAlgorithmFactory {
    public static RecipeAlgorithm getRecipeAlgorithm(String type) {
        if (type.equalsIgnoreCase("vegetarian")) {
            return new VegetarianRecipeAlgorithm();
        } else if (type.equalsIgnoreCase("glutenfree")) {
            return new GlutenFreeRecipeAlgorithm();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardRecipeAlgorithm();
        }
        return null;
    }
}

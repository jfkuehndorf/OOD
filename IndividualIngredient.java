public class IndividualIngredient implements Ingredient {
    private String name;

    public IndividualIngredient(String name) {
        this.name = name;
    }

    public void add(Ingredient ingredient) { /* Not applicable for leaf */ }
    public void remove(Ingredient ingredient) { /* Not applicable for leaf */ }
    public void display() {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }
}

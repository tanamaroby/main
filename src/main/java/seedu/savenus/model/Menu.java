package seedu.savenus.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.savenus.model.food.Food;
import seedu.savenus.model.food.UniqueFoodList;
import seedu.savenus.model.wallet.RemainingBudget;
import seedu.savenus.model.wallet.DaysToExpire;
import seedu.savenus.model.wallet.Wallet;

/**
 * Wraps all data at the menu level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class Menu implements ReadOnlyMenu {

    private final UniqueFoodList foods;
    private Wallet wallet = new Wallet();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new UniqueFoodList();
    }

    public Menu() {}

    /**
     * Creates an Menu using the foods in the {@code toBeCopied}
     */
    public Menu(ReadOnlyMenu toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the food list with {@code foods}.
     * {@code foods} must not contain duplicate foods.
     */
    public void setFoods(List<Food> foods) {
        this.foods.setFoods(foods);
    }

    /**
     * Resets the existing data of this {@code Menu} with {@code newData}.
     */
    public void resetData(ReadOnlyMenu newData) {
        requireNonNull(newData);

        setFoods(newData.getFoodList());
        addWallet(newData.getWallet());
    }

    //// food-level operations

    /**
     * Returns true if a food with the same identity as {@code food} exists in the menu.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foods.contains(food);
    }

    /**
     * Adds a food to the menu.
     * The food must not already exist in the menu.
     */
    public void addFood(Food p) {
        foods.add(p);
    }

    /**
     * Replaces the given food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the menu.
     * The food identity of {@code editedFood} must not be the same as another existing food in the menu.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);

        foods.setFood(target, editedFood);
    }

    /**
     * Removes {@code key} from this {@code Menu}.
     * {@code key} must exist in the menu.
     */
    public void removeFood(Food key) {
        foods.remove(key);
    }

    /**
     * Adds a wallet to the application.
     */
    public void addWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Get users wallet.
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * Set current budget.
     */
    public void setRemainingBudget(RemainingBudget newRemainingBudget) {
        wallet.setRemainingBudget(newRemainingBudget);
    }

    /**
     * Set days to expire.
     */
    public void setDaysToExpire(DaysToExpire newDaysToExpire) {
        wallet.setDaysToExpire(newDaysToExpire);
    }

    //// util methods

    @Override
    public String toString() {
        return foods.asUnmodifiableObservableList().size()
                + " Food Items: \n" + "...\n" + "Wallet: \n" + wallet.toString();
        // TODO: refine later
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foods.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Menu // instanceof handles nulls
                && foods.equals(((Menu) other).foods));
    }

    @Override
    public int hashCode() {
        return foods.hashCode();
    }
}

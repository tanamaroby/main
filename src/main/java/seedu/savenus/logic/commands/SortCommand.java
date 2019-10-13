package seedu.savenus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.savenus.logic.commands.exceptions.CommandException;
import seedu.savenus.model.Model;
import seedu.savenus.model.food.Food;
import seedu.savenus.model.food.FoodComparator;

/**
 * Sorts all the foods in the $aveNUS menu based on given criterion.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String NO_ARGUMENTS_USAGE = "Note you have entered in zero arguments:\n"
            + "Example Usage: " + COMMAND_WORD + " PRICE ASC NAME DESC";
    public static final String DUPLICATE_FIELD_USAGE = "Note you have entered a duplicate field.";
    public static final String MISSING_DIRECTION_USAGE = "Note that you need to have a direction for each field.";
    public static final String INVALID_DIRECTION_USAGE = "Note you have entered an invalid direction:\n"
            + "You are only allowed to enter the following directions:\n"
            + "ASC for Ascending, DESC for descending.";
    public static final String INVALID_FIELD_USAGE = "Note you have entered an invalid field:\n"
            + "You are only allowed to enter the following fields:\n"
            + "NAME, PRICE, CATEGORY, DESCRIPTION, LOCATION, OPENING_HOURS, RESTRICTIONS";
    public static final String MESSAGE_SUCCESS = "You have successfully sorted the food items!";

    private List<String> fields;

    /**
     * Create a simple Sort Command.
     * @param fields the list of fields.
     */
    public SortCommand(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return this.fields;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Food> foodList = model.getFilteredFoodList();
        SortedList<Food> sortedList = foodList.sorted(new FoodComparator(fields));
        model.setFoods(sortedList);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && getFields().equals(((SortCommand) other).getFields()));
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A storage that holds numbers.
 * Each times only two numbers can be pulled from the storage.
 */
public class Storage {

    // Storage's numbers range
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;

    // Holds the storage's values
    private List<Integer> list;

    /**
     * Constructor.
     * @param listSize storage size
     */
    public Storage(int listSize) {
        this.list = new ArrayList<>();
        Random random = new Random();

        // Fill the list with random values in the range (MIN_RANGE, MAX_RANGE)
        for (int i = 0; i < listSize; i++) {
            this.list.add(MIN_RANGE + random.nextInt(MAX_RANGE));
        }
    }

    /**
     * Get two values from the storage.
     * @return array holding two values if exist, null otherwise
     */
    public synchronized int[] getTwoValues() {
        if (this.list.size() <= 1) {
            return null;
        }
        int num1 = this.list.remove(0);
        int num2 = this.list.remove(0);

        return new int[] {num1, num2};
    }

    /**
     * Inserts a sum of two values into the storage.
     * @param sum value to insert
     */
    public synchronized void insertSum(int sum) {
        this.list.add(sum);
    }

    /**
     * Get all the storage values.
     * @return storage values
     */
    public List<Integer> getAll() {
        return this.list;
    }

    /**
     * Get the final summation result.
     * @return last value in storage if exists, null otherwise
     */
    public Integer getResult() {
        if (this.list.size() > 1) {
            return null;
        }

        return this.list.get(0);
    }
}

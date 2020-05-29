/**
 * Sums values that are pulled from a storage.
 */
public class SumExecutor implements Runnable {

    /**
     * The storage from which values to sum are pulled.
     */
    private Storage storage;

    /**
     * Constructor.
     * @param storage storage to pull values from
     */
    public SumExecutor(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int[] valuesToSum;

        while ((valuesToSum = storage.getTwoValues()) != null) {
            storage.insertSum(valuesToSum[0] + valuesToSum[1]);
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParallelSummation {

    /**
     * Performs a parallel summation of values according to user input.
     */
    public static void run() {

        // Get user input
        int[] userInput = getUserInput();
        int n = userInput[0];
        int m = userInput[1];

        // Create a storage
        Storage storage = new Storage(n);
        System.out.println(String.format("The storage values: %s", storage.getAll()));

        List<Thread> threads = initializeThreads(m, storage);

        try {

            // Perform parallel summation
            executeThreads(threads);
            System.out.println(String.format("The result is: %s", storage.getResult()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the threads and waits for them to finish.
     * @param threads threads to execute
     * @throws InterruptedException
     */
    private static void executeThreads(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * Get input from the user.
     * @return array containing desired storage size and number of threads
     */
    private static int[] getUserInput() {
        int storageSize = 0;
        int numOfThreads = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of storage values:");

        try {
            storageSize = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("Illegal value");
            System.exit(1);
        }

        System.out.println("Enter number of threads to be used:");

        try {
            numOfThreads = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("Illegal value");
            System.exit(1);
        }

        return new int[] {storageSize, numOfThreads};
    }

    /**
     * Creates a list of threads.
     * @param numOfThreads number of threads
     * @param storage storage for the threads to pull from
     * @return list of threads
     */
    private static List<Thread> initializeThreads(int numOfThreads, Storage storage) {
        Runnable sumExecutor = new SumExecutor(storage);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(sumExecutor));
        }

        return threads;
    }
}

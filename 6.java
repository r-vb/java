import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedSorting {
    public static void main(String[] args) {
        int n = 10000; // Specify the number of random integers to generate

        // Generate random integers and write them to "integers.txt"
        generateRandomIntegers(n, "integers.txt");

        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Create three tasks for reading and sorting integers
        SortingTask task1 = new SortingTask("integers.txt", "output1.txt");
        SortingTask task2 = new SortingTask("integers.txt", "output2.txt");
        SortingTask task3 = new SortingTask("integers.txt", "output3.txt");

        // Submit tasks to the executor
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);

        // Shutdown the executor when all tasks are completed
        executor.shutdown();

        try {
            // Wait for all threads to finish
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            // Merge and write the sorted output to "sorted_integers.txt"
            mergeAndWriteSortedFiles("sorted_integers.txt", "output1.txt", "output2.txt", "output3.txt");

            System.out.println("Sorting and merging completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Generate and write n random integers to a file
    private static void generateRandomIntegers(int n, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int num = random.nextInt();
                writer.write(Integer.toString(num));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Merge and write sorted files into a single output file
    private static void mergeAndWriteSortedFiles(String outputFileName, String... inputFiles) {
        try {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            // Initialize a reader for each input file
            List<BufferedReader> readers = new ArrayList<>();
            for (String inputFile : inputFiles) {
                readers.add(new BufferedReader(new FileReader(inputFile)));
            }

            // Populate the heap with the first element from each file
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line != null) {
                    int num = Integer.parseInt(line);
                    minHeap.add(num);
                }
            }

            // Merge and write sorted integers
            while (!minHeap.isEmpty()) {
                int min = minHeap.poll();
                writer.write(Integer.toString(min));
                writer.newLine();

                // Read the next integer from the file that provided the min value
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        int num = Integer.parseInt(line);
                        if (num == min) {
                            minHeap.add(num);
                            break;
                        }
                    }
                }
            }

            // Close readers and writer
            for (BufferedReader reader : readers) {
                reader.close();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SortingTask implements Runnable {
    private String inputFile;
    private String outputFile;

    public SortingTask(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try {
            // Read integers from the input file
            List<Integer> integers = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                integers.add(Integer.parseInt(line));
            }
            reader.close();

            // Sort the integers
            Collections.sort(integers);

            // Write sorted integers to the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (int num : integers) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

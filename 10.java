import java.io.*;
import java.util.Scanner;

public class PrimeNumberChecker {
    public static void main(String[] args) {
        generateAndCheckPrimes("numbers.txt", 100000);
    }

    private static void generateAndCheckPrimes(String fileName, int numIntegers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int primeCount = 0;

            for (int i = 0; i < numIntegers; i++) {
                int randomNum = (int) (Math.random() * Integer.MAX_VALUE);
                boolean isPrime = isPrime(randomNum);
                if (isPrime) primeCount++;
                writer.println(randomNum + " " + isPrime);
            }

            System.out.println("Number of prime numbers: " + primeCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}

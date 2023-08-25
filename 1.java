import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SetCalculatorWithJOptionPane {
    public static void main(String[] args) {
        while (true) {
            String choice = JOptionPane.showInputDialog(getMenu());
            if (choice == null) {
                JOptionPane.showMessageDialog(null, "Exiting the program. Goodbye!");
                System.exit(0);
            }

            switch (choice) {
                case "1":
                    performUnion();
                    break;
                case "2":
                    performIntersection();
                    break;
                case "3":
                    performPowerSet();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    private static String getMenu() {
        return "Set Calculator Menu:\n" +
                "1. Union\n" +
                "2. Intersection\n" +
                "3. Power Set\n" +
                "4. Exit\n" +
                "Enter your choice: ";
    }

    private static int[] readSet(String message) {
        String input = JOptionPane.showInputDialog(message);
        String[] elements = input.split("\\s+");
        int[] set = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            set[i] = Integer.parseInt(elements[i]);
        }

        return set;
    }

    private static void performUnion() {
        int[] set1 = readSet("Enter elements for the first set:");
        int[] set2 = readSet("Enter elements for the second set:");

        List<Integer> union = new ArrayList<>();

        for (int num : set1) {
            union.add(num);
        }

        for (int num : set2) {
            if (!union.contains(num)) {
                union.add(num);
            }
        }

        JOptionPane.showMessageDialog(null, "Union of the sets: " + union);
    }

    private static void performIntersection() {
        int[] set1 = readSet("Enter elements for the first set:");
        int[] set2 = readSet("Enter elements for the second set:");

        List<Integer> intersection = new ArrayList<>();

        for (int num : set1) {
            if (contains(set2, num)) {
                intersection.add(num);
            }
        }

        JOptionPane.showMessageDialog(null, "Intersection of the sets: " + intersection);
    }

    private static void performPowerSet() {
        int[] set = readSet("Enter elements for the set:");
        int n = set.length;

        int totalSubsets = 1 << n;
        StringBuilder powerSetResult = new StringBuilder("Power Set of the set:\n");

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(set[i]);
                }
            }
            powerSetResult.append(subset).append("\n");
        }

        JOptionPane.showMessageDialog(null, powerSetResult.toString());
    }

    private static boolean contains(int[] set, int num) {
        for (int i : set) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }
}

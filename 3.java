import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SGPA_Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCourses = 0;
        while (true) {
            System.out.print("Enter the number of courses: ");
            if (scanner.hasNextInt()) {
                numCourses = scanner.nextInt();
                if (numCourses >= 5 && numCourses <= 9) {
                    break;
                } else {
                    System.out.println("Number of courses must be between 5 and 9.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        double totalCredits = 0.0;
        double totalGradePoints = 0.0;

        Map<Character, Integer> gradePointsMap = new HashMap<>();
        gradePointsMap.put('S', 10);
        gradePointsMap.put('A', 9);
        gradePointsMap.put('B', 8);
        gradePointsMap.put('C', 7);
        gradePointsMap.put('D', 6);
        gradePointsMap.put('E', 4);
        gradePointsMap.put('F', 0);

        for (int i = 1; i <= numCourses; i++) {
            System.out.print("Enter grade for Course " + i + ": ");
            char grade = scanner.next().toUpperCase().charAt(0);

            if (!gradePointsMap.containsKey(grade)) {
                System.out.println("Invalid grade entered for Course " + i + ".");
                i--;
                continue;
            }

            System.out.print("Enter credits for Course " + i + ": ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                i--;
                continue;
            }

            double credits = scanner.nextDouble();

            if (credits <= 0) {
                System.out.println("Invalid credits entered for Course " + i + ".");
                i--;
                continue;
            }

            totalGradePoints += gradePointsMap.get(grade) * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            System.out.println("Total credits cannot be zero.");
            scanner.close();
            return;
        }

        double sgpa = totalGradePoints / totalCredits;
        System.out.println("SGPA: " + sgpa);

        scanner.close();
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int ia1 = inputIA("IA-1", scanner);
            int ia2 = inputIA("IA-2", scanner);
            int ia3 = inputIA("IA-3", scanner);

            System.out.print("Enter CTA score (out of 10): ");
            int cta = validateMarksInput(scanner.nextInt(), 0, 10);

            int cie = calculateCIE(ia1, ia2, ia3, cta);

            double ciePercentage = (cie / 50.0) * 100;

            boolean eligibleForSEE = ciePercentage > 40.0 && cie >= 20;

            if (eligibleForSEE) {
                System.out.print("Enter SEE Marks (out of 100) or enter 'ab' for absent or 'det' for detained: ");
                String seeInput = scanner.next();

                if (seeInput.equalsIgnoreCase("ab")) {
                    System.out.println("Grade: F (Absent for SEE exam)");
                } else if (seeInput.equalsIgnoreCase("det")) {
                    System.out.println("Grade: F (Detained for SEE exam)");
                } else {
                    try {
                        int seeMarks = Integer.parseInt(seeInput);
                        int totalMarks = seeMarks / 2 + cie;

                        String gradeLetter;
                        if (totalMarks >= 90) {
                            gradeLetter = "S";
                        } else if (totalMarks >= 80) {
                            gradeLetter = "A";
                        } else if (totalMarks >= 70) {
                            gradeLetter = "B";
                        } else if (totalMarks >= 60) {
                            gradeLetter = "C";
                        } else if (totalMarks >= 50) {
                            gradeLetter = "D";
                        } else {
                            gradeLetter = "F";
                        }

                        System.out.println("CIE Score: " + cie);
                        System.out.println("Total Marks: " + totalMarks);
                        System.out.println("Grade Letter: " + gradeLetter);
                        System.out.println("Pass/Fail: " + (gradeLetter.equals("F") ? "Fail" : "Pass"));

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for SEE marks. Please enter numeric marks, 'ab' for absent, or 'det' for detained.");
                    }
                }
            } else {
                System.out.println("CIE Score: " + cie);
                System.out.println("CIE Percentage: " + ciePercentage);
                System.out.println("Grade: F (Eligibility criteria not met)");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values.");
        }

        scanner.close();
    }

    private static int validateMarksInput(int marks, int min, int max) {
        if (marks < min || marks > max) {
            System.out.println("Invalid marks entered. Marks should be between " + min + " and " + max + ".");
            System.exit(1);
        }
        return marks;
    }


    private static int inputIA(String iaName, Scanner scanner) {
        while (true) {
            System.out.print("Enter " + iaName + " score (out of 20) or enter 'ab' for absent or 'det' for detained: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("ab") || input.equalsIgnoreCase("det")) {
                return 0; // Assigning 0 for absent or detained IA
            } else {
                try {
                    int marks = Integer.parseInt(input);
                    if (marks >= 0 && marks <= 20) {
                        return marks;
                    } else {
                        System.out.println("Error: Marks must be between 0 and 20.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a valid number or 'ab' or 'det'.");
                }
            }
        }
    }

    private static int calculateCIE(int ia1, int ia2, int ia3, int cta) {
        int[] iaScores = {ia1, ia2, ia3};
        java.util.Arrays.sort(iaScores);
        int sumOfBestTwoIAs = iaScores[2] + iaScores[1];
        int cie = sumOfBestTwoIAs + cta;
        return cie;
    }
}

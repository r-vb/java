import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AcademicToppersFinder {
    public static void main(String[] args) {
        List<Student> students = readDataFromFile("data.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Academic Toppers Finder Menu:");
            System.out.println("1. Find Topper by IA-1");
            System.out.println("2. Find Topper by IA-2");
            System.out.println("3. Find Topper by IA-3");
            System.out.println("4. Find Topper by Sum of Best Two IAs");
            System.out.println("5. Find Topper by CTA");
            System.out.println("6. Find Topper by CIE");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    findTopperByParameter(students, "IA-1");
                    break;
                case 2:
                    findTopperByParameter(students, "IA-2");
                    break;
                case 3:
                    findTopperByParameter(students, "IA-3");
                    break;
                case 4:
                    findTopperByParameter(students, "sum_of_best_two_IAs");
                    break;
                case 5:
                    findTopperByParameter(students, "CTA");
                    break;
                case 6:
                    findTopperByParameter(students, "CIE");
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Student> readDataFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    String name = data[0];
                    String usn = data[1];
                    int ia1 = Integer.parseInt(data[2]);
                    int ia2 = Integer.parseInt(data[3]);
                    int ia3 = Integer.parseInt(data[4]);
                    int sumOfBestTwoIAs = Integer.parseInt(data[5]);
                    int cta = Integer.parseInt(data[6]);
                    int cie = Integer.parseInt(data[7]);

                    students.add(new Student(name, usn, ia1, ia2, ia3, sumOfBestTwoIAs, cta, cie));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    private static void findTopperByParameter(List<Student> students, String parameter) {
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        Student topper = null;
        int maxScore = Integer.MIN_VALUE;

        for (Student student : students) {
            int score = 0;

            switch (parameter) {
                case "IA-1":
                    score = student.getIa1();
                    break;
                case "IA-2":
                    score = student.getIa2();
                    break;
                case "IA-3":
                    score = student.getIa3();
                    break;
                case "sum_of_best_two_IAs":
                    score = student.getSumOfBestTwoIAs();
                    break;
                case "CTA":
                    score = student.getCta();
                    break;
                case "CIE":
                    score = student.getCie();
                    break;
            }

            if (score > maxScore) {
                maxScore = score;
                topper = student;
            }
        }

        if (topper != null) {
            System.out.println("Topper based on " + parameter + ":");
            System.out.println("Name: " + topper.getName());
            System.out.println("USN: " + topper.getUsn());
            System.out.println(parameter + ": " + maxScore);
        } else {
            System.out.println("No topper found based on " + parameter);
        }
    }
}

class Student {
    private String name;
    private String usn;
    private int ia1;
    private int ia2;
    private int ia3;
    private int sumOfBestTwoIAs;
    private int cta;
    private int cie;

    public Student(String name, String usn, int ia1, int ia2, int ia3, int sumOfBestTwoIAs, int cta, int cie) {
        this.name = name;
        this.usn = usn;
        this.ia1 = ia1;
        this.ia2 = ia2;
        this.ia3 = ia3;
        this.sumOfBestTwoIAs = sumOfBestTwoIAs;
        this.cta = cta;
        this.cie = cie;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public int getIa1() {
        return ia1;
    }

    public int getIa2() {
        return ia2;
    }

    public int getIa3() {
        return ia3;
    }

    public int getSumOfBestTwoIAs() {
        return sumOfBestTwoIAs;
    }

    public int getCta() {
        return cta;
    }

    public int getCie() {
        return cie;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class SortingApp extends JFrame {
    private JComboBox<String> dataTypeComboBox;
    private JTextArea resultTextArea;
    private JButton sortButton;

    public SortingApp() {
        setTitle("Sorting Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        dataTypeComboBox = new JComboBox<>(new String[]{"Integer", "Double", "String"});
        panel.add(dataTypeComboBox, BorderLayout.NORTH);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        sortButton = new JButton("Sort Data");
        panel.add(sortButton, BorderLayout.SOUTH);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortData();
            }
        });

        add(panel);
    }

    private void sortData() {
        String dataType = (String) dataTypeComboBox.getSelectedItem();
        long startTime = System.currentTimeMillis();

        if ("Integer".equals(dataType)) {
            sortIntegerData();
            resultTextArea.append("Integer Type -- ");
        } else if ("Double".equals(dataType)) {
            sortDoubleData();
            resultTextArea.append("Double Type -- ");
        } else if ("String".equals(dataType)) {
            sortStringData();
            resultTextArea.append("String Type -- ");
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        resultTextArea.append("Data sorted in " + elapsedTime + " milliseconds.\n");
    }

    private void sortIntegerData() {
        int[] data = generateRandomIntegers(100000);
        Arrays.sort(data);
    }

    private void sortDoubleData() {
        double[] data = generateRandomDoubles(100000);
        Arrays.sort(data);
    }

    private void sortStringData() {
        String[] data = generateRandomStrings(100000);
        Arrays.sort(data);
    }

    private int[] generateRandomIntegers(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    private double[] generateRandomDoubles(int size) {
        double[] data = new double[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextDouble();
        }
        return data;
    }

    private String[] generateRandomStrings(int size) {
        String[] data = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = Long.toHexString(Double.doubleToLongBits(random.nextDouble()));
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SortingApp().setVisible(true);
            }
        });
    }
}

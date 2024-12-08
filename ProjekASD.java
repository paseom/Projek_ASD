import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjekASD {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Sorting Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(6, 1));

        // Panel input data
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Masukkan angka (pisahkan dengan koma):");
        JTextField inputField = new JTextField(30);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Panel pemilihan algoritma
        JPanel algoPanel = new JPanel(new FlowLayout());
        JLabel algoLabel = new JLabel("Pilih algoritma sorting:");
        String[] algoOptions = {"Bubble Sort", "Insertion Sort"};
        JComboBox<String> algoComboBox = new JComboBox<>(algoOptions);
        algoPanel.add(algoLabel);
        algoPanel.add(algoComboBox);

        // Panel pemilihan jenis sorting
        JPanel sortPanel = new JPanel(new FlowLayout());
        JLabel sortLabel = new JLabel("Pilih jenis sorting:");
        String[] sortOptions = {"Ascending", "Descending"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortLabel);
        sortPanel.add(sortComboBox);

        // Tombol untuk sorting
        JButton sortButton = new JButton("Urutkan");

        // Panel hasil
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel("Hasil: ");
        resultPanel.add(resultLabel);

        // Menambahkan panel ke frame
        frame.add(inputPanel);
        frame.add(algoPanel);
        frame.add(sortPanel);
        frame.add(sortButton);
        frame.add(resultPanel);

        // Menangani klik tombol "Urutkan"
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                if (inputText.isEmpty()) {
                    resultLabel.setText("Hasil: Masukkan angka terlebih dahulu.");
                    return;
                }

                // Mengubah input menjadi array integer
                String[] inputArray = inputText.split(",");
                int[] numbers;
                try {
                    numbers = new int[inputArray.length];
                    for (int i = 0; i < inputArray.length; i++) {
                        numbers[i] = Integer.parseInt(inputArray[i].trim());
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Hasil: Input harus berupa angka.");
                    return;
                }

                // Mendapatkan algoritma dan jenis sorting
                String selectedAlgorithm = algoComboBox.getSelectedItem().toString();
                boolean ascending = sortComboBox.getSelectedItem().equals("Ascending");

                // Menjalankan algoritma yang dipilih
                if (selectedAlgorithm.equals("Bubble Sort")) {
                    bubbleSort(numbers, ascending);
                } else if (selectedAlgorithm.equals("Insertion Sort")) {
                    insertionSort(numbers, ascending);
                }

                // Menampilkan hasil
                StringBuilder result = new StringBuilder("Hasil: ");
                for (int num : numbers) {
                    result.append(num).append(" ");
                }
                resultLabel.setText(result.toString());
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Fungsi Bubble Sort
    public static void bubbleSort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ascending ? (arr[j] > arr[j + 1]) : (arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Fungsi Insertion Sort
    public static void insertionSort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && (ascending ? (arr[j] > key) : (arr[j] < key))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

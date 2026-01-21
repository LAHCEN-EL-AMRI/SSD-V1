import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FileLoader {

    private JTable table;
    private DefaultTableModel tableModel;

    public FileLoader(JTable table) {
        this.table = table;
        this.tableModel = (DefaultTableModel) table.getModel();
        openFileDialog();
    }

    private void openFileDialog() {
        JFileChooser fileChooser = new JFileChooser();

        // Ouvrir directement dans le dossier "File" du projet
        java.io.File fileFolder = new java.io.File("File");
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        fileChooser.setCurrentDirectory(fileFolder);

        fileChooser.setDialogTitle("Select a .dat file");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            readFile(selectedFile);
        }
    }

    private void readFile(java.io.File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            ArrayList<Double> valuesN = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    for (String part : parts) {
                        try {
                            double val = Double.parseDouble(part);
                            valuesN.add(val);
                        } catch (NumberFormatException e) {
                            // ignorer les valeurs non numériques
                        }
                    }
                }
            }

            // Forcer le point comme séparateur décimal
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');

            DecimalFormat df = new DecimalFormat("#0.00", symbols);

            // Remplir le tableau
            tableModel.setRowCount(0);
            int index = 1;

            for (double n : valuesN) {
                double deltaN = Math.sqrt(n);
                tableModel.addRow(new Object[]{
                        index++,
                        n,
                        df.format(deltaN)   // ex: 12.34
                });
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading file: " + e.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

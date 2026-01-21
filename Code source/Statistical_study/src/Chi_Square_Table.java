import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Chi_Square_Table extends JFrame {

    private static final long serialVersionUID = 1L;

    public Chi_Square_Table() {
        setTitle("Chi-Square Critical Values Table");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ============================
        // Définition des colonnes et des données
        // ============================
        String[] columns = {"df", "0.995", "0.975", "0.95", "0.90", "0.10", "0.05", "0.025", "0.01", "0.005"};

        Object[][] data = {
            {1, 0.000, 0.000, 0.004, 0.016, 2.706, 3.841, 5.024, 6.635, 7.879},
            {2, 0.010, 0.051, 0.103, 0.211, 4.605, 5.991, 7.378, 9.210, 10.597},
            {3, 0.072, 0.216, 0.352, 0.584, 6.251, 7.815, 9.348, 11.345, 12.838},
            {4, 0.207, 0.484, 0.711, 1.064, 7.779, 9.488, 11.143, 13.277, 14.860},
            {5, 0.412, 0.831, 1.145, 1.610, 9.236, 11.070, 12.833, 15.086, 16.750},
            {6, 0.676, 1.237, 1.635, 2.204, 10.645, 12.592, 14.449, 16.812, 18.548},
            {7, 0.989, 1.690, 2.167, 2.833, 12.017, 14.067, 16.013, 18.475, 20.278},
            {8, 1.344, 2.180, 2.733, 3.490, 13.362, 15.507, 17.535, 20.090, 21.955},
            {9, 1.735, 2.700, 3.325, 4.168, 14.684, 16.919, 19.023, 21.666, 23.589},
            {10, 2.156, 3.247, 3.940, 4.865, 15.987, 18.307, 20.483, 23.209, 25.188},
            {11, 2.603, 3.816, 4.575, 5.578, 17.275, 19.675, 21.920, 24.725, 26.757},
            {12, 3.074, 4.404, 5.226, 6.304, 18.549, 21.026, 23.337, 26.217, 28.299},
            {13, 3.565, 5.009, 5.892, 7.042, 19.812, 22.362, 24.736, 27.688, 29.819},
            {14, 4.075, 5.629, 6.571, 7.790, 21.064, 23.685, 26.119, 29.141, 31.319},
            {15, 4.601, 6.262, 7.261, 8.547, 22.307, 25.000, 27.488, 30.578, 32.801},
            {16, 5.142, 6.908, 7.962, 9.312, 23.542, 26.296, 28.845, 31.999, 34.267},
            {17, 5.697, 7.564, 8.672, 10.110, 24.769, 27.587, 30.191, 33.409, 35.718},
            {18, 6.265, 8.231, 9.390, 10.868, 25.989, 28.869, 31.526, 34.805, 37.156},
            {19, 6.844, 8.907, 10.117, 11.651, 27.204, 30.144, 32.852, 36.191, 38.582},
            {20, 7.434, 9.591, 10.851, 12.443, 28.412, 31.410, 34.170, 37.566, 40.000}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // <-- Ajuste les colonnes automatiquement
        table.setFillsViewportHeight(true); // <-- Permet de remplir l'espace vertical

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chi-Square Critical Values"));
        scrollPane.setPreferredSize(new Dimension(850, 400));

        // ============================
        // Layout principal
        // ============================
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}

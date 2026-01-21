import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Infos extends JFrame {

    private static final long serialVersionUID = 1L;

    // Champs texte
    private JTextField txtUser, txtLab, txtDetector, txtSource, txtActivity,
            txtDate, txtVoltage, txtTime, txtRepetitions, txtDeadTime, txtSaturation;

    // Zone texte
    private JTextArea txtOtherInfo;

    private final File dataFile = new File("Data/dat.dat");

    public Infos() {

        setTitle("Additional Information");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // Labels & TextFields
        // =========================

        addLabel("User Name:", 20, 20);
        txtUser = addField(180, 20);

        addLabel("Laboratory:", 20, 60);
        txtLab = addField(180, 60);

        addLabel("Detector Type:", 20, 100);
        txtDetector = addField(180, 100);

        addLabel("Radioactive Source Name:", 20, 140);
        txtSource = addField(180, 140);

        addLabel("Activity (μCi):", 20, 180);
        txtActivity = addField(140, 180, 100);

        addLabel("Production Date:", 260, 180);
        txtDate = addField(380, 180, 180);

        addLabel("Applied Voltage (V):", 20, 220);
        txtVoltage = addField(180, 220);

        addLabel("Counting Time (s):", 20, 260);
        txtTime = addField(180, 260);

        addLabel("Number of Repetitions:", 20, 300);
        txtRepetitions = addField(180, 300);

        addLabel("Dead Time (μs):", 20, 340);
        txtDeadTime = addField(180, 340);

        addLabel("Saturation (cpm):", 20, 380);
        txtSaturation = addField(180, 380);

        // =========================
        // Other additional information
        // =========================

        JLabel lblOther = new JLabel("Other additional information:");
        lblOther.setBounds(20, 420, 250, 25);
        add(lblOther);

        txtOtherInfo = new JTextArea();
        txtOtherInfo.setLineWrap(true);
        txtOtherInfo.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtOtherInfo);
        scrollPane.setBounds(20, 450, 540, 60);
        add(scrollPane);

        // =========================
        // Buttons
        // =========================

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(180, 520, 100, 30);
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(300, 520, 100, 30);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            saveToFile();
            dispose(); // 🔹 fermer la fenêtre après sauvegarde
        });

        // 🔹 Charger automatiquement les données
        loadFromFile();

        setVisible(true);
    }

    // =========================
    // UI helpers
    // =========================

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 200, 25);
        add(lbl);
    }

    private JTextField addField(int x, int y) {
        return addField(x, y, 380);
    }

    private JTextField addField(int x, int y, int width) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, width, 25);
        add(txt);
        return txt;
    }

    // =========================
    // Lecture ligne par ligne
    // =========================

    private void loadFromFile() {
        if (!dataFile.exists()) return;

        List<JTextField> fields = getFieldsInOrder();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            int index = 0;
            StringBuilder otherText = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (index < fields.size()) {
                    fields.get(index).setText(line.trim());
                } else {
                    otherText.append(line).append("\n");
                }
                index++;
            }

            txtOtherInfo.setText(otherText.toString().trim());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error reading file",
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================
    // Écriture ligne par ligne
    // =========================

    private void saveToFile() {
        List<JTextField> fields = getFieldsInOrder();

        try {
            dataFile.getParentFile().mkdirs();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {

                // Champs simples
                for (JTextField field : fields) {
                    bw.write(field.getText());
                    bw.newLine();
                }

                // Zone texte (multiligne)
                if (!txtOtherInfo.getText().isEmpty()) {
                    String[] lines = txtOtherInfo.getText().split("\\R");
                    for (String line : lines) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving file",
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================
    // Ordre EXACT des champs
    // =========================

    private List<JTextField> getFieldsInOrder() {
        List<JTextField> list = new ArrayList<>();

        list.add(txtUser);
        list.add(txtLab);
        list.add(txtDetector);
        list.add(txtSource);
        list.add(txtActivity);
        list.add(txtDate);
        list.add(txtVoltage);
        list.add(txtTime);
        list.add(txtRepetitions);
        list.add(txtDeadTime);
        list.add(txtSaturation);

        return list;
    }
}

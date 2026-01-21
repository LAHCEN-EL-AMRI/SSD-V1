import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class Chi2_Comment {

    /**
     * Méthode pour générer le commentaire en fonction de la valeur de probabilité
     */
    public static void showComment() {
        if (Main.textField_8 == null || Main.textField_8.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No probability value found in textField_8.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

     // Récupérer la valeur saisie et la convertir en double
        String probText = Main.textField_8.getText().trim();
        double pValue;
        String comment;

        try {
            pValue = Double.parseDouble(probText);

            // Générer le commentaire selon la p-value
            if (pValue >= 0.95) {
                comment = "Good agreement with theory.";
            } else if (pValue >= 0.90) {
                comment = "Acceptable agreement.";
            } else if (pValue >= 0.05) {
                comment = "Poor agreement, caution required.";
            } else {
                comment = "Hypothesis rejected at 5% significance level.";
            }

        } catch (NumberFormatException e) {
            // Si la saisie n'est pas un nombre valide
            comment = "Invalid probability value.";
        }
        

        // Afficher le commentaire dans le JLabel lblNewLabel_7 en rouge
        if (Main.lblNewLabel_7 != null) {
            Main.lblNewLabel_7.setText(comment);
            Main.lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
            Main.lblNewLabel_7.setForeground(Color.RED); // couleur rouge
        } else {
            // Si le JLabel n'est pas défini, afficher dans une boîte de dialogue
            JOptionPane.showMessageDialog(null, comment, "Chi² Comment", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

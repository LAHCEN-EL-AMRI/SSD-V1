import java.awt.Color;
import java.awt.Font;

public class Ecart_Coment {

    /**
     * Compare σ_théorique et σ_expérimental et affiche un commentaire
     */
    public static void showComment() {
        try {
            // Vérification que les champs et label existent
            if (Main.textField_3 == null || Main.textField_4 == null || Main.lblNewLabel_14 == null) {
                return;
            }

            // Lire les valeurs
            double sigmaTheo = Double.parseDouble(Main.textField_3.getText().trim().replace(",", "."));
            double sigmaExp  = Double.parseDouble(Main.textField_4.getText().trim().replace(",", "."));

            String comment;

            // Calculer l’écart relatif
            double relativeDiff = Math.abs(sigmaExp - sigmaTheo) / sigmaTheo;

            // Déterminer le commentaire en fonction de l’écart
            if (relativeDiff < 0.05) { // moins de 5%
                comment = "Experimental result closely matches theoretical σ. Detector is very consistent.";
            } else if (relativeDiff < 0.10) { // 5-10%
                comment = "Experimental σ slightly deviates from theoretical σ. Detector is consistent.";
            } else if (relativeDiff < 0.20) { // 10-20%
                comment = "Moderate deviation observed. Interpret detector results with caution.";
            } else if (relativeDiff < 0.30) { // 20-30%
                comment = "Significant deviation. Detector may have performance issues.";
            } else { // >30%
                comment = "Large deviation! Detector performance is unreliable.";
            }

            // Afficher le commentaire en rouge
            Main.lblNewLabel_14.setText(comment);
            Main.lblNewLabel_14.setForeground(Color.RED);
            Main.lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 12));

        } catch (Exception e) {
            Main.lblNewLabel_14.setText("Error computing σ comparison.");
            Main.lblNewLabel_14.setForeground(Color.RED);
        }
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.*;
import javax.crypto.SecretKey;
import javax.swing.*;

public class ViewDataPage extends JPanel {

    public ViewDataPage(MainPanel mainPanel) {
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Données sauvegardées", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Zone de texte pour afficher les données
        JTextArea dataArea = new JTextArea(10, 40);
        dataArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dataArea);
        add(scrollPane, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action du bouton retour
        backButton.addActionListener((ActionEvent e) -> mainPanel.showPage("HomePage"));

        // Lire et déchiffrer les données depuis le fichier
        try {
            Path filePath = Paths.get("basedonner.txt");
            if (Files.exists(filePath)) {
                byte[][] fileData = FileEncryptionUtil.readEncryptedFile("basedonner.txt");
                byte[] iv = fileData[0];
                byte[] encryptedData = fileData[1];

                // Utilisation de la clé fixe
                SecretKey key = FileEncryptionUtil.getFixedKey();

                // Décryptage des données
                String decryptedData = FileEncryptionUtil.decrypt(encryptedData, key, iv);

                // Ajouter les données décryptées à la zone de texte
                dataArea.append(decryptedData + "\n");
            } else {
                dataArea.append("Aucune donnée enregistrée.\n");
            }
        } catch (Exception e) {
            dataArea.append("Erreur : " + e.getMessage() + "\n");
        }
    }
}

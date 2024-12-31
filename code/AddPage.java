import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.swing.*;

public class AddPage extends JPanel {
    private static final String FILE_NAME = "basedonner.txt";

    public AddPage(MainPanel mainPanel) {
        setLayout(new BorderLayout());

        // Formulaire
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel platformLabel = new JLabel("Nom de la plateforme :");
        JTextField platformField = new JTextField();
        platformField.setPreferredSize(new Dimension(150, 20));

        JLabel nameLabel = new JLabel("Identifiant :");
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 20));

        JLabel passwordLabel = new JLabel("Mot de passe :");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150, 20));

        JPanel platformPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        platformPanel.add(platformLabel);
        platformPanel.add(platformField);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        formPanel.add(platformPanel);
        formPanel.add(namePanel);
        formPanel.add(passwordPanel);

        add(formPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton submitButton = new JButton("Valider");

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        submitButton.addActionListener((ActionEvent e) -> {
            String platform = platformField.getText().trim();
            String name = nameField.getText().trim(); // Peut être vide
            String password = new String(passwordField.getPassword()).trim();

            if (platform.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires (Plateforme et Mot de passe).",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Génération de l'IV
                    SecureRandom random = new SecureRandom();
                    byte[] iv = new byte[16];
                    random.nextBytes(iv);

                    // Utilisation de la clé fixe
                    SecretKey key = FileEncryptionUtil.getFixedKey();

                    // Préparation des données à crypter
                    String data = "Plateforme : " + platform + " | " +
                            "Identifiant : " + (name.isEmpty() ? "" : name) + " | " +
                            "Mot de passe : " + password;

                    // Cryptage des données
                    byte[] encryptedData = FileEncryptionUtil.encrypt(data, key, iv);

                    // Sauvegarde des données cryptées
                    FileEncryptionUtil.saveEncryptedFile(FILE_NAME, encryptedData, iv);

                    // Réinitialisation des champs après une sauvegarde
                    platformField.setText("");
                    nameField.setText("");
                    passwordField.setText("");

                    JOptionPane.showMessageDialog(this, "Les données ont été sauvegardées avec succès.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde : " + ex.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener((ActionEvent e) -> mainPanel.showPage("HomePage"));
    }
    
}

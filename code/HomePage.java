import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HomePage extends JPanel {

    public HomePage(MainPanel mainPanel) {
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Gestionnaire de mot de passe", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 45, 65));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Image ou logo au centre
        JLabel logoLabel = new JLabel();
        ImageIcon logo = new ImageIcon("logo.png"); // Assurez-vous que "logo.png" est dans le répertoire de travail
        logoLabel.setIcon(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        // Boutons stylisés
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton addButton = new JButton("Ajouter des données");
        styleButton(addButton);
        addButton.addActionListener((ActionEvent e) -> mainPanel.showPage("AddPage"));

        JButton viewDataButton = new JButton("Afficher les données");
        styleButton(viewDataButton);
        viewDataButton.addActionListener((ActionEvent e) -> mainPanel.showPage("ViewDataPage"));

        JButton quitButton = new JButton("Quitter");
        styleButton(quitButton);
        quitButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showOptionDialog(
                this,
                "Voulez-vous vraiment quitter ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // Icône par défaut
                new Object[]{"Oui", "Non"}, // Options personnalisées
                "Non" // Option par défaut
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewDataButton);
        buttonPanel.add(quitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);

        // Arrière-plan personnalisé
        setBackground(new Color(245, 245, 245)); // Couleur douce pour l'arrière-plan
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(60, 120, 216));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HomePage extends JPanel {

    public HomePage(MainPanel mainPanel) {
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Gestionnaire de mots de passe", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Ajouter");
        JButton quitButton = new JButton("Quitter");

        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);
        
        // Bouton pour afficher les données
        JButton viewDataButton = new JButton("Afficher les données");
        viewDataButton.addActionListener((ActionEvent e) -> mainPanel.showPage("ViewDataPage"));
        buttonPanel.add(viewDataButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        addButton.addActionListener((ActionEvent e) -> mainPanel.showPage("AddPage"));
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
    }
}

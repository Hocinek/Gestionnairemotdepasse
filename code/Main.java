import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialisation de la fenêtre principale
            JFrame frame = new JFrame("Application de gestion");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Ajouter le panneau principal contenant les différentes pages
            frame.setContentPane(new MainPanel());

            // Rendre la fenêtre visible
            frame.setVisible(true);
        });
    }
}

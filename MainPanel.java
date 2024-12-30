import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

    private CardLayout cardLayout;

    public MainPanel() {
        // Utilisation d'un CardLayout pour gérer les pages
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Ajouter les panneaux
        add(new HomePage(this), "HomePage");
        add(new AddPage(this), "AddPage");
        add(new ViewDataPage(this), "ViewDataPage"); // Page pour afficher les données

        // Afficher la page d'accueil par défaut
        cardLayout.show(this, "HomePage");
    }

    public void showPage(String pageName) {
        cardLayout.show(this, pageName);
    }
}

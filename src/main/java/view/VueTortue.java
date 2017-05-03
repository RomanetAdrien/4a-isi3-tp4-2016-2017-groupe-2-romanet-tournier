package view;

import controller.Controller;
import controller.TortueMove;
import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Quentin on 12/04/2017.
 */
public class VueTortue extends JFrame implements ActionListener{
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);
    private static final int NOACTION = -1;
    public static final int HAUTEUR = 600;
    public static final int LARGEUR = 400;

    private JTextField inputValue;
    private static Controller controller;
    private VueDessin vueDessin;

    public static void main(String[] args) {
        controller = new Controller();
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                VueTortue fenetre = new VueTortue();
                fenetre.setVisible(true);
            }
        });

    }
    private void quitter() {
        System.exit(0);
    }

    public VueTortue() {
        super("un logo tout simple");
        vueDessin = new VueDessin();
        controller.setObserver(vueDessin);
        logoInit();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel, "North");

        addButton(toolBar, "Effacer", "Nouveau dessin", "/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        toolBar.add(inputValue);
        addButton(toolBar, "Avancer", "Avancer " + getInputValue(), null);
        addButton(toolBar, "Droite", "Droite 45", null);
        addButton(toolBar, "Gauche", "Gauche 45", null);
        addButton(toolBar, "Lever", "Lever Crayon", null);
        addButton(toolBar, "Baisser", "Baisser Crayon", null);
        addButton(toolBar, "Nouvelle", "Nouvelle Tortue", null);

        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                "vert", "gris clair", "magenta", "orange",
                "gris", "rose", "jaune"};

        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int n = cb.getSelectedIndex();
                controller.setCouranteColor(n);
            }
        });

        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", NOACTION);
        addMenuItem(menuCommandes, "Droite", "Droite", NOACTION);
        addMenuItem(menuCommandes, "Gauche", "Gauche", NOACTION);
        addMenuItem(menuCommandes, "Lever Crayon", "Lever", NOACTION);
        addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", NOACTION);
        addMenuItem(menuCommandes, "Nouvelle Tortue", "Nouvelle", NOACTION);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", NOACTION);
        addMenuItem(menuHelp, "A propos", "About", NOACTION);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // les boutons du bas
        JPanel panel2 = new JPanel(new GridLayout());
        JButton b20 = new JButton("Carre");
        panel2.add(b20);
        b20.addActionListener(this);
        JButton b21 = new JButton("Poly");
        panel2.add(b21);
        b21.addActionListener(this);
        JButton b22 = new JButton("Spirale");
        panel2.add(b22);
        b22.addActionListener(this);

        getContentPane().add(panel2,"South");


        vueDessin.setBackground(Color.white);
        vueDessin.setSize(new Dimension(HAUTEUR,LARGEUR));
        vueDessin.setPreferredSize(new Dimension(HAUTEUR,LARGEUR));


        getContentPane().add(vueDessin,"Center");

        // Deplacement de la tortue au centre de la feuille
        controller.init(HAUTEUR/2, LARGEUR/2);
        TortueMove tm = new TortueMove(controller);
        new Thread(tm).start();
        pack();
        setVisible(true);
    }


    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        }
        else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            }
            else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(this);
    }

    public String getInputValue(){
        String s = inputValue.getText();
        return(s);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.doAction(e);
    }
}

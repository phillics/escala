// Part of Escala.
// Written by Barath Tirumala.

package escala;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu{

    //declare some variables

    private Game game;
    private JFrame frame;
    private static int width = 360;
    private static int height = 240;
    private final static double SCALE = 1.5;
    public static int scaledWidth = (int) (width * SCALE);
    public static int scaledHeight = (int) (height * SCALE);
    public static int gameWidth = 1600;
    public static int gameHeight = 900;
    JButton play = new JButton("PLAY");
    JButton set = new JButton("SETTINGS");
    JButton cred = new JButton("CREDITS");
    JButton back = new JButton("BACK");
    JButton back2 = new JButton("BACK");
    JButton easy = new JButton("EASY");
    JButton backfromDiff = new JButton("BACK");
    JButton medium = new JButton("MEDIUM");
    JButton hard = new JButton("HARD");
    JButton exit = new JButton("EXIT");
    JPanel cards = new JPanel(new CardLayout());

    Color background = Color.decode("#e6ffe6");
    Color buttonCol = Color.decode("#33adff");



    /**
     * Creates new form Start
     */
    public Menu(Game game) {
        //init frame
        this.game = game;
        game.setFrame(new JFrame());
        frame = game.getFrame();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Escala");
        frame.setSize( scaledWidth, scaledHeight);

        //center frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);

        //add various pages into the "cardstack" to then switch between the screens
        addIndexCard(cards);
        addDiffCard(cards);
        addSetCard(cards);
        addCredCard(cards);
        addGameCard(cards);

        frame.add(cards);

        //tie buttons to their respective functions
        play.addActionListener(e -> toDiff(frame));
        back.addActionListener(e -> toMenu(frame));
        back2.addActionListener(e -> toMenu(frame));
        backfromDiff.addActionListener(e -> toMenu(frame));
        set.addActionListener(e -> toSet(frame));
        cred.addActionListener(e -> toCred(frame));
        exit.addActionListener(e -> quit(frame));
        easy.addActionListener(e -> startGameEasy(frame));
        medium.addActionListener(e -> startGameMedium(frame));
        hard.addActionListener(e -> startGameHard(frame));

        //show screen
        frame.setVisible(true);
    }

    //add start menu
    public void addIndexCard(Container pane){
        JPanel index = new JPanel();
        index.setLayout(null);

        index.setOpaque(true);
        index.setBackground(background);

        JLabel title = new JLabel("ESCALA");
        title.setFont(new Font("Serif", Font.PLAIN, 50));
        title.setBounds(170, 40, 220, 60);
        play.setBounds(200, 120, 120, 30);
        set.setBounds(200, 160, 120, 30);
        cred.setBounds(200, 200, 120, 30);
        exit.setBounds(200, 240, 120, 30);


        index.add(title);
        index.add(play);
        index.add(set);
        index.add(cred);
        index.add(exit);

        play.setBackground(buttonCol);
        set.setBackground(buttonCol);
        cred.setBackground(buttonCol);
        exit.setBackground(buttonCol);

        pane.add(index, "INDEX");

        //Add background and color scheme

    }

    //add difficulty menu
    public void addDiffCard(Container pane){
        JPanel diff = new JPanel();
        diff.setLayout(null);

        diff.setOpaque(true);
        diff.setBackground(background);


        JLabel diffTitle = new JLabel("DIFFICULTY");
        diffTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        diffTitle.setBounds(120, 40, 300, 60);

        easy.setBounds(100, 120, 100, 100);
        medium.setBounds(210, 120, 100, 100);
        hard.setBounds(320, 120, 100, 100);
        backfromDiff.setBounds(200, 240, 120, 30);

        diff.add(diffTitle);
        diff.add(easy);
        diff.add(medium);
        diff.add(hard);
        diff.add(backfromDiff);

        pane.add(diff, "DIFF");

        // color schemes
    }

    //add settings menu
    public void addSetCard(Container pane){
        JPanel set = new JPanel();
        set.setLayout(null);

        set.setOpaque(true);
        set.setBackground(background);

        JLabel setTitle = new JLabel("SETTINGS");
        setTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        setTitle.setBounds(140, 40, 240, 60);


        set.add(setTitle);
        back.setBounds(200, 240, 120, 30);
        set.add(back);

        JButton loadFile = new JButton("Load Save File");
        loadFile.setBounds(200, 110, 120, 30);
        set.add(loadFile);

        JLabel frameScale = new JLabel("50");
        frameScale.setFont(new Font("Serif", Font.PLAIN, 20));
        frameScale.setBounds(250, 130, 240, 60);
        set.add(frameScale);

        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL);
        framesPerSecond.setBounds(160,190,200,30);
        framesPerSecond.addChangeListener((ChangeEvent e) -> {
            frameScale.setText(String.valueOf(framesPerSecond.getValue()));
        });
        set.add(framesPerSecond);


        pane.add(set, "SET");
        //add setting options
    }

    //add credits menu
    public void addCredCard(Container pane){
        JPanel cred = new JPanel();
        cred.setLayout(null);

        cred.setOpaque(true);
        cred.setBackground(background);

        JLabel credTitle = new JLabel("CREDITS");
        credTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        credTitle.setBounds(155, 40, 220, 60);

        JLabel credText = new JLabel("#TeamEscala");
        credText.setFont(new Font("Serif", Font.PLAIN, 25));
        credText.setBounds(190, 40, 220, 150);

        cred.add(credTitle);
        cred.add(credText);
        back2.setBounds(200, 240, 120, 30);
        cred.add(back2);

        pane.add(cred, "CRED");

        //have credits page more text
    }

    public void addGameCard(Container pane){
        JPanel game = new JPanel();
        game.setLayout(null);

        pane.add(game, "GAME");
    }

    //switches to diff page
    private void toDiff(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "DIFF");
    }

    //goes back to menu
    private void toMenu(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "INDEX");
    }

    //goes to settings
    private void toSet(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "SET");
    }

    //goes to credits
    private void toCred(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "CRED");
    }

    //exits game
    private void quit(Container parent) {
        System.exit(0);
    }

    private void startGameEasy(Container parent) {
        game.setDifficulty(Game.Difficulty.EASY);
        game.startGame();

        frame.dispose();
        game.setFrame(null);
    }

    private void startGameMedium(Container parent) {
        JOptionPane.showMessageDialog(null, "This page is currently under construction!");
    }

    private void startGameHard(Container parent) {
        JOptionPane.showMessageDialog(null, "This page is currently under construction!");
    }
}

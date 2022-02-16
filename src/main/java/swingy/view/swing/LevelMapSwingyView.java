package swingy.view.swing;

import swingy.controller.LevelMapController;
import swingy.model.character.Artifact;
import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.character.villain.Villain;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.Button;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.GameWonException;
import swingy.utils.exceptions.LooseGameException;
import swingy.view.LevelMapView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LevelMapSwingyView extends JFrame implements LevelMapView {
    private MapModel mapModel;
    private Hero hero;
    private ImageIcon heroImage;
    private ImageIcon villain;
    private ImageIcon zero;
    private JLabel[][] map;
    private SwingyButtonPressHandler swingyButtonPressHandler;

    public LevelMapSwingyView(MapModel mapModel, Hero hero, LevelMapController levelMapController, SwingyButtonPressHandler swingyButtonPressHandler) {
        super("Forgotten Realms");
        this.mapModel = mapModel;
        this.hero = hero;
        this.setBounds(200, 200, 50 * 40 + 206, 50 * 40 + 26);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.swingyButtonPressHandler = swingyButtonPressHandler;
        String userDir = System.getProperty("user.dir");
        heroImage = resizeImage(new ImageIcon(userDir + "/target/classes/images/hero.png"));
        villain = resizeImage(new ImageIcon(userDir + "/target/classes/images/villain.png"));
        zero = resizeImage(new ImageIcon(userDir + "/target/classes/images/zero.png"));
        map = new JLabel[40][];
        this.setLayout(null);
        for (int i = 0; i < 40; i++) {
            map[i] = new JLabel[40];
            for (int j = 0; j < 40; j++) {
                map[i][j] = new JLabel();
                map[i][j].setLocation(i * 35 + 2, j * 35 + 2);
                map[i][j].setSize(30, 30);
            }
        }
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    levelMapController.handleButtonPressings(hero, getButton(e.getKeyCode()));
                } catch (IOException | InterruptedException | BreakGameFromKeyboardException | LooseGameException | GameWonException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private Button getButton(int code) {
        for (var button :Button.values()) {
            if (code == button.getCode())
                return button;
        }
        return null;
    }



    @Override
    public void printMap(MapModel mapModel, Hero character, LevelMapController levelMapController) throws IOException, BreakGameFromKeyboardException, GameWonException {
        for (int i = 0; i < mapModel.getMapSize(); i++) {
            for (int j = 0; j < mapModel.getMapSize(); j++) {
                Character field = mapModel.getCharacter(new Coordinate(i, j));
                if (field == null) {
                    map[i][j].setIcon(zero);
                } else if (field instanceof Villain) {
                    map[i][j].setIcon(villain);
                } else if (field instanceof Hero) {
                    map[i][j].setIcon(heroImage);
                    printHeroInfo((Hero) field);
                }
                this.add(map[i][j]);
            }
        }
        this.setVisible(true);
    }

    @Override
    public void clean() {
        setVisible(false);
        dispose();
    }

    private ImageIcon resizeImage(ImageIcon image) {
        Image image1 = image.getImage();
        Image newImg = image1.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    private void printHeroInfo(Hero hero) {
        JLabel t1, t2, t3, t4, t5, t6, t7, t8, t9;
        t1 = new JLabel("Hero: " + hero.getName());
        t2 = new JLabel("lvl: " + hero.getLevel());
        t3 = new JLabel("attack: " + hero.getAttackStrength());
        t4 = new JLabel("defence: " + hero.getDefenceStrength());
        t5 = new JLabel("hitPoints: " + hero.getHp());
        t6 = new JLabel("artifacts:");
        if (hero.getHelm() != null) {
            t7 = new JLabel(hero.getHelm().toString());
            t7.setBounds(50 * mapModel.getMapSize() + 6, 106, 500, 200);
            this.add(t7);
        }
        if (hero.getSword() != null) {
            t8 = new JLabel(hero.getSword().toString());
            t8.setBounds(50 * mapModel.getMapSize() + 6, 116, 500, 200);
            this.add(t8);
        }
        if (hero.getArmor() != null) {
            t9 = new JLabel(hero.getArmor().toString());
            t9.setBounds(50 * mapModel.getMapSize() + 6, 126, 500, 200);
            this.add(t9);
        }
        t1.setBounds(50 * mapModel.getMapSize() + 6, 6, 200,200);
        t2.setBounds(50 * mapModel.getMapSize() + 6, 26, 200,200);
        t3.setBounds(50 * mapModel.getMapSize() + 6, 46, 200,200);
        t4.setBounds(50 * mapModel.getMapSize() + 6, 66, 200,200);
        t5.setBounds(50 * mapModel.getMapSize() + 6, 86, 200,200);
        t6.setBounds(50 * mapModel.getMapSize() + 6, 96, 200, 200);
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
    }

    @Override
    public void printFightDescription(MapModel mapModel, Hero character) {
        JOptionPane.showMessageDialog(this, "you meet some enemy " + mapModel.getCharacter(character.getCoordinate()).toString());
    }

    @Override
    public void printFailedRun() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "You try to run, but failed, battle begins...");
    }

    @Override
    public void printWonBattle() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "You win battle!");
    }

    @Override
    public void printSuccessRun() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "You successfully run!");

    }

    @Override
    public void printWonGame() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "You win game, congratulations!");
    }

    @Override
    public void printArtifactDescription(Hero character, Artifact artifact) {

    }

    @Override
    public void printArtifactSuccessfulPickingUp() {

    }

    @Override
    public void printMessageAboutCleanMap() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "You clean map, and get 250 bonus experience for that!");
    }

    @Override
    public void printLevelUp(int level, int maxLevel) throws InterruptedException {
        JOptionPane.showMessageDialog(this, "Your level up! Max level is " + maxLevel + " if you get it, game will be won!");
    }
}

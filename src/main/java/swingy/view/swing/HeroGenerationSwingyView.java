package swingy.view.swing;

import swingy.model.character.hero.Hero;
import swingy.model.character.hero.HeroClass;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.view.HeroGenerationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class HeroGenerationSwingyView extends JFrame implements HeroGenerationView {
    @Override
    public void welcomePage() throws IOException, BreakGameFromKeyboardException {
        JLabel welcome = new JLabel("Greetings in swingy");
        setLayout(null);
        welcome.setSize(300, 30);
        welcome.setLocation(10, 10);
        super.add(welcome);
        super.setVisible(true);
    }

    @Override
    public Hero createHero() throws Exception {
        ButtonGroup group = new ButtonGroup();
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel();
        final var label2 = new JLabel();
        final var label3 = new JLabel();
        final var label4 = new JLabel();
        final var label5 = new JLabel();
        label.setText("You must choose from 2 classes:thief and warrior \n");
        label2.setText("warrior - 10 hp, 3 attack strength and 5 defence strength\n");
        label3.setText("thief - 7 hp, 5 attack strength and 3 defence strength\n");
        label4.setText("both classes have bonus 3 hp , and 2 attach and defence strength per level\n");
        label5.setText("choose wisely....");
        label.setBounds(30, 260, 400, 20);
        label2.setBounds(30, 280, 400, 20);
        label3.setBounds(30, 300, 400, 20);
        label4.setBounds(30, 320, 500, 20);
        label5.setBounds(30, 340, 400, 20);
        final JRadioButton warriorButton = new JRadioButton();
        warriorButton.setText("Warrior");
        warriorButton.setBounds(10, 50, 90, 30);
        final JRadioButton thiefButton = new JRadioButton();
        thiefButton.setText("Thief");
        thiefButton.setBounds(100, 50, 90, 30);
        group.add(warriorButton);
        group.add(thiefButton);
        final JTextField name = new JTextField("adventurer");
        name.setBounds(10, 100, 250, 30);
        this.add(warriorButton);
        this.add(thiefButton);
        this.add(name);
        label.setLocation(30, 260);
        label2.setLocation(30, 280);
        label3.setLocation(30, 300);
        label4.setLocation(30, 320);
        label5.setLocation(30, 340);
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);


        final boolean[] isWait = {true};
        JButton heroButton = new JButton("Save");
        heroButton.setSize(100, 100);
        heroButton.setLocation(10, 150);
        final Hero[] hero = {new Hero()};
        heroButton.addActionListener(e -> {
            //заполняю поля героя
            if (warriorButton.isSelected() && !thiefButton.isSelected()) {
                hero[0] = new Hero(HeroClass.WARRIOR);
            } else if (!warriorButton.isSelected() && thiefButton.isSelected()) {
                hero[0] = new Hero(HeroClass.THIEF);
            }
            var heroName = name.getText();
            if (heroName.isEmpty())
                heroName = "adventurer";
            hero[0].setName(heroName);
            isWait[0] = false;
        });
        this.add(heroButton);
        super.validate();
        super.repaint();
        while (isWait[0]) {
            Thread.sleep(100);
        }
        this.setVisible(false);
        return hero[0];
    }

    @Override
    public boolean isHeroGeneratedOrTakenFromDb() throws IOException, BreakGameFromKeyboardException {
        return JOptionPane.showConfirmDialog(this,
                "Do you want to create new hero?",
                "Choice",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public Hero choiceOldHero(List<Hero> heroes) throws InterruptedException, IOException {
        int position = 50;
        final Hero[] result = new Hero[1];
        final boolean[] isWait = {true};
        for (final Hero hero : heroes) {
            var numberOfArtifacts = 0;
            numberOfArtifacts += hero.getHelm() == null ? 0 : 1;
            numberOfArtifacts += hero.getSword() == null ? 0 : 1;
            numberOfArtifacts += hero.getArmor() == null ? 0 : 1;
            String artifacts;
            if (numberOfArtifacts != 1) {
                artifacts = numberOfArtifacts + " artifacts";
            } else {
                artifacts = numberOfArtifacts + " artifact";
            }
            JButton heroButton = new JButton(hero.getName() +" " +hero.getHeroClass() +" " + hero.getLevel() + " lvl, " + artifacts);
            heroButton.setSize(300, 50);
            heroButton.setLocation(10, position);
            position += 50;
            heroButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isWait[0] = false;
                    result[0] = hero;
                }
            });
            super.add(heroButton);
        }
        super.validate();
        super.repaint();
        while (isWait[0]) {
            Thread.sleep(100);
        }
        this.setVisible(false);
        return result[0];
    }

    @Override
    public void clean() {
        super.setVisible(false);
    }

    public HeroGenerationSwingyView() throws HeadlessException {
        super("Set hero page");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

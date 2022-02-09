package swingy.model.character.hero.classes;

import swingy.model.character.hero.Hero;
import swingy.utils.ActualAndMaxValuePair;

public class Mage extends Hero {
    private ActualAndMaxValuePair<Integer, Integer> hp;
    private ActualAndMaxValuePair<Integer, Integer> mp;

    public Mage(String name) {
        super(name);
        this.hp = new ActualAndMaxValuePair<>(10,10);
        this.mp = new ActualAndMaxValuePair<>(100, 100);
    }

    @Override
    public ActualAndMaxValuePair<Integer, Integer> getHp() {
        return hp;
    }

    @Override
    public void setHp(ActualAndMaxValuePair<Integer, Integer> hp) {
        this.hp = hp;
    }

    @Override
    public ActualAndMaxValuePair<Integer, Integer> getMp() {
        return mp;
    }

    @Override
    public void setMp(ActualAndMaxValuePair<Integer, Integer> mp) {
        this.mp = mp;
    }
}

package swingy.model.character.hero.classes;

import swingy.model.character.hero.Hero;
import swingy.utils.ActualAndMaxValuePair;

public class Thief extends Hero {
    private ActualAndMaxValuePair<Integer, Integer> hp;
    private ActualAndMaxValuePair<Integer, Integer> mp;

    public Thief(String name) {
        super(name);
        this.hp = new ActualAndMaxValuePair<>(30,30);
        this.mp = new ActualAndMaxValuePair<>(30, 30);
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

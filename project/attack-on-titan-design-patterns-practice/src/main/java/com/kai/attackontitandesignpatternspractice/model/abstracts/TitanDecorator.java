package com.kai.attackontitandesignpatternspractice.model.abstracts;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;

// 裝飾者模式:抽象裝飾者
public abstract class TitanDecorator implements Titan {

    protected Titan titan;

    public TitanDecorator(Titan titan) {
        this.titan = titan;
    }

    @Override
    public String attack() {
        return titan.attack();
    }

    @Override
    public String eatHuman(ConcreteHuman human) {
        return titan.eatHuman(human);
    }

    @Override
    public String getAbility() {
        return titan.getAbility();
    }


}

package com.kai.attackontitandesignpatternspractice.model.abstracts;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 裝飾者模式:抽象裝飾者
public abstract class HumanDecorator implements Human {

    protected Human human;

    public HumanDecorator(Human human) {
        this.human = human;
    }

    @Override
    public String getAbility() {
        return human.getAbility();
    }

}

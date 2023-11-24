package com.kai.attackontitandesignpatternspractice.model.decorator;

import com.kai.attackontitandesignpatternspractice.model.abstracts.HumanDecorator;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.UUID;

// 裝飾者模式:具體裝飾者ab1a
public class KnifeAbility extends HumanDecorator {
    public KnifeAbility(Human human) {
        super(human);
    }

    @Override
    public UUID getUuid() {
        return human.getUuid();
    }

    @Override
    public String getName() {
        return human.getName();
    }

    @Override
    public String getAbility() {
        return human.getAbility() + " + Knife";
    }

    @Override
    public String attack() {
        return human.attack();
    }
}

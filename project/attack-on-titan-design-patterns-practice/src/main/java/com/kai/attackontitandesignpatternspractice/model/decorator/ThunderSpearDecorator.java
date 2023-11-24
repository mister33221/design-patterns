package com.kai.attackontitandesignpatternspractice.model.decorator;

import com.kai.attackontitandesignpatternspractice.model.decorator.abstracts.HumanDecorator;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.UUID;

// 裝飾者模式:具體裝飾者
public class ThunderSpearDecorator extends HumanDecorator {
    public ThunderSpearDecorator(Human human) {
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
        return human.getAbility() + " + Thunder Spear";
    }

    @Override
    public String attack() {
        return human.attack();
    }
}

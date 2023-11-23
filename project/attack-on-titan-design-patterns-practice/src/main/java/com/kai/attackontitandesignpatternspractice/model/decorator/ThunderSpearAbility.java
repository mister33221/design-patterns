package com.kai.attackontitandesignpatternspractice.model.decorator;

import com.kai.attackontitandesignpatternspractice.model.abstracts.HumanDecorator;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 裝飾者模式:具體裝飾者
public class ThunderSpearAbility extends HumanDecorator {
    public ThunderSpearAbility(Human human) {
        super(human);
    }
}

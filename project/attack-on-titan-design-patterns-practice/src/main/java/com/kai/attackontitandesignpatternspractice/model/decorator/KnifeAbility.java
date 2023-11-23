package com.kai.attackontitandesignpatternspractice.model.decorator;

import com.kai.attackontitandesignpatternspractice.model.abstracts.HumanDecorator;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 裝飾者模式:具體裝飾者ab1a
public class KnifeAbility extends HumanDecorator {
    public KnifeAbility(Human human) {
        super(human);
    }
}

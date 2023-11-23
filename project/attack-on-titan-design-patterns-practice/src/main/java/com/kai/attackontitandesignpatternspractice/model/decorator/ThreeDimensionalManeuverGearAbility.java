package com.kai.attackontitandesignpatternspractice.model.decorator;

import com.kai.attackontitandesignpatternspractice.model.abstracts.HumanDecorator;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 裝飾者模式:具體裝飾者
public class ThreeDimensionalManeuverGearAbility extends HumanDecorator {
    public ThreeDimensionalManeuverGearAbility(Human human) {
        super(human);
    }
}

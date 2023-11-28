package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.TitanAttackStrategy;

// 策略模式: 具體策略
public class BeastTitanShoutsStrategy implements TitanAttackStrategy {
    @Override
    public String attack() {
        return "Beast Titan shouts, all the Eldia species turn into Titans";
    }
}

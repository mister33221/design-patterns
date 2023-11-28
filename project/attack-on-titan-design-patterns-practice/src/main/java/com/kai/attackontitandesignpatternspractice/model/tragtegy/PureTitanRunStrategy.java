package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.TitanAttackStrategy;

// 策略模式: 具體策略
public class PureTitanRunStrategy implements TitanAttackStrategy {
    @Override
    public String attack() {
        return "Pure Titan runs";
    }
}

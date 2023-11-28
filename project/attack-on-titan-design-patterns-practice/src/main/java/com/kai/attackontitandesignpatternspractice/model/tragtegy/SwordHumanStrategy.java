package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.HumanAttackStrategy;

// 策略模式: 具體策略
public class SwordHumanStrategy implements HumanAttackStrategy {
    @Override
    public String attack() {
        return "Sword Human slashes with sword";
    }
}

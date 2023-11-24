package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.interfaces.HumanAttackStrategy;

public class SwordHumanStrategy implements HumanAttackStrategy {
    @Override
    public String attack() {
        return "Sword Human slashes with sword";
    }
}

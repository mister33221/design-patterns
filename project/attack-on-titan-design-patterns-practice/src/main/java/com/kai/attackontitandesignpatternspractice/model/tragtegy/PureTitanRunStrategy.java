package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.interfaces.TitanAttackStrategy;

public class PureTitanRunStrategy implements TitanAttackStrategy {
    @Override
    public String attack() {
        return "Pure Titan runs";
    }
}

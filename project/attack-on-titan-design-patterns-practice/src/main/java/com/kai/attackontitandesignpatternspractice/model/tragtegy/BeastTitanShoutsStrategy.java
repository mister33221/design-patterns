package com.kai.attackontitandesignpatternspractice.model.tragtegy;

import com.kai.attackontitandesignpatternspractice.model.interfaces.TitanAttackStrategy;

public class BeastTitanShoutsStrategy implements TitanAttackStrategy {
    @Override
    public String attack() {
        return "Beast Titan shouts, all the Eldia species turn into Titans";
    }
}

package com.kai.attackontitandesignpatternspractice.model.tragtegy;


import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.TitanAttackStrategy;

public class BeastTitanThrowsStrategy implements TitanAttackStrategy {
    @Override
    public String attack() {
        return "Beast Titan throws rocks";
    }
}

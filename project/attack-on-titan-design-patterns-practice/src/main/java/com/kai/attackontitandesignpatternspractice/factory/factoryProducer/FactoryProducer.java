package com.kai.attackontitandesignpatternspractice.factory.factoryProducer;

import com.kai.attackontitandesignpatternspractice.exception.CustomException;
import com.kai.attackontitandesignpatternspractice.factory.factory.*;
import com.kai.attackontitandesignpatternspractice.model.ConcreteTitan;
import com.kai.attackontitandesignpatternspractice.model.decorator.*;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;

// 簡單工廠模式:工廠生產者
//
public class FactoryProducer {

    public Titan createTitansByFactory(TitanType titanType) throws CustomException {
        switch (titanType.getTypeNo()) {
            case 1 -> {
                return new FoundingTitanFactory().createTitan();
            }
            case 2 -> {
                return new ColossalTitanFactory().createTitan();
            }
            case 3 -> {
                return new ArmoredTitanFactory().createTitan();
            }
            case 4 -> {
                return new FemaleTitanFactory().createTitan();
            }
            case 5 -> {
                return new BeastTitanFactory().createTitan();
            }
            case 6 -> {
                return new AttackTitanFactory().createTitan();
            }
            case 7 -> {
                return new CartTitanFactory().createTitan();
            }
            case 8 -> {
                return new WarHammerTitanFactory().createTitan();
            }
            case 9 -> {
                return new PureTitanFactory().createTitan();
            }
            default -> throw new CustomException("No such titan type");

        }
    }

    public Human createHumansByFactory() {
        return new HumanFactory().createHuman();
    }

    public Human createSpecificHumanByFactory(String name, String ability, String type) throws CustomException {
        return new HumanFactory().createSpecificHuman(
                name,
                ability,
                type == null ? null : Integer.parseInt(type));
    }

}

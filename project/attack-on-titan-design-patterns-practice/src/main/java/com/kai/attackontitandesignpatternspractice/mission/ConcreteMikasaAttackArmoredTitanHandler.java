package com.kai.attackontitandesignpatternspractice.mission;

import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.List;

public class ConcreteMikasaAttackArmoredTitanHandler implements AttackArmoredTitanHandler {

    private AttackArmoredTitanHandler nextHandler;

    @Override
    public void setNextHandler(AttackArmoredTitanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleAttack(List<Human> decoratedHumans) {
        Human human = decoratedHumans.stream()
                .filter(decoratedHuman -> decoratedHuman.getName().equals("Mikasa"))
                .findFirst()
                .orElse(new ConcreteHuman());
        System.out.println(human.getName() + " is attacking the Armored Titan");
        System.out.println(human.getName() + " is using " + human.getAbility());
        System.out.println("Reiner has been blown out of the Armored Titan's nape");

        if(nextHandler != null) {
            System.out.println("--------------------");
            nextHandler.handleAttack(decoratedHumans);
        }
    }
}
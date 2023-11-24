package com.kai.attackontitandesignpatternspractice.mission;

import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.List;

public class ConcreteJeanAttackArmoredTitanHandler implements AttackArmoredTitanHandler {

    private AttackArmoredTitanHandler nextHandler;

    @Override
    public void setNextHandler(AttackArmoredTitanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleAttack(List<Human> decoratedHumans) {
        Human human = decoratedHumans.stream()
                .filter(decoratedHuman -> decoratedHuman.getName().equals("Jean"))
                .findFirst()
                .orElse(new ConcreteHuman());
        System.out.println(human.getName() + " is attacking the Armored Titan");
        System.out.println(human.getName() + " is using " + human.getAbility());
        System.out.println("Armored Titan's left mouth muscle is injured");

        if (nextHandler != null) {
            System.out.println("--------------------");
            nextHandler.handleAttack(decoratedHumans);
        }
    }
}

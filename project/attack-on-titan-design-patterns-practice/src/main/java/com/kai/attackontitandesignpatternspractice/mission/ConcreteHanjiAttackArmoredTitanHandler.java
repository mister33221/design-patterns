package com.kai.attackontitandesignpatternspractice.mission;

import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.List;

// 職責鏈模式: 具體處理者
public class ConcreteHanjiAttackArmoredTitanHandler implements AttackArmoredTitanHandler {

    private AttackArmoredTitanHandler nextHandler;
    @Override
    public void setNextHandler(AttackArmoredTitanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleAttack(List<Human> decoratedHumans) {
        Human juman = decoratedHumans.stream()
                .filter(decoratedHuman -> decoratedHuman.getName().equals("Hanji"))
                .findFirst()
                .orElse(new ConcreteHuman());
        System.out.println(juman.getName() + " is attacking the Armored Titan");
        System.out.println(juman.getName() + " is using " + juman.getAbility());
        System.out.println("Armored Titan's right mouth muscle is injured. Armored Titan's mouth is open");

        if(nextHandler != null) {
            System.out.println("--------------------");
            nextHandler.handleAttack(decoratedHumans);
        }
    }
}

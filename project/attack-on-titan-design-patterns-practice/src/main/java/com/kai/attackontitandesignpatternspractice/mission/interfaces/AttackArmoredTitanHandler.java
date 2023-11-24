package com.kai.attackontitandesignpatternspractice.mission.interfaces;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.List;

public interface AttackArmoredTitanHandler {

    void setNextHandler(AttackArmoredTitanHandler nextHandler);
    void handleAttack(List<Human> decoratedHumans);

}

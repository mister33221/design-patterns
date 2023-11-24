package com.kai.attackontitandesignpatternspractice.model.interfaces;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;

public interface State {
    void transform(ConcreteHuman human);
    String attack();
    void setAttackStrategy(AttackStrategy attackStrategy);
}

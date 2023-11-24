package com.kai.attackontitandesignpatternspractice.model.state.interfaces;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.AttackStrategy;

public interface State {
    void transform(ConcreteHuman human);
    String attack();
    void setAttackStrategy(AttackStrategy attackStrategy);
}

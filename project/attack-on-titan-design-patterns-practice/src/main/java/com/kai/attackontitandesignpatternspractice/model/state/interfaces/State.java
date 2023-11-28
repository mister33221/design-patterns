package com.kai.attackontitandesignpatternspractice.model.state.interfaces;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.AttackStrategy;

// 狀態模式: 狀態介面
public interface State {
    void transform(ConcreteHuman human);
    String attack();
    void setAttackStrategy(AttackStrategy attackStrategy);
}

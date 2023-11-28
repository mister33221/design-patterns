package com.kai.attackontitandesignpatternspractice.model.state;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.AttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.HumanAttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.state.interfaces.State;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.SwordHumanStrategy;
import lombok.Data;

// 狀態模式: 人類狀態
@Data
public class HumanState implements State {

    private HumanAttackStrategy attackStrategy;

    @Override
    public void transform(ConcreteHuman human) {
        System.out.println(human.getName() + " is turning into a human.");
        setAttackStrategy(new SwordHumanStrategy());
    }

    public String attack() {
        return attackStrategy.attack();
    }

    @Override
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = (HumanAttackStrategy) attackStrategy;
    }
}

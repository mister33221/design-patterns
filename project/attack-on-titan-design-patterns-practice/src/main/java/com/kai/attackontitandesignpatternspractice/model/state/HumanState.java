package com.kai.attackontitandesignpatternspractice.model.state;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.AttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.interfaces.HumanAttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.interfaces.State;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.SwordHumanStrategy;
import lombok.Data;

@Data
public class HumanState implements State {

    private HumanAttackStrategy attackStrategy;
    @Override
    public void transform(ConcreteHuman human) {
        System.out.println("I am turning into a human.");
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

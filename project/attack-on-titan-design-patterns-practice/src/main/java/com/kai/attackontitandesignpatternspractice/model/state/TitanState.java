package com.kai.attackontitandesignpatternspractice.model.state;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.AttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.state.interfaces.State;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.interfaces.TitanAttackStrategy;
import lombok.Data;

@Data
public class TitanState implements State {

    private TitanAttackStrategy attackStrategy;
    @Override
    public void transform(ConcreteHuman human) {
        System.out.println(human.getName() + " is turning into a titan.");
    }

    @Override
    public String attack() {
        return attackStrategy.attack();
    }

    @Override
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = (TitanAttackStrategy) attackStrategy;
    }

}

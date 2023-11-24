package com.kai.attackontitandesignpatternspractice.model.state;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.interfaces.TitanAttackStrategy;
import com.kai.attackontitandesignpatternspractice.model.interfaces.State;
import lombok.Data;

@Data
public class HumanState implements State {

    private TitanAttackStrategy attackStrategy;
    @Override
    public void transform(ConcreteHuman human) {
        System.out.println("I am a human.");
//        TODO: set the ability and attack method
    }

    public String attack() {
        return attackStrategy.attack();
    }
}

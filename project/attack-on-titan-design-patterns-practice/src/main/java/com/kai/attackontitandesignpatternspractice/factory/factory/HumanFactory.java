package com.kai.attackontitandesignpatternspractice.factory.factory;

import com.kai.attackontitandesignpatternspractice.factory.interfaces.HumanFacory;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

public class HumanFactory implements HumanFacory {
    @Override
    public Human createHuman() {
        return new ConcreteHuman().setUuid(java.util.UUID.randomUUID()).setName("Human").setAbility("base ability");
    }

    public Human createSpecificHuman(String name, String ability, Integer type) {
        return new ConcreteHuman()
                .setUuid(java.util.UUID.randomUUID())
                .setName(name).setAbility(ability)
                .setTitanType(type == null ? null:TitanType.getTitanTypeByTypeNo(type)
                );
    }
}

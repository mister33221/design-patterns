package com.kai.attackontitandesignpatternspractice.factory.factory;

import com.kai.attackontitandesignpatternspractice.factory.interfaces.HumanFacory;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.enums.BloodType;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.state.HumanState;

public class HumanFactory implements HumanFacory {
    @Override
    public Human createHuman() {
        return new ConcreteHuman()
                .setUuid(java.util.UUID.randomUUID())
                .setName("Human")
                .setAbility("base ability")
                .setTitanType(null)
                .setBloodType(null)
                .setState(new HumanState());
    }

    public Human createSpecificHuman(String name, String ability) {
        switch (name) {
            case "Erwin", "Hanji", "Jean", "Sasha", "Connie":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(null)
                        .setBloodType(BloodType.ELDIAN)
                        .setState(new HumanState());
            case "Levi":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(null)
                        .setBloodType(BloodType.ACKERMAN)
                        .setState(new HumanState());
            case "Eren":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(TitanType.ATTACK_TITAN)
                        .setBloodType(BloodType.ELDIAN)
                        .setState(new HumanState());
            case "Armin", "Bertholdt":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(TitanType.COLOSSAL_TITAN)
                        .setBloodType(BloodType.ELDIAN)
                        .setState(new HumanState());
            case "Mikasa":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(TitanType.ATTACK_TITAN)
                        .setBloodType(BloodType.ACKERMAN)
                        .setState(new HumanState());
            case "Zeke":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(TitanType.BEAST_TITAN)
                        .setBloodType(BloodType.ELDIAN)
                        .setState(new HumanState());
            case "Reiner":
                return new ConcreteHuman()
                        .setUuid(java.util.UUID.randomUUID())
                        .setName(name)
                        .setAbility(ability)
                        .setTitanType(TitanType.ARMORED_TITAN)
                        .setBloodType(BloodType.ELDIAN)
                        .setState(new HumanState());
            default:
                return null;
        }
    }
}

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

    @Override
    public Human createSpecificHuman(String name, String ability) {
        return switch (name) {
            case "Erwin", "Hanji", "Jean", "Sasha", "Connie" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(null)
                    .setBloodType(BloodType.ELDIAN)
                    .setState(new HumanState());
            case "Levi" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(null)
                    .setBloodType(BloodType.ACKERMAN)
                    .setState(new HumanState());
            case "Eren" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(TitanType.ATTACK_TITAN)
                    .setBloodType(BloodType.ELDIAN)
                    .setState(new HumanState());
            case "Armin", "Bertholdt" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(TitanType.COLOSSAL_TITAN)
                    .setBloodType(BloodType.ELDIAN)
                    .setState(new HumanState());
            case "Mikasa" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(TitanType.ATTACK_TITAN)
                    .setBloodType(BloodType.ACKERMAN)
                    .setState(new HumanState());
            case "Zeke" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(TitanType.BEAST_TITAN)
                    .setBloodType(BloodType.ELDIAN)
                    .setState(new HumanState());
            case "Reiner" -> new ConcreteHuman()
                    .setUuid(java.util.UUID.randomUUID())
                    .setName(name)
                    .setAbility(ability)
                    .setTitanType(TitanType.ARMORED_TITAN)
                    .setBloodType(BloodType.ELDIAN)
                    .setState(new HumanState());
            default -> null;
        };
    }

    @Override
    public Human createNormalErdianHuman(String name) {
        return new ConcreteHuman()
                .setUuid(java.util.UUID.randomUUID())
                .setName("Erdian " + name)
                .setAbility("Running")
                .setTitanType(TitanType.PURE_TITAN)
                .setBloodType(BloodType.ELDIAN)
                .setState(new HumanState());
    }
}

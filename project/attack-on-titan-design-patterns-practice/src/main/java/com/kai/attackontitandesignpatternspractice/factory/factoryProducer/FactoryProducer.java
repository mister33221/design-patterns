package com.kai.attackontitandesignpatternspractice.factory.factoryProducer;

import com.kai.attackontitandesignpatternspractice.factory.factory.HumanFactory;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 簡單工廠模式:工廠生產者
public class FactoryProducer {

    private static final HumanFactory humanFactory = new HumanFactory();


    public Human createHumansByFactory() {
        return humanFactory.createHuman();
    }

    public Human createSpecificHumanByFactory(String name, String ability) {
        return humanFactory.createSpecificHuman(
                name,
                ability);
    }

    public Human createNormalErdianHumanByFactory(String name) {
        return humanFactory.createNormalErdianHuman(name);
    }

}

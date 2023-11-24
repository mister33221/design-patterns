package com.kai.attackontitandesignpatternspractice.factory.factoryProducer;

import com.kai.attackontitandesignpatternspractice.factory.factory.HumanFactory;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 簡單工廠模式:工廠生產者
//
public class FactoryProducer {

    public Human createHumansByFactory() {
        return new HumanFactory().createHuman();
    }

    public Human createSpecificHumanByFactory(String name, String ability) {
        return new HumanFactory().createSpecificHuman(
                name,
                ability);
    }

}

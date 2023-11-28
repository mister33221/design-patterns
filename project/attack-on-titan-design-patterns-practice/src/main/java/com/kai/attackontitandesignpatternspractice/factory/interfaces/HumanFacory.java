package com.kai.attackontitandesignpatternspractice.factory.interfaces;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

// 工廠模式: 工廠介面
public interface HumanFacory {

    Human createHuman();
    Human createSpecificHuman(String name, String ability);
    Human createNormalErdianHuman(String name);

}

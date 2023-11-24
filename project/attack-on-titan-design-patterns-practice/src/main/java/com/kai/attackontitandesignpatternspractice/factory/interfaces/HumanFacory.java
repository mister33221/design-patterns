package com.kai.attackontitandesignpatternspractice.factory.interfaces;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

public interface HumanFacory {

    Human createHuman();
    Human createSpecificHuman(String name, String ability);
    Human createNormalErdianHuman(String name);

}

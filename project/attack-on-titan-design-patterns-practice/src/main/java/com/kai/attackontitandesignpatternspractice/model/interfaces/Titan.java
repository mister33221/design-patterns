package com.kai.attackontitandesignpatternspractice.model.interfaces;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;

import java.util.UUID;

// 裝飾者模式:抽象組件
public interface Titan {

    String getName();
    UUID getUuid();
    String getAbility();
    String attack();
    String eatHuman(ConcreteHuman human);


}

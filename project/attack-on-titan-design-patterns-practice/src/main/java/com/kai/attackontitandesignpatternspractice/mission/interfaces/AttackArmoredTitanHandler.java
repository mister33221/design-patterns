package com.kai.attackontitandesignpatternspractice.mission.interfaces;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;

import java.util.List;

// 職責鏈模式: 處理者介面
public interface AttackArmoredTitanHandler {

    void setNextHandler(AttackArmoredTitanHandler nextHandler);
    void handleAttack(List<Human> decoratedHumans);

}

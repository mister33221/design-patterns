package com.kai.attackontitandesignpatternspractice.factory.interfaces;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;

// 工廠方法模式:抽象工廠
public interface TitanFactory {
    Titan createTitan();
}

package com.kai.attackontitandesignpatternspractice.factory.factory;

import com.kai.attackontitandesignpatternspractice.factory.interfaces.TitanFactory;
import com.kai.attackontitandesignpatternspractice.model.ConcreteTitan;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;

import java.util.UUID;
// 工廠方法模式:具體工廠
public class CartTitanFactory implements TitanFactory {
    @Override
    public Titan createTitan() {
        return new ConcreteTitan().setUuid(UUID.randomUUID()).setName("Cart Titan").setType(TitanType.CART_TITAN).setHeight(60).setAbility("Exceptional endurance.");
    }
}

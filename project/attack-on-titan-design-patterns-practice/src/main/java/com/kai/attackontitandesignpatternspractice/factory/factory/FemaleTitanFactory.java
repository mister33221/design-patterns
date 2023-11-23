package com.kai.attackontitandesignpatternspractice.factory.factory;

import com.kai.attackontitandesignpatternspractice.factory.interfaces.TitanFactory;
import com.kai.attackontitandesignpatternspractice.model.ConcreteTitan;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;

import java.util.UUID;
// 工廠方法模式:具體工廠
public class FemaleTitanFactory implements TitanFactory {

    @Override
    public Titan createTitan() {
        return new ConcreteTitan().setUuid(UUID.randomUUID()).setName("Female Titan").setType(TitanType.FEMALE_TITAN).setHeight(60).setAbility("Call other pure titans. ");
    }
}

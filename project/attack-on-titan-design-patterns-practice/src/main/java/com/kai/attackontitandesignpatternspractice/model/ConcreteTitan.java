package com.kai.attackontitandesignpatternspractice.model;

import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

// 裝飾者模式:具體組件
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteTitan implements Titan {

    private UUID uuid;
    private String name;
    private TitanType type;
    private String ability;
    private int height;


    @Override
    public String getAbility() {
        return ability;
    }

    @Override
    public String attack() {
        return null;
    }

    @Override
    public String eatHuman(ConcreteHuman human) {
        return "My name is " + name + ".";
    }

    @Override
    public String toString() {
        return "ConcreteTitan{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", ability='" + ability + '\'' +
                ", height=" + height +
                '}';
    }

}

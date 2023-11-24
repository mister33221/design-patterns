package com.kai.attackontitandesignpatternspractice.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TitanType {

    FOUNDING_TITAN(1, "始祖巨人"),
    COLOSSAL_TITAN(2, "超大型巨人"),
    ARMORED_TITAN(3, "鎧甲巨人"),
    FEMALE_TITAN(4, "女型巨人"),
    BEAST_TITAN(5, "獸型巨人"),
    ATTACK_TITAN(6, "進擊巨人"),
    CART_TITAN(7, "戰車巨人"),
    WAR_HAMMER_TITAN(8, "戰鎚巨人"),
    PURE_TITAN(9, "純潔巨人");

    private final Integer typeNo;
    private final String typeName;

    TitanType(Integer typeNo, String typeName) {
        this.typeNo = typeNo;
        this.typeName = typeName;
    }

    public static TitanType getTitanTypeByTypeNo(Integer typeNo) {
        return Arrays.stream(TitanType.values())
                .filter(titanType -> titanType.getTypeNo().equals(typeNo))
                .findFirst()
                .orElse(null);
    }

}

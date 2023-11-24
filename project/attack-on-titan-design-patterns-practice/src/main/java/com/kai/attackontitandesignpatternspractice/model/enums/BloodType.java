package com.kai.attackontitandesignpatternspractice.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BloodType {

    ELDIAN(1, "艾爾迪亞人"),
    MARLEYAN(2, "瑪雷人"),
    ACKERMAN(3, "阿克曼"),
    ELSE(4, "其他");

    private final Integer typeNo;
    private final String typeName;

    BloodType(Integer typeNo, String typeName) {
        this.typeNo = typeNo;
        this.typeName = typeName;
    }

    public static BloodType getBloodTypeByTypeNo(Integer typeNo) {
        return Arrays.stream(BloodType.values())
                .filter(bloodType -> bloodType.getTypeNo().equals(typeNo))
                .findFirst()
                .orElse(null);
    }

}

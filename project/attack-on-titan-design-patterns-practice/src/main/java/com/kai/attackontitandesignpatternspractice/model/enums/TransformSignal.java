package com.kai.attackontitandesignpatternspractice.model.enums;

import lombok.Getter;

@Getter
public enum TransformSignal {

    TITAN("Titan"),
    HUMAN("Human");

    private String value;

    TransformSignal(String value) {
        this.value = value;
    }
}

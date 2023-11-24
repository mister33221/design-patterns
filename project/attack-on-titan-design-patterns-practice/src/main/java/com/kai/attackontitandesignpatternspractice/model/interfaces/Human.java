package com.kai.attackontitandesignpatternspractice.model.interfaces;

import java.util.UUID;

public interface Human extends Attacker {

    UUID getUuid();
    String getName();
    String getAbility();
    String attack();

}

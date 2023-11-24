package com.kai.attackontitandesignpatternspractice.model;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Attacker;

import java.util.ArrayList;
import java.util.List;

// 為了讓這個class乾淨，沒有繼承Human，而上在human上面又多了一個interface Attacker，但是這樣的繼承芳是很不符合邏輯，因為human不應該是Attacker的一種
public class PureTitanArmy implements Attacker {

    private final List<ConcreteHuman> titanArmy = new ArrayList<>();

    @Override
    public String attack() {
        titanArmy.forEach(titan -> {
            System.out.println(titan.getName() + " is " + titan.getAbility());
        });
        return null;
    }

    public void addTitan(ConcreteHuman titan) {
        titanArmy.add(titan);
    }


}

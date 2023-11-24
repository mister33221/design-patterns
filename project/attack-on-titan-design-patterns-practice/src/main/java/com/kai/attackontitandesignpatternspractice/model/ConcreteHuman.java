package com.kai.attackontitandesignpatternspractice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kai.attackontitandesignpatternspractice.model.enums.BloodType;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.interfaces.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteHuman implements Human {

    private UUID uuid;
    private String name;
    private String ability;
    private TitanType titanType;
    private BloodType bloodType;
    @JsonIgnore
    private State state;


    @Override
    public String attack() {
        return state.attack();
    }

    public void transform() {
        state.transform(this);
    }
}

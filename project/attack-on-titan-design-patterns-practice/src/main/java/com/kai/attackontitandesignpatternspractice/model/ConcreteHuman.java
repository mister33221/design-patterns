package com.kai.attackontitandesignpatternspractice.model;

import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteHuman implements Human {

    private UUID id;
    private String name;
    private String ability;

    @Override
    public String getAbility() {
        return ability;
    }
}

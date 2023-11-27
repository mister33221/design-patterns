package com.kai.attackontitandesignpatternspractice.model.command;

import com.kai.attackontitandesignpatternspractice.model.command.interfaces.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Erwin {

    private Command command;

    public void executeCommand() {
        command.execute();
    }

}

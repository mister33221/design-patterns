package com.kai.attackontitandesignpatternspractice.model.command;

import com.kai.attackontitandesignpatternspractice.model.command.interfaces.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmokeSignalCommand implements Command {

    private ScoutRegiment scoutRegiment;

    @Override
    public void execute() {
        scoutRegiment.smokSignal();
    }
}

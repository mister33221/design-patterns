package com.kai.attackontitandesignpatternspractice.model.command;

import com.kai.attackontitandesignpatternspractice.model.command.interfaces.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 命令模式: 命令接口實作
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssaultCommand implements Command {

    private ScoutRegiment scoutRegiment;


    @Override
    public void execute() {
        scoutRegiment.assault();
    }
}

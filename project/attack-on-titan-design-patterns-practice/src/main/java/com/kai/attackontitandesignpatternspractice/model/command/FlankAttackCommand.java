package com.kai.attackontitandesignpatternspractice.model.command;

import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.command.interfaces.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 命令模式: 命令接口實作
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlankAttackCommand implements Command {

    private Levi levi;

    @Override
    public void execute() {
        levi.flankAttack();
    }
}

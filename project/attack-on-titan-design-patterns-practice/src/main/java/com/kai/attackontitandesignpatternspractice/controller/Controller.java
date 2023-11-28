package com.kai.attackontitandesignpatternspractice.controller;

import com.kai.attackontitandesignpatternspractice.factory.factoryProducer.FactoryProducer;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteConnieAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteHanjiAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteJeanAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteMikasaAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.PureTitanArmy;
import com.kai.attackontitandesignpatternspractice.model.command.*;
import com.kai.attackontitandesignpatternspractice.model.command.interfaces.Command;
import com.kai.attackontitandesignpatternspractice.model.decorator.ThunderSpearDecorator;
import com.kai.attackontitandesignpatternspractice.model.decorator.VerticalManeuveringEquipmentDecorator;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.enums.TransformSignal;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.state.HumanState;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.BeastTitanShoutsStrategy;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.BeastTitanThrowsStrategy;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.PureTitanRunStrategy;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {


    List<ConcreteHuman> concreteHumanList = new ArrayList<>();
    HashMap<String, String> specialCharactersInitialMap = new HashMap<>() {{
//        帕拉迪島勢力
        put("Erwin", "Commander");
        put("Hanji", "Scientist");
        put("Levi", "Captain");
        put("Eren", "Brave");
        put("Armin", "Strategist");
        put("Mikasa", "Love");
        put("Jean", "LeaderShip");
        put("Connie", "Speed");
        put("Sasha", "Archery");
//        瑪萊勢力
        put("Zeke", "Captain");
        put("Reiner", "Warrior");
        put("Bertholdt", "Warrior");

    }};

    @PostMapping("/createHumans")
    @Operation(summary = "Create humans by using simple factory pattern", tags = {"Init"})
    public String createHumans() {
//        1. 簡單工廠模式:工廠生產者
        FactoryProducer factoryProducer = new FactoryProducer();

//        2. 簡單工廠模式:具體工廠，透過工廠生產者取得具體工廠，把產出的人類放進List
        for (int i = 1; i <= 10; i++) {
            concreteHumanList.add((ConcreteHuman) factoryProducer.createHumansByFactory());
        }

        return "Human created";
    }

    @PostMapping("/createSpecificHuman")
    @Operation(summary = "Create specific humans by using simple factory pattern", tags = {"Init"})
    public String createSpecificHuman() {
//        1. 簡單工廠模式:工廠生產者
        FactoryProducer factoryProducer = new FactoryProducer();

//        2. 簡單工廠模式:具體工廠，透過工廠生產者取得具體工廠，把產出的人類放進List
        for (String name : specialCharactersInitialMap.keySet()) {
            concreteHumanList.add((ConcreteHuman) factoryProducer.createSpecificHumanByFactory(name, specialCharactersInitialMap.get(name)));
        }

        return "Specific human created";
    }

    @GetMapping("/humans")
    @Operation(summary = "Peek humans", tags = {"Init"})
    public List<ConcreteHuman> humans() {
        return concreteHumanList;
    }

    @PostMapping("/attackTheArmoredTitan")
    @Operation(summary = "Attack the armored titan using decorator pattern and chain of responsibility pattern", tags = {"Mission"})
    public String attackTheArmoredTitan() {

        List<ConcreteHuman> specialCorps = concreteHumanList.stream()
                .filter(human -> human.getName().equals("Hanji") || human.getName().equals("Mikasa") || human.getName().equals("Jean") || human.getName().equals("Connie"))
                .toList();

        System.out.println("What equipment do we have?");
        System.out.println(specialCorps.stream().map(Human::getAbility).reduce((a, b) -> a + ", " + b).orElse("No equipment"));

        List<Human> decoratedHumans = new ArrayList<>();

//        2. 裝飾者模式: 為調查兵團的人員加上立體機動裝置及雷槍
        for (Human human : specialCorps) {
            human = new VerticalManeuveringEquipmentDecorator(human);
            human = new ThunderSpearDecorator(human);
            decoratedHumans.add(human);
        }

//        3.使用責任鏈模式(Chain of Responsibility Pattern)讓調查兵團的人先攻及鎧甲入人的左右臉頰肌肉，再由Mikasa進入鎧甲巨人的嘴巴攻擊咽喉
        AttackArmoredTitanHandler jeanAttackHandler = new ConcreteJeanAttackArmoredTitanHandler();
        AttackArmoredTitanHandler connieAttackHandler = new ConcreteConnieAttackArmoredTitanHandler();
        AttackArmoredTitanHandler hanjiAttackHandler = new ConcreteHanjiAttackArmoredTitanHandler();
        AttackArmoredTitanHandler mikasaAttackHandler = new ConcreteMikasaAttackArmoredTitanHandler();

        jeanAttackHandler.setNextHandler(connieAttackHandler);
        connieAttackHandler.setNextHandler(hanjiAttackHandler);
        hanjiAttackHandler.setNextHandler(mikasaAttackHandler);

        jeanAttackHandler.handleAttack(decoratedHumans);

        return "Armored Titan has been defeated";


    }

    @PostMapping("/beastTitanAttackMiddeEast")
    @Operation(summary = "Beast Titan attack Middle East Using state pattern to solve human and titan transform. Using strategy pattern to solve different attack strategy", tags = {"Mission"})
    public String BeastTitanAttackMiddeEast() {

//        1. 先把吉克的資料取出來
        ConcreteHuman beastTitan = concreteHumanList.stream()
                .filter(human -> human.getName().equals("Zeke"))
                .findFirst()
                .orElse(
                        new ConcreteHuman()
                                .setUuid(java.util.UUID.randomUUID())
                                .setName("Zeke")
                                .setAbility("Beast Titan")
                                .setTitanType(TitanType.BEAST_TITAN)
                                .setBloodType(null)
                                .setState(new HumanState())
                );

//        2. 開始進攻
        System.out.println(beastTitan.getName() + " is attacking Midde East");
        System.out.println("All the Aldians have been dropped out from the plane to middle east army's territory");

//        3. 使用狀態模式(State Pattern)讓吉克在巨人狀態下攻擊
        beastTitan.transform(TransformSignal.TITAN.getValue());

//        4. 使用策略模式(Strategy Pattern)讓吉克在巨人狀態下使用不同的攻擊策略
//        strategy 1
        beastTitan.getState().setAttackStrategy(new BeastTitanShoutsStrategy());
        System.out.println(beastTitan.attack());
        System.out.println("Middle East army has been defeated, the territory is now under Marley's control");

//        strategy 2
        beastTitan.getState().setAttackStrategy(new BeastTitanThrowsStrategy());
        System.out.println(beastTitan.attack());
        System.out.println("Middle East navy has been defeated, the war is over");

//        5. 使用狀態模式(State Pattern)讓吉克變回人類
        beastTitan.transform(TransformSignal.HUMAN.getValue());

        return "Beast Titan has finished his mission";
    }

    @PostMapping("/pureTitansChasingScoutsRegiment")
    @Operation(summary = "Pure Titans chasing Scouts Regiment by using Composite Pattern and observer pattern", tags = {"Mission"})
    public void pureTitansChasingScoutsRegiment() {

//        1. 先把吉克的資料取出來
        ConcreteHuman beastTitan = concreteHumanList.stream()
                .filter(human -> human.getName().equals("Zeke"))
                .findFirst()
                .orElse(
                        new ConcreteHuman()
                                .setUuid(java.util.UUID.randomUUID())
                                .setName("Zeke")
                                .setAbility("Beast Titan")
                                .setTitanType(TitanType.BEAST_TITAN)
                                .setBloodType(null)
                                .setState(new HumanState())
                );
//        1.2 建立10個純潔的艾爾迪亞人
        List<ConcreteHuman> pureTitans = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            pureTitans.add((ConcreteHuman) new FactoryProducer().createNormalErdianHumanByFactory(Integer.toString(i)));
        }

//        2. 吉克變成巨人
        beastTitan.transform(TransformSignal.TITAN.getValue());
//        3. 吉克使用吶喊技能，讓人類變成純潔巨人
        beastTitan.getState().setAttackStrategy(new BeastTitanShoutsStrategy());
        System.out.println(beastTitan.attack());
        PureTitanArmy pureTitanArmy = new PureTitanArmy();
//        TODO: 這裡應該可以改成使用觀察者模式(Observer Pattern)來讓吉克的吶喊技能通知所有的人類變成純潔巨人
        for (ConcreteHuman pureTitan : pureTitans) {
            pureTitan.transform(TransformSignal.TITAN.getValue());
            pureTitan.getState().setAttackStrategy(new PureTitanRunStrategy());
            pureTitanArmy.addTitan(pureTitan);
        }
//        4. 使用組合模式(Composite Pattern)讓所有的純潔巨人一起攻擊(追)調查兵團
        System.out.println("Pure Titans are chasing Scouts Regiment");
        pureTitanArmy.attack();

    }

    @PostMapping("ErwinCommandTheScoutsRegimentToAttackBeastTitan")
    @Operation(summary = "Erwin command the scouts regiment to attack beast titan by using command pattern", tags = {"Mission"})
    public void ErwinCommandTheScoutsRegimentToAttackBeastTitan() {

//        1. TODO 先暫時直接建立一個Erwin、一個Levi class，之後有空再改以現有的ConcreteHuman取代
//        2. 艾爾文下令調查兵團發動突襲，噴射煙霧彈來干擾野獸巨人的視線，並讓Levi隻身繞後偷襲野獸巨人
//
        Erwin erwin = new Erwin();
        Levi levi = new Levi();
        ScoutRegiment scoutRegiment = new ScoutRegiment();

        Command attackCommand = new AssaultCommand(scoutRegiment);
        Command smokeSignalCommand = new SmokeSignalCommand(scoutRegiment);
        Command attackFromBehindCommand = new FlankAttackCommand(levi);

//          2.1 艾爾文下令調查兵團發動突襲
        erwin.setCommand(attackCommand);
        erwin.executeCommand();
//          2.2 艾爾文下令調查兵團發動煙霧彈
        erwin.setCommand(smokeSignalCommand);
        erwin.executeCommand();
//          2.3 艾爾文下令Levi隻身繞後偷襲野獸巨人
        erwin.setCommand(attackFromBehindCommand);
        erwin.executeCommand();

    }

}
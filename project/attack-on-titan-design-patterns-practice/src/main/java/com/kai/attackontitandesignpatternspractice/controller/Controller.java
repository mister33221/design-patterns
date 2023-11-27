package com.kai.attackontitandesignpatternspractice.controller;

import com.kai.attackontitandesignpatternspractice.factory.factoryProducer.FactoryProducer;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteConnieAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteHanjiAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteJeanAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteMikasaAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.PureTitanArmy;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/init")
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
        for(ConcreteHuman pureTitan : pureTitans) {
            pureTitan.transform(TransformSignal.TITAN.getValue());
            pureTitan.getState().setAttackStrategy(new PureTitanRunStrategy());
            pureTitanArmy.addTitan(pureTitan);
        }
//        4. 使用組合模式(Composite Pattern)讓純潔巨人們追蹤調查兵團
        System.out.println("Pure Titans are chasing Scouts Regiment");
        pureTitanArmy.attack();

    }

}


/**
 * 適配器模式（Adapter）：如果你有一些現有的類（例如巨人、人類），但他們的接口不符合你的需求，你可以使用適配器模式來創建一個新的接口。
 * <p>
 * 橋接模式（Bridge）：你可以使用橋接模式來將巨人的抽象部分（例如巨人的類型）與他們的具體實現部分（例如巨人的行為）分離開來。
 * <p>
 * 組合模式（Composite）：如果你需要表示巨人或人類的層次結構，你可以使用組合模式。
 * <p>
 * 裝飾者模式（Decorator）：你可以使用裝飾者模式來動態地給巨人或人類添加新的能力或特性。
 * <p>
 * 外觀模式（Facade）：你可以創建一個外觀類來提供一個統一的接口，用於控制巨人或人類的複雜子系統。
 * <p>
 * 享元模式（Flyweight）：如果你需要大量的巨人或人類對象，你可以使用享元模式來共享相同或相似的對象，以節省記憶體。
 * <p>
 * 代理模式（Proxy）：你可以使用代理模式來控制對巨人或人類的訪問，例如，你可以創建一個代理來延遲巨人的創建，直到真正需要時才創建。
 * <p>
 * 鏈接責任模式（Chain of Responsibility）：你可以使用鏈接責任模式來創建一個巨人攻擊的鏈，每個巨人都有機會處理攻擊，或者將攻擊傳遞給下一個巨人。
 * <p>
 * 命令模式（Command）：你可以使用命令模式來封裝巨人或人類的行為為命令對象，這樣你可以在運行時動態地改變他們的行為。
 * <p>
 * 解釋器模式（Interpreter）：如果你需要解釋一種語言（例如巨人的語言），你可以使用解釋器模式。
 * <p>
 * 迭代器模式（Iterator）：你可以使用迭代器模式來提供一種方法來訪問巨人或人類集合的元素，而不需要暴露集合的內部表示。
 * <p>
 * 中介者模式（Mediator）：你可以使用中介者模式來封裝巨人或人類之間的交互，這樣他們不需要直接互相參考，從而使其解耦。
 * <p>
 * 備忘錄模式（Memento）：你可以使用備忘錄模式來保存巨人或人類的內部狀態，並在以後需要時恢復。
 * <p>
 * 觀察者模式（Observer）：你可以使用觀察者模式來實現巨人或人類的狀態變化時，通知其他對象的功能。
 * <p>
 * 狀態模式（State）：你可以使用狀態模式來讓巨人或人類在內部狀態改變時改變其行為。
 * <p>
 * 策略模式（Strategy）：你可以使用策略模式來定義一系列的算法，並將每一種算法分別放入獨立的類中，使其對象的行為可以在運行時根據不同的策略改變。
 * <p>
 * 模板方法模式（Template Method）：你可以使用模板方法模式來定義一個操作中的算法的骨架，並將一些步驟延遲到子類中。
 * <p>
 * 訪問者模式（Visitor）：你可以使用訪問者模式來定義一個新的操作，以處理巨人或人類的對象。這樣你可以在不改變他們的類的情況下添加新的操作。
 */
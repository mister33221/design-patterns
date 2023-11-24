package com.kai.attackontitandesignpatternspractice.controller;

import com.kai.attackontitandesignpatternspractice.exception.CustomException;
import com.kai.attackontitandesignpatternspractice.factory.factoryProducer.FactoryProducer;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteConnieAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteHangeAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteJeanAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.ConcreteMikasaAttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.mission.interfaces.AttackArmoredTitanHandler;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.ConcreteTitan;
import com.kai.attackontitandesignpatternspractice.model.decorator.ThunderSpearDecorator;
import com.kai.attackontitandesignpatternspractice.model.decorator.VerticalManeuveringEquipmentDecorator;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.BeastTitanShoutsStrategy;
import com.kai.attackontitandesignpatternspractice.model.tragtegy.BeastTitanThrowsStrategy;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/init")
public class InitController {


    List<ConcreteTitan> concreteTitanList = new ArrayList<>();
    List<ConcreteHuman> concreteHumanList = new ArrayList<>();


    @PostMapping("/createTitans")
    @Operation(summary = "Use Simple Factory to create a titan", tags = {"Init"})
    public String createTitan() throws CustomException {
//        1. 簡單工廠模式:工廠生產者
        FactoryProducer factoryProducer = new FactoryProducer();

//        2. 簡單工廠模式:具體工廠，透過工廠生產者取得具體工廠，把產出的巨人放進List
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.FOUNDING_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.COLOSSAL_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.ARMORED_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.FEMALE_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.BEAST_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.ATTACK_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.CART_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.WAR_HAMMER_TITAN));
        for (int i = 1; i <= 10; i++) {
            concreteTitanList.add((ConcreteTitan) factoryProducer.createTitansByFactory(TitanType.PURE_TITAN));
        }

        return "Titan created";
    }

    @GetMapping("/titans")
    @Operation(summary = "Peek titans", tags = {"Init"})
    public List<ConcreteTitan> titans() {
        return concreteTitanList;
    }

    @GetMapping("/titans/addbility/{UUID}")
    @Operation(summary = "Peek titans ability by id", tags = {"Init"})
    public String titansAbility(@PathVariable String UUID) {

        return concreteTitanList.stream().filter(titan -> titan.getUuid().toString().equals(UUID)).findFirst().get().getAbility();
    }

    @PostMapping("/createHumans")
    @Operation(summary = "Create humans", tags = {"Init"})
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
    @Operation(summary = "Create specific human", tags = {"Init"})
    public String createSpecificHuman() throws CustomException {
//        1. 簡單工廠模式:工廠生產者
        FactoryProducer factoryProducer = new FactoryProducer();

//        2. 預設的調查兵團人員名單
        HashMap<String, HashMap<String,String>> humanMap = new HashMap<>();
        // ability and titanType map
        humanMap.put("Erwin", new HashMap<>(){{put("ability", "Commander");put("titanType", "9");}});
        humanMap.put("Hange", new HashMap<>(){{put("ability", "Scientist");put("titanType", "9");}});
        humanMap.put("Levi", new HashMap<>(){{put("ability", "Captain");put("titanType", null);}});
        humanMap.put("Eren", new HashMap<>(){{put("ability", "Attack Titan");put("titanType", "6");}});
        humanMap.put("Armin", new HashMap<>(){{put("ability", "Colossal Titan");put("titanType", "2");}});
        humanMap.put("Mikasa", new HashMap<>(){{put("ability", "Ackerman");put("titanType", null);}});
        humanMap.put("Jean", new HashMap<>(){{put("ability", "Horse Rider");put("titanType", null);}});
        humanMap.put("Connie", new HashMap<>(){{put("ability", "Horse Rider");put("titanType", null);}});
        humanMap.put("Sasha", new HashMap<>(){{put("ability", "Horse Rider");put("titanType", null);}});

//        3. 簡單工廠模式:具體工廠，透過工廠生產者取得具體工廠，把產出的人類放進List
        for (String name : humanMap.keySet()) {
            concreteHumanList.add((ConcreteHuman) factoryProducer.createSpecificHumanByFactory(name, humanMap.get(name).get("ability"), humanMap.get(name).get("titanType")));
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

        List<Human> specialCorps = new ArrayList<>();



//        1. 把調查兵團中對付鎧甲巨人的人員取出
        for (ConcreteHuman human : concreteHumanList) {
            if (human.getName().equals("Hange") || human.getName().equals("Mikasa") || human.getName().equals("Jean") || human.getName().equals("Connie")) {
                specialCorps.add(human);
            }
        }

        System.out.println("What equipment do we have?");
        System.out.println(specialCorps.stream().map(Human::getAbility).reduce((a, b) -> a + ", " + b).get());

        List<Human> decoratedHumans = new ArrayList<>();

//        2. 裝飾者模式: 為調查兵團的人員家上立體機動裝置及雷槍
        for (Human human : specialCorps) {
            human = new VerticalManeuveringEquipmentDecorator(human);
            human = new ThunderSpearDecorator(human);
            decoratedHumans.add(human);
        }

//        3.使用責任鏈模式(Chain of Responsibility Pattern)讓調查兵團的人先攻及鎧甲入人的左右臉頰肌肉，再由Mikasa進入鎧甲巨人的嘴巴攻擊咽喉
        AttackArmoredTitanHandler jeanAttackHandler = new ConcreteJeanAttackArmoredTitanHandler();
        AttackArmoredTitanHandler connieAttackHandler = new ConcreteConnieAttackArmoredTitanHandler();
        AttackArmoredTitanHandler hangeAttackHandler = new ConcreteHangeAttackArmoredTitanHandler();
        AttackArmoredTitanHandler mikasaAttackHandler = new ConcreteMikasaAttackArmoredTitanHandler();

        jeanAttackHandler.setNextHandler(connieAttackHandler);
        connieAttackHandler.setNextHandler(hangeAttackHandler);
        hangeAttackHandler.setNextHandler(mikasaAttackHandler);

        jeanAttackHandler.handleAttack(decoratedHumans);

        return "Armored Titan has been defeated";


    }

    @PostMapping("/BeastTitanAttackMiddeEast")
    @Operation(summary = "Beast Titan attack Midde East Using strategy pattern", tags = {"Mission"})
    public String BeastTitanAttackMiddeEast() {

        ConcreteTitan beastTitan = concreteTitanList.stream().filter(titan -> titan.getName().equals("Beast Titan")).findFirst().get();
        System.out.println(beastTitan.getName() + " is attacking Midde East");
        System.out.println("All the Aldians have been dropped out from the plane to middle east army's territory");

//        strategy 1
        beastTitan.setTitanAttackStrategy(new BeastTitanShoutsStrategy());
        System.out.println(beastTitan.attack());
        System.out.println("Middle East army has been defeated, the territory is now under Marley's control");

//        strategy 2
        beastTitan.setTitanAttackStrategy(new BeastTitanThrowsStrategy());
        System.out.println(beastTitan.attack());
        System.out.println("Middle East navy has been defeated, the war is over");

        return "Beast Titan has finished his mission";
    }

    @PostMapping("/transformBetweenTitanAndHuman")
    @Operation(summary = "Transform between titan and human by using state pattern", tags = {"Mission"})
    public String transformBetweenTitanAndHuman()  {

        return null;
    }
}


/**
 * 適配器模式（Adapter）：如果你有一些現有的類（例如巨人、人類），但他們的接口不符合你的需求，你可以使用適配器模式來創建一個新的接口。
 *
 * 橋接模式（Bridge）：你可以使用橋接模式來將巨人的抽象部分（例如巨人的類型）與他們的具體實現部分（例如巨人的行為）分離開來。
 *
 * 組合模式（Composite）：如果你需要表示巨人或人類的層次結構，你可以使用組合模式。
 *
 * 裝飾者模式（Decorator）：你可以使用裝飾者模式來動態地給巨人或人類添加新的能力或特性。
 *
 * 外觀模式（Facade）：你可以創建一個外觀類來提供一個統一的接口，用於控制巨人或人類的複雜子系統。
 *
 * 享元模式（Flyweight）：如果你需要大量的巨人或人類對象，你可以使用享元模式來共享相同或相似的對象，以節省記憶體。
 *
 * 代理模式（Proxy）：你可以使用代理模式來控制對巨人或人類的訪問，例如，你可以創建一個代理來延遲巨人的創建，直到真正需要時才創建。
 *
 * 鏈接責任模式（Chain of Responsibility）：你可以使用鏈接責任模式來創建一個巨人攻擊的鏈，每個巨人都有機會處理攻擊，或者將攻擊傳遞給下一個巨人。
 *
 * 命令模式（Command）：你可以使用命令模式來封裝巨人或人類的行為為命令對象，這樣你可以在運行時動態地改變他們的行為。
 *
 * 解釋器模式（Interpreter）：如果你需要解釋一種語言（例如巨人的語言），你可以使用解釋器模式。
 *
 * 迭代器模式（Iterator）：你可以使用迭代器模式來提供一種方法來訪問巨人或人類集合的元素，而不需要暴露集合的內部表示。
 *
 * 中介者模式（Mediator）：你可以使用中介者模式來封裝巨人或人類之間的交互，這樣他們不需要直接互相參考，從而使其解耦。
 *
 * 備忘錄模式（Memento）：你可以使用備忘錄模式來保存巨人或人類的內部狀態，並在以後需要時恢復。
 *
 * 觀察者模式（Observer）：你可以使用觀察者模式來實現巨人或人類的狀態變化時，通知其他對象的功能。
 *
 * 狀態模式（State）：你可以使用狀態模式來讓巨人或人類在內部狀態改變時改變其行為。
 *
 * 策略模式（Strategy）：你可以使用策略模式來定義一系列的算法，並將每一種算法分別放入獨立的類中，使其對象的行為可以在運行時根據不同的策略改變。
 *
 * 模板方法模式（Template Method）：你可以使用模板方法模式來定義一個操作中的算法的骨架，並將一些步驟延遲到子類中。
 *
 * 訪問者模式（Visitor）：你可以使用訪問者模式來定義一個新的操作，以處理巨人或人類的對象。這樣你可以在不改變他們的類的情況下添加新的操作。
 */
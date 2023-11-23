package com.kai.attackontitandesignpatternspractice.controller;

import com.kai.attackontitandesignpatternspractice.exception.CustomException;
import com.kai.attackontitandesignpatternspractice.factory.factoryProducer.FactoryProducer;
import com.kai.attackontitandesignpatternspractice.model.ConcreteHuman;
import com.kai.attackontitandesignpatternspractice.model.ConcreteTitan;
import com.kai.attackontitandesignpatternspractice.model.abstracts.TitanDecorator;
import com.kai.attackontitandesignpatternspractice.model.enums.TitanType;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Human;
import com.kai.attackontitandesignpatternspractice.model.interfaces.Titan;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/init")
public class InitController {


    List<ConcreteTitan> concreteTitanList = new ArrayList<>();
    List<Human> concreteHumanList = new ArrayList<>();


    @PostMapping("/createTitan")
    @Operation(summary = "Use Simple Factory to create a titan", tags = {"Init"})
    public String createTitan() throws CustomException {
        FactoryProducer factoryProducer = new FactoryProducer();

        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.FOUNDING_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.COLOSSAL_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.ARMORED_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.FEMALE_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.BEAST_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.ATTACK_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.CART_TITAN));
        concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.WAR_HAMMER_TITAN));
        for (int i = 1; i <= 10; i++) {
            concreteTitanList.add((ConcreteTitan) factoryProducer.getTitanFactory(TitanType.PURE_TITAN));
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

//        for (ConcreteTitan titan : concreteTitanList) {
//            if (titan.getUuid().toString().equals(UUID)) {
//                return titan.ability();
//            }
//        }
        return "No such titan";
    }

//    @GetMapping("/humans")
//    @Operation(summary = "Peek humans", tags = {"Init"})
//    public List<Human> humans() {
//        return concreteHumanList;
//    }

}

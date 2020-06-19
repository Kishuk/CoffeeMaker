package com.kishuk.coffeeMaker.DrinkType;

import com.kishuk.coffeeMaker.errors.InvalidDrinkException;

import java.util.HashMap;
import java.util.Map;


public class DrinkManager {
    final static int DefaultDrinkPrepTimeInSec = 5;
    Map<String, DrinkType> drinkTypeMapping;

    public void addDrinkType(String drinkType, DrinkRecipe recipe) {
        drinkTypeMapping.put(drinkType, new DrinkType(drinkType, recipe));
    }

    public DrinkManager() {
        this.drinkTypeMapping = new HashMap<>();
    }


    public DrinkType getDrinkType(String type) throws InvalidDrinkException {
        if(this.drinkTypeMapping.containsKey(type)) return this.drinkTypeMapping.get(type);
        else throw new InvalidDrinkException();
    }
}

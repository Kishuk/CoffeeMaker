package com.kishuk.coffeeMaker.DrinkType;

import com.kishuk.coffeeMaker.IngredinetType.Ingredient;

import java.util.Map;

public class DrinkRecipe {

    Map<Ingredient, Integer> config;

    public DrinkRecipe(Map<Ingredient, Integer> config) {
        this.config = config;
    }

    public Map<Ingredient, Integer> getMapping() {
        return this.config;
    }
}

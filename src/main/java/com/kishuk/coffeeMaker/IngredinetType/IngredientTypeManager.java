package com.kishuk.coffeeMaker.IngredinetType;

import com.kishuk.coffeeMaker.errors.InvalidIngredientException;

import java.util.HashMap;
import java.util.Map;

public class IngredientTypeManager {

    Map<String, Ingredient> ingredientMapping;

    public void addIngredientType(String type) {
        ingredientMapping.put(type, new Ingredient(type));
    }

    public IngredientTypeManager() {
        this.ingredientMapping = new HashMap<>();
    }


    public Ingredient getIngredient(String type) throws InvalidIngredientException {
        if (this.ingredientMapping.containsKey(type)) return this.ingredientMapping.get(type);
        throw new InvalidIngredientException();
    }
}

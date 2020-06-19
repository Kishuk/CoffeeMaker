package com.kishuk.coffeeMaker.sm;

import com.kishuk.coffeeMaker.DrinkType.DrinkRecipe;
import com.kishuk.coffeeMaker.IngredinetType.Ingredient;
import com.kishuk.coffeeMaker.errors.InsufficientIngredientsException;

public interface StockManagerI {
    void addStock(Ingredient type, int qua);
    void getIngredients(DrinkRecipe mapping) throws InsufficientIngredientsException;
}

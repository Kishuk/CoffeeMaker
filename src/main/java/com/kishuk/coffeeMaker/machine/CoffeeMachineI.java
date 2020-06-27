package com.kishuk.coffeeMaker.machine;

import com.kishuk.coffeeMaker.DrinkType.DrinkType;
import com.kishuk.coffeeMaker.errors.*;

public interface CoffeeMachineI {
    DrinkType getDrink(int outlet, String drinkName) throws InvalidOutlet, InvalidDrinkException, InsufficientIngredientsException, InterruptedException, OutletBusyException;

    void fillStock(String ingre, int quantity) throws InvalidIngredientException;
}

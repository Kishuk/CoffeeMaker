package com.kishuk.coffeeMaker.machine;

import com.kishuk.coffeeMaker.errors.*;

public interface CoffeeMachineI {
    void getDrink(int outlet, String drinkName) throws InvalidOutlet, InvalidDrinkException, InsufficientIngredientsException, InterruptedException, OutletBusyException;

    void fillStock(String ingre, int quantity) throws InvalidIngredientException;
}

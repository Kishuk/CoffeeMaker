package com.kishuk.coffeeMaker.machine;

import com.kishuk.coffeeMaker.DrinkType.DrinkType;
import com.kishuk.coffeeMaker.errors.InsufficientIngredientsException;
import com.kishuk.coffeeMaker.errors.InvalidDrinkException;

public interface DrinkMakerI {
    DrinkType getDrink(String drinkName) throws InvalidDrinkException, InsufficientIngredientsException, InterruptedException;
}

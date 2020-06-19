package com.kishuk.coffeeMaker.errors;

import com.kishuk.coffeeMaker.IngredinetType.Ingredient;

public class InsufficientIngredientsException extends Exception {

    public InsufficientIngredientsException() {
        super("InsufficientInngredients");
    }

    public InsufficientIngredientsException(Ingredient type, int available, int req) {
        super(String.format("InsufficientInngredients for %s req: %d but have: %d", type.toString(), req, available));
    }
}

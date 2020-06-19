package com.kishuk.coffeeMaker.machine;

import com.kishuk.coffeeMaker.DrinkType.DrinkManager;
import com.kishuk.coffeeMaker.DrinkType.DrinkType;
import com.kishuk.coffeeMaker.errors.InsufficientIngredientsException;
import com.kishuk.coffeeMaker.errors.InvalidDrinkException;
import com.kishuk.coffeeMaker.sm.StockManagerI;

public class DrinkMaker implements DrinkMakerI {

    private StockManagerI sm;
    private DrinkManager dm;

    public DrinkMaker(StockManagerI sm, DrinkManager dm) {
        this.sm = sm;
        this.dm = dm;
    }

    @Override
    public DrinkType getDrink(String drinkName) throws InvalidDrinkException, InsufficientIngredientsException, InterruptedException {
      DrinkType dt = this.dm.getDrinkType(drinkName);

      this.sm.getIngredients(dt.getConfig());
      dt.prepareDrink();

      return dt;
    }
}

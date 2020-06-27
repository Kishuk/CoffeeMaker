package com.kishuk.coffeeMaker.machine;

import com.kishuk.coffeeMaker.DrinkType.DrinkManager;
import com.kishuk.coffeeMaker.DrinkType.DrinkType;
import com.kishuk.coffeeMaker.IngredinetType.IngredientTypeManager;
import com.kishuk.coffeeMaker.errors.*;
import com.kishuk.coffeeMaker.sm.StockManager;

import java.util.List;
import java.util.stream.IntStream;

public class CoffeeMachine implements CoffeeMachineI {

    StockManager sm;
    int totalOutlet;
    DrinkMaker[] drinkMakers;
    DrinkManager dm;
    IngredientTypeManager im;

    boolean[] outletLock;

    public CoffeeMachine(int totalOutlet, IngredientTypeManager im, DrinkManager dm) {

        this.totalOutlet = totalOutlet;
        this.drinkMakers = new DrinkMaker[totalOutlet];
        this.outletLock = new boolean[totalOutlet];

        this.sm = new StockManager();

        this.dm = dm;
        this.im = im;

        IntStream.range(0,totalOutlet).forEach(i -> drinkMakers[i] = new DrinkMaker(sm, dm));
    }

    private synchronized void takeOutletLock(int outlet) throws OutletBusyException {
        if(this.outletLock[outlet -1]) throw new OutletBusyException();
        this.outletLock[outlet-1] = true;
    }

    private void releaseOutletLock(int outlet) throws OutletBusyException {
        this.outletLock[outlet-1] = false;
    }

    @Override
    public DrinkType getDrink(int outlet, String drinkName) throws InvalidOutlet, InvalidDrinkException, InsufficientIngredientsException, InterruptedException, OutletBusyException {

        if(outlet>this.totalOutlet) throw new InvalidOutlet();

        this.takeOutletLock(outlet);

        try {
            DrinkType drink = this.drinkMakers[outlet - 1].getDrink(drinkName);
            return drink;
        } finally {
            this.releaseOutletLock(outlet);
        }

    }


    @Override
    public void fillStock(String ingre, int quantity) throws InvalidIngredientException {
        this.sm.addStock(this.im.getIngredient(ingre), quantity);
    }
}

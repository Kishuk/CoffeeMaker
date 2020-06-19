package com.kishuk.coffeeMaker.sm;
import com.kishuk.coffeeMaker.DrinkType.DrinkRecipe;
import com.kishuk.coffeeMaker.IngredinetType.Ingredient;
import com.kishuk.coffeeMaker.errors.InsufficientIngredientsException;

import java.util.HashMap;
import java.util.Map;

public class StockManager implements StockManagerI {

    Map<Ingredient, Integer> stocks;

    public StockManager() {
        this.stocks = new HashMap<>();
    }

    @Override
    public void addStock(Ingredient type, int qua) {

        int prevQuna = 0;
        if(this.stocks.containsKey(type)) {
            prevQuna = this.stocks.get(type);
        }

        this.stocks.put(type, prevQuna+qua);

    }

    private int getStock(Ingredient type) {
        return stocks.getOrDefault(type, 0);
    }

    private void releaseStock(Ingredient type, int quant) throws InsufficientIngredientsException {

        int curr = stocks.getOrDefault(type, 0);
        if (curr< quant) throw new InsufficientIngredientsException(type, curr, quant);
        else stocks.put(type, curr-quant);

    }
    private void checkStock(Ingredient type, int quant) throws InsufficientIngredientsException {

        int curr = getStock(type);
        if (curr< quant) throw new InsufficientIngredientsException(type, curr, quant);

    }


    @Override
    public synchronized void getIngredients(DrinkRecipe mapping) throws InsufficientIngredientsException {
        for (Map.Entry<Ingredient, Integer> entry : mapping.getMapping().entrySet()) {
            this.checkStock(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Ingredient, Integer> entry : mapping.getMapping().entrySet()) {
            this.releaseStock(entry.getKey(), entry.getValue());
        }

    }
}

package com.kishuk.coffeeMaker.DrinkType;



public class DrinkType {

    String type;
    DrinkRecipe config;
    int timeSec;

    public DrinkType(String type, DrinkRecipe config) {
        this.type = type;
        this.config = config;
        this.timeSec = DrinkManager.DefaultDrinkPrepTimeInSec;
    }

    public void prepareDrink() throws InterruptedException {
        Thread.sleep(this.timeSec *1000);
    }

    public DrinkType(String type, DrinkRecipe config, int timeSec) {
        this.type = type;
        this.config = config;
        this.timeSec = timeSec;
    }

    public DrinkRecipe getConfig() {
        return config;
    }

    @Override
    public String toString() {
        return "DrinkType{" +
                "type='" + type + '\'' +
                '}';
    }
}

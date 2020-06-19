package com.kishuk.coffeeMaker.IngredinetType;

public class Ingredient {
    String type;

    Ingredient(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "type='" + type + '\'' +
                '}';
    }
}

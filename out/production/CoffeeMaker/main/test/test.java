package test;

import com.kishuk.coffeeMaker.CoffeeMakerClient;
import com.kishuk.coffeeMaker.errors.InvalidIngredientException;

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        try {
            CoffeeMakerClient.main(new String[]{new File("src/main/test").getAbsolutePath() +"/test.txt"});
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidIngredientException e) {
            System.out.printf("Ingredient required is not present %s", e.getMessage());
        }
    }
}

package test;

import com.kishuk.coffeeMaker.CoffeeMakerClient;
import com.kishuk.coffeeMaker.errors.InvalidIngredientException;

import java.io.File;
import java.io.IOException;

public class Test {

    CoffeeMakerClient client;

    void Test1() {
        try {
            client.process(new File("src/main/test").getAbsolutePath() +"/test.txt");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidIngredientException e) {
            System.out.printf("Ingredient required is not present %s", e.getMessage());
        }
    }




    public static void main(String[] args) {

        Test test = new Test();
        test.client = new CoffeeMakerClient();
        test.Test1();
    }
}

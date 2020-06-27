package com.kishuk.coffeeMaker;

import com.kishuk.coffeeMaker.DrinkType.DrinkManager;
import com.kishuk.coffeeMaker.DrinkType.DrinkRecipe;
import com.kishuk.coffeeMaker.DrinkType.DrinkType;
import com.kishuk.coffeeMaker.IngredinetType.Ingredient;
import com.kishuk.coffeeMaker.IngredinetType.IngredientTypeManager;
import com.kishuk.coffeeMaker.errors.InvalidIngredientException;
import com.kishuk.coffeeMaker.machine.CoffeeMachine;
import com.kishuk.coffeeMaker.machine.CoffeeMachineI;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class CoffeeMakerClient {

    BufferedReader reader;
    IngredientTypeManager im = new IngredientTypeManager();
    DrinkManager dm = new DrinkManager();

    CoffeeMachineI machine;

    public void readAllIngredients() throws IOException {

        String[] is = reader.readLine().split(" ");
        for(String ingredient : is) im.addIngredientType(ingredient);
    }

    public void readAllDrinkConfig() throws IOException, InvalidIngredientException {

        Integer n = Integer.parseInt(reader.readLine());
        for(int i=0;i<n;i++) {

            String[] s = reader.readLine().split(" ");
            String drinkName = s[0];
            int m = Integer.parseInt(s[1]);

            Map<Ingredient, Integer> mapping = new HashMap<>();
            for(int j=0;j<m;j++) {

                s = reader.readLine().split(" ");
                String ingredient = s[0];
                int quan = Integer.parseInt(s[1]);
                mapping.put(im.getIngredient(ingredient), quan);

            }
            DrinkRecipe dr = new DrinkRecipe(mapping);
            dm.addDrinkType(drinkName, dr);
            System.out.printf("Drink added: %s\n", drinkName);
        }

    }


    public void createMachine() throws IOException {

        int n = Integer.parseInt(reader.readLine());
        machine = new CoffeeMachine(n, im, dm);

    }

    public void fillStock() throws IOException, InvalidIngredientException {

        int n = Integer.parseInt(reader.readLine());
        for(int i=0;i<n;i++) {

            String[] s = reader.readLine().split(" ");
            String ingredient = s[0];
            int m = Integer.parseInt(s[1]);
            machine.fillStock(ingredient, m);
        }

    }

    public void processQuery(String q, CountDownLatch latch) {
        try {
            System.out.printf("request received for %s\n", q);
            String[] s =q.split(" ");

            switch (s[0]) {
                case "get": {
                    int onum = Integer.parseInt(s[2]);
                    String drinkName = s[1];
                    try {
                        DrinkType drink  = machine.getDrink(onum, drinkName);
                        System.out.println(drink.toString() + " from outlet " + onum);
                    } catch (Exception e) {
                        System.out.printf("get query not process got error: %s\n", e.getMessage());
                    }
                    break;
                }
                case "refill": {
                    String ing = s[1];
                    int quan = Integer.parseInt(s[2]);
                    try {
                        machine.fillStock(ing, quan);
                    } catch (Exception e) {
                        System.out.printf("refill query not process got error: %s\n", e.getMessage());
                    }
                    break;
                }
            }
        }finally {
            latch.countDown();
        }

    }


    public void processQueries() throws IOException, InterruptedException {
        int n = Integer.parseInt(reader.readLine());

        System.out.println("\n\n\n\n\n ------------------Results---------------");
        CountDownLatch latch= new CountDownLatch(n);
        for(int i=0;i<n;i++) {

            String q = reader.readLine();

            Thread newThread = new Thread(() -> processQuery(q, latch));
            newThread.start();
        }
        latch.await();
    }

    public static void main(String[] args) throws IOException, InvalidIngredientException, InterruptedException {
        new CoffeeMakerClient().processSysIn(args);
    }


    public void processSysIn(String[] args) throws IOException, InvalidIngredientException, InterruptedException {

        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
//        writer = new BufferedWriter(new OutputStreamWriter(System.out));


        System.out.println("give all the supported ingredients space separated in single line");
        readAllIngredients();

        System.out.println("give all the supported drinks type and there recipe");
        System.out.println("first line should contain n: total number of drinks. for each drink give name and a integer m denoting total ingredient require");
        System.out.println("next m lines should contain 2 space separated value: ingredient name and quantity required.");
        readAllDrinkConfig();


        System.out.println("give total outlet needed in machine");
        createMachine();

        System.out.println("first line should contain n: total number of stock refill");
        System.out.println("next n lines should contain 2 space separated value: ingredient name and quantity stocked.");
        fillStock();

        System.out.println("first line should contain n: total number of query");
        processQueries();


        reader.close();
    }


    public void process(String fileName) throws IOException, InvalidIngredientException, InterruptedException {
        setReader(fileName);

        readAllIngredients();
        readAllDrinkConfig();
        createMachine();
        fillStock();
        processQueries();

        reader.close();
    }

    private void setReader(String fileName) throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    }
}



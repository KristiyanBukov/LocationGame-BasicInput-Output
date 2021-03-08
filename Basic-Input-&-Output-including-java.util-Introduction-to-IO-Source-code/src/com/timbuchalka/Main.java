package com.timbuchalka;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);

        Map<String,String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

//        System.out.println(vocabulary.get("QUIT"));
        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0) {
                break;

            }
            Map<String, Integer> exits = locations.get(loc).getExits(); // location[1] exits ..
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase(); // User input
            if(direction.length() > 1) {        // If input is not 1 char, we should split the string  -->
                String[] words = direction.split(" "); // Splitting
                for(String word : words) { // Searching in the array[]
                    if(vocabulary.containsKey(word)) { //  if vocabulary contains key equal to user input
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)) {
            loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}

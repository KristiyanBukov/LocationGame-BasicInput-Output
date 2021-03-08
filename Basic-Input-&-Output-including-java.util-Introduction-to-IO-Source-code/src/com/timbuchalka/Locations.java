package com.timbuchalka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (FileWriter locfile = new FileWriter("locations.txt");
             FileWriter dirFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) {
                locfile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }
            }
        }

//        FileWriter locFile = null;
//        try {
//        locFile = new FileWriter("locations.txt");
//        for(Location location : locations.values()) {
//            locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//        }
//        } catch (IOException e) {
//            System.out.println("In catch block");
//            e.printStackTrace();
//        } finally {
//            System.out.println("In finally block");
//            try{
//                if(locFile != null) {
//                    System.out.println("Attempting to close locfile");
//                    locFile.close();
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    static {

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println(loc + "," + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now read the exits

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")))) {
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {

                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());
//                String dest = scanner.nextLine();
//                int destination = Integer.parseInt(dest);
                System.out.println(loc + "," + direction + "," + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }


//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building ",null)); // W,E,S,N,Q
//        locations.put(2, new Location(2, "You are at the top of the hill",null)); // N,Q
//        locations.put(3, new Location(3, "You are inside of a building, a well house for a small spring",null));
//        locations.put(4, new Location(4, "You are at the valley beside the stream",null));
//        locations.put(5, new Location(5, "You are in the forest",null));
//
//
//        locations.get(1).addExit("W", 2);
//        locations.get(1).addExit("E", 3);
//        locations.get(1).addExit("S", 4);
//        locations.get(1).addExit("N", 5);
//        //  locations.get(1).addExit("Q", 0); // added in constructor
//
//        locations.get(2).addExit("N", 5);
//        // locations.get(2).addExit("Q", 0);
//
//        locations.get(3).addExit("W", 1);
//        // locations.get(3).addExit("Q", 0);
//
//        locations.get(4).addExit("N",1);
//        locations.get(4).addExit("W",2);
//        // locations.get(4).addExit("Q",0);
//
//        locations.get(5).addExit("S",1);
//        locations.get(5).addExit("W",2);
//          locations.get(5).addExit("Q",0);
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}

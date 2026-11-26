import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

class Cat {
    private String name;
    private String type;
    private int fullness;
    private int happiness;
    private int energy;
    private int cleanliness;

    public Cat(String name, String type) {
        this.name = name;
        this.type = type;
        fullness = 50;
        happiness = 50;
        energy = 100;
        cleanliness = 100;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void displayCatArt() {
        System.out.println();

        if (type.equals("Orange Tabby")) {
            System.out.println(" /\\_/\\");
            System.out.println("( o.o )");
            System.out.println(" > ^ <   Orange Tabby");
        } 
        else if (type.equals("Persian")) {
            System.out.println(" /\\___/\\");
            System.out.println("(  o o  )");
            System.out.println(" /   ^   \\");
            System.out.println("(_________)  Persian");
        } 
        else if (type.equals("Siamese")) {
            System.out.println(" /\\_/\\");
            System.out.println("( -.- )");
            System.out.println(" > ^ <   Siamese");
        } 
        else if (type.equals("Black Cat")) {
            System.out.println(" /\\_/\\");
            System.out.println("( o.o )");
            System.out.println(" > # <   Black Cat");
        } 
        else {
            System.out.println(" /\\_/\\");
            System.out.println("( o.o )");
            System.out.println(" > ^ <");
        }

        System.out.println();
    }

    public void feed() {
        int oldFullness = fullness;

        fullness = Math.min(100, fullness + 20);

        displayCatArt();

        System.out.println(name + " enjoyed the food!");
        System.out.println("FULLNESS: "
            + oldFullness + " -> " + fullness);
    }

    public void play() {
        int oldHappiness = happiness;
        int oldEnergy = energy;

        happiness = Math.min(100, happiness + 20);
        energy = Math.max(0, energy - 10);

        displayCatArt();

        System.out.println(name + " had so much fun!");
        System.out.println("HAPPINESS: "
            + oldHappiness + " -> " + happiness);
        System.out.println("ENERGY: "
            + oldEnergy + " -> " + energy);
    }

    public void displayStats() {
        displayCatArt();

        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Fullness: " + fullness);
        System.out.println("Happiness: " + happiness);
        System.out.println("Energy: " + energy);
        System.out.println("Cleanliness: " + cleanliness);
    }
}

public class LittlePaws {
    private static HashMap<String, Cat> cats = new HashMap<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to Little Paws!");
        System.out.println("Virtual Cat Adoption Center");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Adopt a cat");
            System.out.println("2. View adopted cats");
            System.out.println("3. Feed a cat");
            System.out.println("4. Play with a cat");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    adoptCat();
                    break;

                case "2":
                    viewCats();
                    break;

                case "3":
                    interactWithCat("feed");
                    break;

                case "4":
                    interactWithCat("play");
                    break;

                case "5":
                    running = false;
                    System.out.println(
                        "Thanks for visiting Little Paws!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        input.close();
    }

    private static void adoptCat() {
        String[] types = {
            "Orange Tabby",
            "Persian",
            "Siamese",
            "Black Cat"
        };

        System.out.println("\n--- Available Cats ---");

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        System.out.print("Choose a cat type: ");

        int choice;

        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return;
        }

        if (choice < 1 || choice > types.length) {
            System.out.println("Invalid cat type.");
            return;
        }

        System.out.print("Enter your cat's name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        if (cats.containsKey(name.toLowerCase())) {
            System.out.println(
                "You already have a cat with that name.");
            return;
        }

        Cat newCat = new Cat(name, types[choice - 1]);

        cats.put(name.toLowerCase(), newCat);

        System.out.println(
            "Congratulations! You adopted " + name + "!");
    }

    private static void viewCats() {
        if (cats.isEmpty()) {
            System.out.println(
                "You haven't adopted any cats yet.");
            return;
        }

        List<Cat> catList = new ArrayList<>(cats.values());

        while (true) {
            System.out.println("\n--- Your Adopted Cats ---");

            for (int i = 0; i < catList.size(); i++) {
                System.out.println((i + 1) + ". "
                    + catList.get(i).getName());
                System.out.println();
            }

            System.out.println("0. Return to main menu");
            System.out.print("Choose a cat: ");

            int choice;

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > catList.size()) {
                System.out.println("Invalid cat selection.");
                continue;
            }

            Cat cat = catList.get(choice - 1);

            System.out.println("\n--- Cat Profile ---");
            cat.displayStats();

            System.out.print(
                "\nPress Enter to return to your cats...");
            input.nextLine();
        }
    }

    private static void interactWithCat(String action) {
        if (cats.isEmpty()) {
            System.out.println(
                "You haven't adopted any cats yet.");
            return;
        }

        List<Cat> catList = new ArrayList<>(cats.values());

        System.out.println("\n--- Select a Cat ---");

        for (int i = 0; i < catList.size(); i++) {
            Cat cat = catList.get(i);

            System.out.println((i + 1) + ". "
                + cat.getName() + " (" + cat.getType() + ")");
            System.out.println();
        }

        System.out.print("Choose a cat: ");

        int choice;

        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return;
        }

        if (choice < 1 || choice > catList.size()) {
            System.out.println("Invalid cat selection.");
            return;
        }

        Cat cat = catList.get(choice - 1);

        if (action.equals("feed")) {
            cat.feed();
        } else if (action.equals("play")) {
            cat.play();
        }
    }
}

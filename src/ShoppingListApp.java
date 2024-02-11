import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListApp {

    private ArrayList<Item> shoppingList;
    private Scanner scan;

    public ShoppingListApp() {
        shoppingList = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    public void start() {

        readData();  // read data in from the file

        System.out.println("---------------------------------");
        System.out.println("Welcome to the shopping list app!");
        System.out.println("---------------------------------");
        String option = "";
        while (!option.equals("q")) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("(v)iew shopping list");
            System.out.println("(a)dd item to list");
            System.out.println("(e)dit an item's price");
            System.out.println("(r)emove item from list");
            System.out.println("(q)uit");
            System.out.print("Enter choice: ");
            option = scan.nextLine();
            if (option.equals("v")) {
                viewShoppingList();
            } else if (option.equals("a")) {
                addItemToList();
            } else if (option.equals("e")) {
                editItemPrice();
            } else if (option.equals("r")) {
                removeItem();
            }
            saveData();
        }
        System.out.println("Goodbye!");
    }

    private void viewShoppingList() {
        if(shoppingList.size() == 0){
            System.out.println("Empty List!");
        }
        else{
            for(int i = 0; i < shoppingList.size(); i++){
                System.out.println(shoppingList.get(i).getName());
            }
        }
    }

    private void addItemToList() {
        System.out.print("What item would you like to add: ");
        String askName = scan.nextLine();
        System.out.print("What is its cost: ");
        int askPrice = scan.nextInt();
        scan.nextLine();
        shoppingList.add(new Item(askName,askPrice));
    }

    private void editItemPrice() {
        System.out.print("What item price would you like to change?: ");
        String askName = scan.nextLine();
        for(int i = 0; i < shoppingList.size(); i++) {
            if (!shoppingList.get(i).getName().equals(askName)) {
                System.out.println("Item not in list");
            } else {
                System.out.print("What is the price you would like to set? ");
                int askPrice = scan.nextInt();
                scan.nextLine();
                shoppingList.get(i).setPrice(askPrice);
            }
        }
    }

    private void removeItem() {
        System.out.println("What is the name of the item you would like to remove?: ");
        String askName = scan.nextLine();
        for(int i = 0; i < shoppingList.size(); i++) {
            if (!shoppingList.get(i).getName().equals(askName)) {
                System.out.println("Item not in list");
            } else {
                shoppingList.remove(i);
            }
        }
    }

    private void readData() {
        // TODO: write this method: load the shopping list data from your shoppinglist.txt file and populate shoppingList
        ArrayList<String> words = new ArrayList<>();
        try {
            File myFile = new File("src\\shoppingList.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                words.add(data);
            }
        } catch (IOException exception) {
            System.out.println("error");
        }
    }

        private void saveData(){
            try(PrintWriter file = new PrintWriter("src\\shoppinglist.txt")) {
                for (Item items : shoppingList) {
                    file.write(items.getName());
                }
            } catch (IOException exception) {
                System.out.println("error");
            }
        }
    }


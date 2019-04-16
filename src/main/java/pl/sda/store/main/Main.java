package pl.sda.store.main;

import pl.sda.store.logic.InventoryManager;
import pl.sda.store.logic.ProductManager;
import pl.sda.store.model.Product;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            // menu level 1
            System.out.println("Enter a number: ");
            System.out.println("1 - Add product. ");
            System.out.println("2 - Add to inventory.");
            System.out.println("3 - Remove Student (by id).");
            System.out.println("4 - Exit.");
            int choice1 = input.nextInt();

            switch (choice1) {
                case 1:
                    System.out.println("Enter name");
                    String name = input.next();
//todo: get id ?
                    ProductManager.addProduct(new Product(name));
                    System.out.println("---------Dodano produkt :" +
                            " " + name +
                            " " + "id");
                    break;
                case 2:
                    System.out.println("Enter quantity");
                    String quantity = input.next();
                    System.out.println("Enter value");
                    String value = input.next();
                    System.out.println("Enter product id");
                    Long idproduct = input.nextLong();
                    InventoryManager.addToInventory(quantity,value,idproduct);
                    System.out.println("---------Dodano inventory");
                    break;
                case 3:

                    System.out.println("Enter student id to remove");
                    long idToRemove = input.nextLong();
                    break;
                case 4:

                    System.exit(1);
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }
}

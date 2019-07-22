package pl.sda.store.main;

import pl.sda.store.logic.*;
import pl.sda.store.model.Inventory;
import pl.sda.store.model.Invoice;
import pl.sda.store.model.Product;
import pl.sda.store.model.ProductSale;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            // menu level 1
            System.out.println("Enter a number: ");
            System.out.println("1 - Add product. ");
            System.out.println("2 - Add to inventory.");
            System.out.println("3 - List of all product.");
            System.out.println("4 - List inventory of product.");
            System.out.println("5 - List all inventory.");
            System.out.println("6 - Add invoice.");
            System.out.println("7 - Add sale to invoice with id");
            System.out.println("8 - Exit.");
            int choice1 = input.nextInt();

            switch (choice1) {
                case 1:
                    System.out.println("Enter name");
                    String name = input.next();
                    Product product = new Product(name);
//todo: get id ?
                    ProductManager.addProduct(product);
                    System.out.println("---------Dodano produkt :  "
                             + name +
                            "   o id: " + product.getId());
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

                    System.out.println("---------Lista produktów");
                    ProductManager.listProducts();
                    break;
                case 4:

                    System.out.println("Enter product id");
                    Long idproductInve = input.nextLong();
                    System.out.println("---------Inventory dla danego produktu");
                    ProductManager.listInventory(idproductInve);
                    break;
                case 5:

                    System.out.println("---------Lista inventory");
                    InventoryManager.listInventory();
                    break;
                case 6:
//todo: id wyciagnać, czy tak jest ok?
                    System.out.println("---------");
                    Invoice invoice = new Invoice();
                    InvoiceManager.addInvoice(invoice);
                    System.out.println("---------Stworzono fakturę o id: " + invoice.getId());
                    break;
                case 7:

                    System.out.println("Enter Invoice id");
                    Long invoiceId = input.nextLong();
                    System.out.println("Enter quantity");
                    String quantitySale = input.next();
                    System.out.println("Enter price");
                    String priceSale = input.next();
                    InvoiceManager.addSale(invoiceId, quantitySale, priceSale );
                    break;
                case 8:
   InvoiceManager.listaproduktownafakturze();
                    System.exit(1);
                default:
                    System.out.println("invalid input");
                    break;
            }
        }

    }

}

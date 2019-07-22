package pl.sda.store.logic;

import pl.sda.store.model.Product;

import java.util.Optional;

public class ProductManager {


    public static void addProduct(Product product){
        EntityDao entityDao = new EntityDao();
        entityDao.save(product);
    }

    public static void listProducts(){
        EntityDao entityDao = new EntityDao();
        entityDao.getListOfAll(Product.class).forEach(System.out::println);
    }

    public static void listInventory(Long idProduct){
        EntityDao entityDao = new EntityDao();
        Optional<Product> product = entityDao.getById(Product.class, idProduct);
        product.ifPresent(product1 -> product1.getInventory().forEach(System.out::println));
    }

// NIE ROBIC STATYCZNYCH !!

}

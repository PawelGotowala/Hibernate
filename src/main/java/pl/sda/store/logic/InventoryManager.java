package pl.sda.store.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.store.model.Inventory;
import pl.sda.store.model.Product;

import java.time.LocalDate;

public class InventoryManager {

    public static void addToInventory(String quantity, String value, Long productId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            // logika logika logika...
            // 1. stworzenie inventory
            Inventory inventory = new Inventory( quantity, value, LocalDate.now());

            // 2. zapisanie inventory
            session.save(inventory);

            // 3. pobranie produktu z identyfikatorem productId
            Product product = session.get(Product.class, productId);
            // Gdyby produktu nie udało się odnaleźć otrzymalibyśmy exception
            // co spowodowałoby wykonanie rollback'a.

            inventory.setProduct(product);
            product.getInventory().add(inventory);

            session.update(product);
            session.update(inventory);
//            session.save(repairOrder);

            transaction.commit();
        } catch (Exception sqle) {// dzięki try - with - resources nie musimy robić 'close' na sesji
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void listInventory(){
        EntityDao entityDao = new EntityDao();
        entityDao.getListOfAll(Inventory.class).forEach(System.out::println);
    }


}

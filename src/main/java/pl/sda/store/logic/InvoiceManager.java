package pl.sda.store.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.store.model.Invoice;
import pl.sda.store.model.ProductSale;


public class InvoiceManager {

    public static void addInvoice(Invoice invoice){
        EntityDao entityDao = new EntityDao();
        entityDao.save(invoice);
    }


    public static void addSale(Long idInvoice, String quantity, String price){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            // logika logika logika...
            // 1. stworzenie
            ProductSale productSale = new ProductSale(quantity,price);
            // 2. zapisanie inventory
            session.save(productSale);

            // 3. pobranie produktu z identyfikatorem productId
            Invoice invoice = session.get(Invoice.class, idInvoice);
            // Gdyby produktu nie udało się odnaleźć otrzymalibyśmy exception
            // co spowodowałoby wykonanie rollback'a.


            invoice.getProductSalesList().add(productSale);

            session.update(invoice);

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
    public static void listaproduktownafakturze(){
        EntityDao entityDao = new EntityDao();
        entityDao.getById(Invoice.class , (long) 1).get().getProductSalesList().forEach(System.out::println);
    }

}

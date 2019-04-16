package pl.sda.store.logic;

import pl.sda.store.model.IBaseEntity;

public class ProductManager {

    public static void addProduct(IBaseEntity iBaseEntity){
        EntityDao entityDao = new EntityDao();
        entityDao.save(iBaseEntity);
    }



}

package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TESTE 1: sellerFindById ===");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);
        System.out.println();


        System.out.println("=== TESTE 2: sellerFindDepartment ===");
        Department dep = new Department(1,null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for (Seller seller1 : list) {
            System.out.println(seller1);
            System.out.println();
        }
        System.out.println();

        System.out.println("=== TESTE 3: sellerFindAll ===");

        list = sellerDao.findAll();
        for (Seller seller1 : list) {
            System.out.println(seller1);
            System.out.println();
        }

        System.out.println("=== TESTE 4: sellerInsert ===");

        Seller newSeller = new Seller(null,"Greg", "greg@gmail.com", new Date(), 4000.00, dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());


    }
}
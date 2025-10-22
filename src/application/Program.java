package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("\n=== TESTE 1: sellerFindById ===");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);
        System.out.println();


        System.out.println("\n=== TESTE 2: sellerFindDepartment ===");
        Department dep = new Department(1,null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for (Seller seller1 : list) {
            System.out.println(seller1);
            System.out.println();
        }
        System.out.println();

        System.out.println("\n=== TESTE 3: sellerFindAll ===");

        list = sellerDao.findAll();
        for (Seller seller1 : list) {
            System.out.println(seller1);
            System.out.println();
        }

//        System.out.println("\n=== TESTE 4: sellerInsert ===");
//
//        Seller newSeller = new Seller(null,"Greg", "greg@gmail.com", new Date(), 4000.00, dep);
//        sellerDao.insert(newSeller);
//        System.out.println("Inserted! New id = " + newSeller.getId());
//        System.out.println();


        System.out.println("\n=== TESTE 5: sellerUpdate ===");
        seller = sellerDao.findById(1);
        seller.setName("Marta Waine");
        sellerDao.update(seller);
        System.out.println("Updated!");
        System.out.println();


        System.out.println("\n=== TESTE 6: sellerDelete ===");
        System.out.println("Id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed!");

        sc.close();

    }
}
package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dep = new Department(null, "Music");

        departmentDao.insert(dep);

        System.out.println("\n=== TESTE 1: sellerFindById ===");
        System.out.println(dep);
        System.out.println();

    }
}

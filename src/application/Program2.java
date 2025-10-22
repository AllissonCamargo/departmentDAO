package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dep = new Department(null, "Stock");

        System.out.println("\n=== TESTE 1: departmentInsert ===");
        departmentDao.insert(dep);
        System.out.println(dep);
        System.out.println();

        System.out.println("\n=== TESTE 2: departmentUpdate ===");
        Department dep2 = departmentDao.findById(1);
        dep2.setName("ADM");
        departmentDao.uptdate(dep2);
        System.out.println("Updated!");
        System.out.println(dep2);
        System.out.println();

        System.out.println("=== TEST 1: findById =======");
        Department dep3 = departmentDao.findById(1);
        Department dep4 = departmentDao.findById(4);
        System.out.println(dep3);
        System.out.println(dep4);




        System.out.println("\n=== TESTE 3: departmentDeleteById ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");

        System.out.println("\n=== TEST 4: findAll =======");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }


        sc.close();
    }
}

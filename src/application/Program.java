package application;

import entities.Department;
import entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department obj = new Department(1, "livros");

        System.out.println(obj);

        Seller seller = new Seller(1, "Jhon", "jhon@gmail.com", new Date(), 3000.00, obj);

        System.out.println(seller);
    }
}
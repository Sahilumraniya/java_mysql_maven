package org.example;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        Scanner sc = new Scanner(System.in);
        int choise;

        do{
            System.out.println("1 for add");
            System.out.println("2 for update");
            System.out.println("3 for delete");
            System.out.println("4 for getAll");
            System.out.print("Enter your choise : ");
            choise = sc.nextInt();
            switch (choise){
                case 1:
                    sc.nextLine();
                    System.out.println("Enter name : ");
                    String name = sc.nextLine();
                    System.out.println("Enter Email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter password : ");
                    String password = sc.next();
                    System.out.println(name+" "+email+" "+password);
                    db.add(name,email,password);
                    break;
                case 2:
                    System.out.println("Enter id that you have update : ");
                    int id = sc.nextInt();
                    System.out.println("Enter value other wise left empty");
                    sc.nextLine();
                    System.out.println("Enter name : ");
                    name = sc.nextLine();
                    System.out.println("Enter Email : ");
                    email = sc.nextLine();
                    System.out.println("Enter password : ");
                    password = sc.nextLine();
                    db.update(id, Optional.of(name.trim()), Optional.of(email.trim()), Optional.of(password.trim()));
                    break;
                case 3:
                    System.out.println("Enter id that you have delete : ");
                    id = sc.nextInt();
                    db.delete(id);
                    break;
                case 4:
                    db.getAll();
                    break;
            }
        }while (choise!=0);

    }
}
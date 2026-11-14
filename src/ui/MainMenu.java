package ui;

import java.util.Scanner;

public class MainMenu {
    private final Scanner sc = new Scanner(System.in);
    public int displayMenu() {
        
        System.out.println("===== Carteen =====");
        System.out.println("Welcome User!");
        System.out.println();
        System.out.println("[1] Order Food");
        System.out.println("[2] View History");
        System.out.println("[3] Exit");

        System.out.println();

        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();

        return choice;
    }

    public void orderfood(){
            
    }

    public void viewHistory(){

    }

    public void exitProgram(){
        
    }

}

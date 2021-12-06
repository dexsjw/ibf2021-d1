package ibf2021;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        
        ArrayList<String> cart = new ArrayList<>();
        
        System.out.println("Welcome to your shopping cart! Please separate items with a comma.");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        
        while (!input.trim().toLowerCase().equals("close")) {

            if (input.trim().toLowerCase().equals("list")) {                                    //start of "list" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d.%s\n", (i+1), cart.get(i));
                    }
                }                                                                               //end of "list" block

            } else if (input.trim().toLowerCase().equals("add")) {                              //start of "add" block
                Scanner addScan = new Scanner(scan.nextLine());
                if (!addScan.hasNext()) {
                    System.out.println("Please input item to add");
                } else {
                    while (addScan.hasNext()) {
                        addScan.useDelimiter(",");
                        String addItem = addScan.next();
                        if (cart.contains(addItem)) {
                            System.out.printf("You have %s in your cart\n", addItem);
                        } else {
                            cart.add(addItem);
                            System.out.printf("%s added to cart\n", addItem);
                        }
                    }          
                }                                                                               //end of "add" block

            } else if (input.trim().toLowerCase().equals("delete")) {                           //start of "delete" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    Scanner deleteScan = new Scanner(scan.next());
                    if (deleteScan.hasNextInt()) {
                        while (deleteScan.hasNext()) {
                        int index = Integer.parseInt(deleteScan.next());
                            if (index > cart.size()) {
                                System.out.println("Incorrect item index");
                            } else {
                                String deleteItem = cart.get(index-1);
                                cart.remove(index-1);
                                System.out.printf("%s removed from cart\n", deleteItem);
                            }
                        }
                    } else {
                        System.out.println("Please only select index NUMBER to delete");
                    }
                }                                                                               //end of "delete" block

            } else {
                System.out.println("Invalid input! Please use list, add or delete only.");
            }
            input = scan.next();
        }
        scan.close();
    }
}
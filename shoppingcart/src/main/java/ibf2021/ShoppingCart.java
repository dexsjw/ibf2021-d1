package ibf2021;

import java.util.ArrayList;
import java.util.Scanner;

import ibf2021.d3.ShoppingCartDB;

public class ShoppingCart {
    public static void main(String[] args) {
        
/*         //Using Scanner - start
        ArrayList<String> cart = new ArrayList<>();

        System.out.println("Welcome to your shopping cart! Please separate items with a comma.");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        //String scanned = scan.nextLine();
        
        while (!input.trim().toLowerCase().equals("close")) {

            if (input.trim().toLowerCase().equals("list")) {                                    //start of "list" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d.%s%n", (i+1), cart.get(i));
                    }
                }                                                                               //end of "list" block

            } else if (input.trim().toLowerCase().equals("add")) {                              //start of "add" block
                Scanner scanned = new Scanner(scan.nextLine());
                if (!scanned.hasNext()) {
                    System.out.println("Please input item to add");
                } else {
                    while (scanned.hasNext()) {
                        scanned.useDelimiter(",");
                        String addItem = scanned.next();
                        if (cart.contains(addItem)) {
                            System.out.printf("You have %s in your cart%n", addItem);
                        } else {
                            cart.add(addItem);
                            System.out.printf("%s added to cart%n", addItem);
                        }
                    }          
                }                                                                               //end of "add" block

            } else if (input.trim().toLowerCase().equals("delete")) {                           //start of "delete" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    Scanner scanned = new Scanner(scan.next());
                    if (scanned.hasNextInt()) {
                        //while (scanned.hasNext()) { //don't need while
                        int index = Integer.parseInt(scanned.next());
                            if (index > cart.size()) {
                                System.out.println("Incorrect item index");
                            } else {
                                String deleteItem = cart.get(index-1);
                                cart.remove(index-1);
                                System.out.printf("%s removed from cart%n", deleteItem);
                            }
                        //}
                    } else {
                        System.out.println("Please only select index NUMBER to delete");
                    }
                }                                                                               //end of "delete" block

            } else {
                System.out.println("Invalid input! Please only use list, add <item> or delete <item number>.");
            }
            input = scan.next();
        }
        scan.close();
        //Using Scanner - end */

        //Using String[] - start
        ArrayList<String> cart = new ArrayList<>();
        ShoppingCartDB scdb = new ShoppingCartDB();
        String username = "";

        System.out.println("Welcome to your shopping cart! Please separate items with a comma.");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        String scanned = scan.nextLine();
        
        while (!input.trim().toLowerCase().equals("close")) {

            if (input.trim().toLowerCase().equals("list")) {                                    //start of "list" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d. %s%n", (i+1), cart.get(i));
                    }
                }                                                                               //end of "list" block


            } else if (input.trim().toLowerCase().equals("add")) {                              //start of "add" block
                if (scanned.isEmpty()) {
                    System.out.println("Please input item to add");
                } else {
                    String[] item = scanned.split(",");
                    for (String addItem: item) {
                        if (cart.contains(addItem.trim())) {
                            System.out.printf("You have %s in your cart%n", addItem.trim());
                        } else {
                            cart.add(addItem.trim());
                            System.out.printf("%s added to cart%n", addItem.trim());
                        }
                    }
                }                                                                                //end of "add" block


            } else if (input.trim().toLowerCase().equals("delete")) {                           //start of "delete" block
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    if (!scanned.isEmpty()) {

                        try {
                            int index = Integer.parseInt(scanned.trim());
                            if (index > cart.size()) {
                                System.out.println("Incorrect item index");
                            } else {
                                String deleteItem = cart.get(index-1);
                                cart.remove(index-1);
                                System.out.printf("%s removed from cart%n", deleteItem);
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Please only select index NUMBER to delete");
                        }

                    } else {
                        System.out.println("Please select an index NUMBER to delete");
                    }
                }                                                                               //end of "delete" block


            } else if (input.trim().toLowerCase().equals("login")) {                            //start of "login" block
                if (scanned.isEmpty()) {
                    System.out.println("Please input username");
                } else {
                    username = scanned.trim();
                    cart = scdb.login(username);
                }                                                                               //end of "login" block

                
            } else if (input.trim().toLowerCase().equals("save")) {                             //start of "save" block
                if (username.isEmpty() == true) {
                    System.out.println("Please login first");
                } else {
                    scdb.save(username, cart);
                    System.out.println("Your cart has been saved");
                }                                                                               //end of "save" block


            } else if (input.trim().toLowerCase().equals("users")) {
                scdb.users();

            } else {
                System.out.println("Invalid input! Please only use list, add <item>, delete <item number>,");
                System.out.println("login <username>, save or users.");
            }
            input = scan.next();
            scanned = scan.nextLine();
        }
        scan.close();
        //Using String[] - end
    }
}
package ibf2021.d3;

import java.io.BufferedWriter;
//import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShoppingCartDB {
    
/*     
    login
    1. new user
        a. create new db
    2. existing user 
        a. read db
        b. display db

    save
    1. file empty
        a. write to db
    2. file exists
        a. delete existing file
        b. create new db
        c. write to db

    users
    1. list of users
        a. read filenames
        b. display filenames

    Methods required:
    1. creating new file
    2. deleting existing file
    3. writing into new file
    4. reading and displaying existing file
    5. reading and displaying existing filenames
 */

    public void toCreate(String username) {                                                                     //CREATE
        try {
            //System.out.println(new File(".").getCanonicalPath()); - used to determine current directory
            Path userdb = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb\\" + username + ".db");
            Files.createFile(userdb);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void toDelete(String username) {                                                                     //DELETE
        try {
            Path userdb = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb\\" + username + ".db");
            Files.deleteIfExists(userdb);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }

    public void toWrite(String username, ArrayList<String> cart) {                                              //WRITE
        try {
            Path userdb = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb\\" + username + ".db");
/*             String[] item = cart.toArray(new String[cart.size()]);
            for (String cartItem: item) {
                //save each item on new line
            } */
            String item = String.join("\n", cart);
            if (Files.isWritable(userdb)) {
                BufferedWriter writer = Files.newBufferedWriter(userdb);
                writer.write(item);
                writer.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public List<String> toReadDBFile(String username) {                                                         //READ
        List<String> item = new ArrayList<>();
        try {
            Path userdb = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb\\" + username + ".db");
            if (Files.isReadable(userdb)) {
                item = Files.readAllLines(userdb);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return item;
    }

    public ArrayList<String> login(String username) {                                                           //LOGIN
        List<String> item = new ArrayList<>();
        ShoppingCartDB scdb = new ShoppingCartDB();
        Path userdb = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb\\" + username + ".db");

        if (Files.notExists(userdb) == true) {
            scdb.toCreate(username);
            System.out.printf("%s, your cart is empty%n", username);

        } else {
            item = scdb.toReadDBFile(username);
            System.out.printf("%s, your cart contains the following items%n", username);
            /* for (int i = 0; i < cart.size(); i++) {
                System.out.printf("%d. %s%n", (i+1), cart.get(i));
            } */
            int i = 1;
            for (String cartItem: item) {
                System.out.printf("%d. %s%n", i, cartItem);
                i++;
            }
        }

        return (ArrayList<String>) item;
    }

    public void save(String username, ArrayList<String> cart) {                                                 //SAVE                                 //SAVE
        ShoppingCartDB scdb = new ShoppingCartDB();
        scdb.toDelete(username);
        scdb.toCreate(username);
        scdb.toWrite(username, cart);
    }

    public void users() {                                                                                       //USERS
        System.out.println("The following users are registered");
        try {
            //System.out.println(new File(".").getCanonicalPath());
            Path cartdbdir = Paths.get("shoppingcart\\src\\main\\java\\ibf2021\\d3\\cartdb");
            Stream<Path> cartdbpath = Files.list(cartdbdir);
            List<Path> users = cartdbpath.toList();
            int i = 1;
            for (Path user: users) {
                String username = user.getFileName().toString().replace(".db", "");
                System.out.printf("%d. %s%n", i, username);
                i++;
            }
        
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}

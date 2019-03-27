import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreateCatalogDB cd = new CreateCatalogDB();

        // Method to print list of items from Catalog DB
        outputCatalogDB();
        Scanner keyboard = new Scanner(System.in);
        int entry = 0;
        while (entry != 3) {
            System.out.println("Please select an option:\n1. Add to cart\n2. Review cart\n3. Quit");
            entry = keyboard.nextInt();
            if (entry == 1) {
                int item = 9;
                while (item != 0) {
                    System.out.println("Enter item ID or 0 to quit: ");
                    item = keyboard.nextInt();
                    switch (item) {
                        case 1:
                            System.out.println("Adding Pizza to your cart...");
                            addContent("1");
                            System.out.println("Adding complete.");
                            break;
                        case 2:
                            System.out.println("Adding Coffee to your cart...");
                            addContent("2");
                            System.out.println("Adding complete.");
                            break;
                        case 3:
                            System.out.println("Adding Tiramisu to your cart...");
                            addContent("3");
                            System.out.println("Adding complete.");
                            break;
                        case 4:
                            System.out.println("Adding Calzone to your cart...");
                            addContent("4");
                            System.out.println("Adding complete.");
                            break;
                        case 5:
                            System.out.println("Adding Soda to your cart...");
                            addContent("5");
                            System.out.println("Adding complete.");
                            break;
                        case 6:
                            System.out.println("Adding Fries to your cart...");
                            addContent("6");
                            System.out.println("Adding complete.");
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid item number.");
                    }
                }
            }
            if (entry == 2) {
                outputCartDB();
            }
            if (entry == 3) {
                break;
            }
        }
        // Method to add content to the cart DB
        //addContent();
    }

    public static void addContent(String id) {
        final String DB_URL = "jdbc:derby:CatalogDB";
        Statement stmt = null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();

            // ADD ITEMS TO CART LOGIC
            String sql = "";
            switch(id) {
                case "1":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('1', 'Pizza', 12.95)";
                    stmt.executeUpdate(sql);
                    break;
                case "2":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('2', 'Coffee', 1.99)";
                    stmt.executeUpdate(sql);
                    break;
                case "3":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('3', 'Tiramisu', 5.99)";
                    stmt.executeUpdate(sql);
                    break;
                case "4":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('4', 'Calzone', 10.95)";
                    stmt.executeUpdate(sql);
                    break;
                case "5":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('5', 'Soda', 2.29)";
                    stmt.executeUpdate(sql);
                    break;
                case "6":
                    sql = "INSERT INTO Cart " +
                            "VALUES ('6', 'Fries', 3.99)";
                    stmt.executeUpdate(sql);
                    break;
            }

            System.out.println("Inserted records into the table...");

            //Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void outputCatalogDB() {
        final String DB_URL = "jdbc:derby:CatalogDB";
        Statement stmt = null;
        Connection conn = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ProdNum, Description, Price FROM Catalog";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String id = rs.getString("ProdNum");
                double price = rs.getDouble("Price");
                String description = rs.getString("Description");

                System.out.print("ID: " + id.trim());
                System.out.print(", Price: " + price);
                System.out.println(", Description: " + description);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {}
            try{
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void outputCartDB() {
        final String DB_URL = "jdbc:derby:CatalogDB";
        Statement stmt = null;
        Connection conn = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ProdNum, Description, Price FROM Cart";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String id = rs.getString("ProdNum");
                double price = rs.getDouble("Price");
                String description = rs.getString("Description");

                System.out.print("ID: " + id.trim());
                System.out.print(", Price: " + price);
                System.out.println(", Description: " + description);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {}
            try{
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }
}
